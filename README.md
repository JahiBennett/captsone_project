# Job Portal Login System

A complete login system for job seekers and employers with Python Flask backend and Java Swing desktop application.

## 🌟 Features

- **Dual User Types**: Separate login for Job Seekers and Employers
- **Modern UI**: Green-themed professional design with centered logo and login box
- **Secure Authentication**: Password hashing with werkzeug security
- **Responsive Design**: Web interface works on all devices
- **Desktop Application**: Java Swing version with JButton components

## 📁 Project Structure

```
├── app.py                          # Flask backend server
├── JobPortalLogin.java             # Java Swing desktop application
├── templates/
│   ├── login.html                  # Main login page (HTML/CSS/JavaScript)
│   ├── dashboard_job_seeker.html   # Job seeker dashboard
│   └── dashboard_employer.html     # Employer dashboard
└── job_portal.db                   # SQLite database (auto-created)
```

## 🚀 Web Application Setup

### Prerequisites
- Python 3.7+
- pip package manager

### Installation

1. **Install Flask and dependencies**:
```bash
pip install flask werkzeug --break-system-packages
```

2. **Run the application**:
```bash
python app.py
```

3. **Access the application**:
Open your browser and navigate to: `http://localhost:5000`

### Demo Credentials

**Job Seeker:**
- Username: `jobseeker1`
- Password: `password123`

**Employer:**
- Username: `employer1`
- Password: `password123`

## 🖥️ Java Desktop Application Setup

### Prerequisites
- Java JDK 8 or higher
- Java compiler (javac)

### Compilation and Execution

1. **Compile the Java application**:
```bash
javac JobPortalLogin.java
```

2. **Run the application**:
```bash
java JobPortalLogin
```

### Java Application Features
- Swing-based GUI with JButton components
- Custom painted green header background
- Centered login panel
- User type toggle buttons
- Password masking
- Dashboard with logout functionality

## 🎨 Design Features

### Web Application (HTML/CSS/JavaScript)
- **Green Gradient Header**: Top 50% of page with gradient background
- **Centered Logo**: White circular logo with "JP" initials
- **Business Name**: "JOB PORTAL" prominently displayed
- **Floating Login Box**: Centered white card with shadow effects
- **User Type Toggle**: Buttons to switch between Job Seeker and Employer
- **Smooth Animations**: Fade-in effects on page load
- **Responsive Layout**: Adapts to different screen sizes

### Java Application
- **Custom Graphics**: Java2D gradient painting for header
- **Swing Components**: JButton, JTextField, JPasswordField
- **Event Handling**: ActionListener for button clicks
- **Password Security**: Password field masking
- **Dashboard Navigation**: Seamless transition after login

## 🔐 Security Features

- Password hashing using werkzeug security
- Session management with Flask sessions
- SQL injection prevention with parameterized queries
- User type verification on login
- Secure password storage (never stored in plain text)

## 📝 API Endpoints

### POST /login
Authenticates user credentials
```json
{
  "username": "string",
  "password": "string",
  "user_type": "job_seeker" | "employer"
}
```

### POST /register
Creates new user account
```json
{
  "username": "string",
  "password": "string",
  "user_type": "job_seeker" | "employer",
  "email": "string"
}
```

### GET /logout
Clears session and redirects to login

## 🛠️ Technologies Used

### Web Application
- **Backend**: Python Flask
- **Database**: SQLite3
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Security**: Werkzeug password hashing

### Desktop Application
- **Language**: Java
- **GUI Framework**: Swing
- **Components**: JFrame, JPanel, JButton, JTextField, JPasswordField
- **Graphics**: Java2D for custom painting

## 📱 Browser Compatibility

- Chrome (recommended)
- Firefox
- Safari
- Edge
- Opera

## 🎯 Usage Instructions

### Web Application

1. Open the login page
2. Select user type (Job Seeker or Employer)
3. Enter credentials
4. Click "Login" button
5. You'll be redirected to the appropriate dashboard

### Java Application

1. Launch the application
2. Click on user type button (Job Seeker or Employer)
3. Enter username and password
4. Press "Login" button or hit Enter
5. Dashboard window will open

## 🔄 Extending the System

### Adding New Users Programmatically

You can add users to the database by modifying the `init_db()` function in `app.py`:

```python
demo_users = [
    ('newuser', 'newpassword', 'job_seeker', 'email@example.com'),
]
```

### Adding New Features

The modular structure allows easy addition of:
- Job posting functionality
- Application tracking
- Profile management
- Resume upload
- Email notifications
- Advanced search filters

## 🐛 Troubleshooting

**Port already in use:**
```bash
# Change port in app.py
app.run(debug=True, port=5001)
```

**Database locked:**
```bash
# Delete and recreate database
rm job_portal.db
python app.py
```

**Java compilation error:**
```bash
# Ensure JDK is installed
java -version
javac -version
```

## 📄 License

This project is open source and available for educational purposes.

## 👥 Contributors

Created for dual-user job portal system with optimized login functionality.

## 🎨 Color Scheme

- Primary Green: #27ae60 (RGB: 39, 174, 96)
- Light Green: #2ecc71 (RGB: 46, 204, 113)
- Dark Text: #2c3e50
- Light Text: #7f8c8d
- Background: #f5f5f5
- White: #ffffff

## 📞 Support

For issues or questions, please check:
- Flask documentation: https://flask.palletsprojects.com/
- Java Swing tutorial: https://docs.oracle.com/javase/tutorial/uiswing/
