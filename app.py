from flask import Flask, render_template, request, jsonify, session, redirect, url_for
from werkzeug.security import generate_password_hash, check_password_hash
import sqlite3
from datetime import datetime
import os

app = Flask(__name__)
app.secret_key = 'your-secret-key-here-change-in-production'


def init_db():
    conn = sqlite3.connect('job_portal.db')
    cursor = conn.cursor()
    
   =
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT UNIQUE NOT NULL,
            password TEXT NOT NULL,
            user_type TEXT NOT NULL,
            email TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
    ''')
    
    
    demo_users = [
        ('jobseeker1', 'password123', 'job_seeker', 'seeker@example.com'),
        ('employer1', 'password123', 'employer', 'employer@example.com')
    ]
    
    for username, password, user_type, email in demo_users:
        try:
            hashed_password = generate_password_hash(password)
            cursor.execute('''
                INSERT INTO users (username, password, user_type, email)
                VALUES (?, ?, ?, ?)
            ''', (username, hashed_password, user_type, email))
        except sqlite3.IntegrityError:
            pass  # User already exists
    
    conn.commit()
    conn.close()


init_db()

@app.route('/')
def index():
    return render_template('login.html')

@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')
    user_type = data.get('user_type')
    
    if not username or not password or not user_type:
        return jsonify({'success': False, 'message': 'All fields are required'})
    
    conn = sqlite3.connect('job_portal.db')
    cursor = conn.cursor()
    
    cursor.execute('''
        SELECT id, password, user_type FROM users 
        WHERE username = ? AND user_type = ?
    ''', (username, user_type))
    
    user = cursor.fetchone()
    conn.close()
    
    if user and check_password_hash(user[1], password):
        session['user_id'] = user[0]
        session['username'] = username
        session['user_type'] = user[2]
        
        return jsonify({
            'success': True, 
            'message': 'Login successful',
            'user_type': user[2],
            'redirect': f'/dashboard/{user[2]}'
        })
    else:
        return jsonify({'success': False, 'message': 'Invalid credentials'})

@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')
    user_type = data.get('user_type')
    email = data.get('email')
    
    if not username or not password or not user_type:
        return jsonify({'success': False, 'message': 'All fields are required'})
    
    hashed_password = generate_password_hash(password)
    
    try:
        conn = sqlite3.connect('job_portal.db')
        cursor = conn.cursor()
        
        cursor.execute('''
            INSERT INTO users (username, password, user_type, email)
            VALUES (?, ?, ?, ?)
        ''', (username, hashed_password, user_type, email))
        
        conn.commit()
        conn.close()
        
        return jsonify({'success': True, 'message': 'Registration successful'})
    except sqlite3.IntegrityError:
        return jsonify({'success': False, 'message': 'Username already exists'})

@app.route('/dashboard/<user_type>')
def dashboard(user_type):
    if 'user_id' not in session:
        return redirect(url_for('index'))
    
    if session.get('user_type') != user_type:
        return redirect(url_for('index'))
    
    return render_template(f'dashboard_{user_type}.html', username=session.get('username'))

@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('index'))

if __name__ == '__main__':
    app.run(debug=True, port=5000)
