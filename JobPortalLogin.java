import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class JobPortalLogin extends JFrame {
    
    // Store user credentials (in production, use a proper database)
    private static Map<String, User> jobSeekers = new HashMap<>();
    private static Map<String, User> employers = new HashMap<>();
    
    // UI Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton jobSeekerButton;
    private JButton employerButton;
    private JLabel messageLabel;
    private String selectedUserType = "job_seeker";
    
    // Inner class to represent a user
    static class User {
        String username;
        String password;
        String userType;
        
        User(String username, String password, String userType) {
            this.username = username;
            this.password = password;
            this.userType = userType;
        }
    }
    
    // Initialize demo users
    static {
        jobSeekers.put("jobseeker1", new User("jobseeker1", "password123", "job_seeker"));
        employers.put("employer1", new User("employer1", "password123", "employer"));
    }
    
    public JobPortalLogin() {
        // Frame setup
        setTitle("Job Portal - Login");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel with custom paint for green header
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                   RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Green gradient background for top half
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(46, 204, 113),
                    0, getHeight() / 2, new Color(39, 174, 96)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight() / 2);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        
        // Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(150, 80, 200, 200);
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BorderLayout());
        
        // Logo circle
        JLabel logoLabel = new JLabel("JP", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setForeground(new Color(39, 174, 96));
        logoLabel.setOpaque(true);
        logoLabel.setBackground(Color.WHITE);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        logoLabel.setBounds(175, 100, 150, 100);
        
        // Business name
        JLabel businessName = new JLabel("JOB PORTAL", SwingConstants.CENTER);
        businessName.setFont(new Font("Arial", Font.BOLD, 32));
        businessName.setForeground(Color.WHITE);
        businessName.setBounds(100, 220, 300, 40);
        
        // Tagline
        JLabel tagline = new JLabel("Connecting Talent with Opportunity", SwingConstants.CENTER);
        tagline.setFont(new Font("Arial", Font.PLAIN, 14));
        tagline.setForeground(Color.WHITE);
        tagline.setBounds(100, 260, 300, 20);
        
        // Login container panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(50, 320, 400, 350);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 224, 224), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        loginPanel.setLayout(null);
        
        // Add shadow effect
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));
        
        // Welcome header
        JLabel welcomeLabel = new JLabel("Welcome Back", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(44, 62, 80));
        welcomeLabel.setBounds(0, 10, 340, 30);
        loginPanel.add(welcomeLabel);
        
        JLabel subLabel = new JLabel("Please login to continue", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(new Color(127, 140, 141));
        subLabel.setBounds(0, 40, 340, 20);
        loginPanel.add(subLabel);
        
        // User type buttons
        jobSeekerButton = new JButton("Job Seeker");
        jobSeekerButton.setBounds(20, 80, 150, 40);
        styleUserTypeButton(jobSeekerButton, true);
        
        employerButton = new JButton("Employer");
        employerButton.setBounds(180, 80, 150, 40);
        styleUserTypeButton(employerButton, false);
        
        // Username field
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(20, 140, 100, 20);
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        loginPanel.add(userLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(20, 165, 300, 35);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 224, 224), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginPanel.add(usernameField);
        
        // Password field
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(20, 210, 100, 20);
        passLabel.setFont(new Font("Arial", Font.BOLD, 12));
        loginPanel.add(passLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(20, 235, 300, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 224, 224), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginPanel.add(passwordField);
        
        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(20, 290, 300, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(39, 174, 96));
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Message label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(20, 335, 300, 25);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        loginPanel.add(messageLabel);
        
        // Add action listeners
        jobSeekerButton.addActionListener(e -> selectUserType("job_seeker"));
        employerButton.addActionListener(e -> selectUserType("employer"));
        loginButton.addActionListener(e -> performLogin());
        
        // Add Enter key support
        passwordField.addActionListener(e -> performLogin());
        
        // Add hover effects
        addHoverEffect(loginButton, new Color(39, 174, 96), new Color(46, 204, 113));
        
        // Add components to panels
        loginPanel.add(jobSeekerButton);
        loginPanel.add(employerButton);
        loginPanel.add(loginButton);
        
        mainPanel.add(logoLabel);
        mainPanel.add(businessName);
        mainPanel.add(tagline);
        mainPanel.add(loginPanel);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void styleUserTypeButton(JButton button, boolean isActive) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (isActive) {
            button.setBackground(new Color(39, 174, 96));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(new Color(39, 174, 96), 2));
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(127, 140, 141));
            button.setBorder(BorderFactory.createLineBorder(new Color(224, 224, 224), 2));
        }
    }
    
    private void selectUserType(String userType) {
        selectedUserType = userType;
        styleUserTypeButton(jobSeekerButton, userType.equals("job_seeker"));
        styleUserTypeButton(employerButton, userType.equals("employer"));
        messageLabel.setText("");
    }
    
    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Please fill in all fields", false);
            return;
        }
        
        Map<String, User> users = selectedUserType.equals("job_seeker") ? jobSeekers : employers;
        User user = users.get(username);
        
        if (user != null && user.password.equals(password)) {
            showMessage("Login successful!", true);
            
            // Open dashboard
            SwingUtilities.invokeLater(() -> {
                new Dashboard(username, selectedUserType);
                dispose();
            });
        } else {
            showMessage("Invalid credentials", false);
        }
    }
    
    private void showMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setForeground(isSuccess ? new Color(21, 87, 36) : new Color(114, 28, 36));
    }
    
    private void addHoverEffect(JButton button, Color normal, Color hover) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
            }
        });
    }
    
    // Dashboard window
    static class Dashboard extends JFrame {
        public Dashboard(String username, String userType) {
            setTitle((userType.equals("job_seeker") ? "Job Seeker" : "Employer") + " Dashboard");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(245, 245, 245));
            
            // Header
            JPanel header = new JPanel();
            header.setBackground(new Color(39, 174, 96));
            header.setPreferredSize(new Dimension(800, 80));
            
            JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            welcomeLabel.setForeground(Color.WHITE);
            header.add(welcomeLabel);
            
            JButton logoutBtn = new JButton("Logout");
            logoutBtn.setBackground(Color.WHITE);
            logoutBtn.setForeground(new Color(39, 174, 96));
            logoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
            logoutBtn.setFocusPainted(false);
            logoutBtn.addActionListener(e -> {
                new JobPortalLogin();
                dispose();
            });
            header.add(logoutBtn);
            
            // Content
            JLabel contentLabel = new JLabel(
                "<html><center><h1>" + 
                (userType.equals("job_seeker") ? "Job Seeker" : "Employer") + 
                " Dashboard</h1><br>Dashboard features would be displayed here</center></html>",
                SwingConstants.CENTER
            );
            contentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            
            panel.add(header, BorderLayout.NORTH);
            panel.add(contentLabel, BorderLayout.CENTER);
            
            add(panel);
            setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new JobPortalLogin());
    }
}
