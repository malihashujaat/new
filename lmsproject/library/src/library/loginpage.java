package library;
import java.awt.EventQueue;//sare event control karta hai
import java.io.*;//sari class call karne ke liye use hota hai
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;//hover
import java.awt.event.ActionListener;//buttn badane ke baad age kiya hona hai


public class loginpage {

    public JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private List<String[]> userList;
    private static final String USER_DATA_FILE = "users.dat";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginpage window = new loginpage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public loginpage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        loadUserData(); // Load user data from file at the beginning

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        lblNewLabel.setBounds(142, 11, 115, 34);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter your registered Email, Username, and Password:");
        lblNewLabel_1.setBounds(25, 62, 297, 29);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Email:");
        lblNewLabel_1_1.setBounds(25, 102, 46, 14);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Username:");
        lblNewLabel_1_2.setBounds(25, 137, 63, 14);
        frame.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_2_1 = new JLabel("Password:");
        lblNewLabel_1_2_1.setBounds(25, 178, 63, 14);
        frame.getContentPane().add(lblNewLabel_1_2_1);

        textField = new JTextField();
        textField.setBounds(107, 99, 240, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(107, 134, 240, 20);
        frame.getContentPane().add(textField_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(107, 175, 240, 20);
        frame.getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBounds(258, 213, 89, 23);
        frame.getContentPane().add(btnNewButton);

        // Action listener for the login button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get input values
                String email = textField.getText();
                String username = textField_1.getText();
                String password = new String(passwordField.getPassword());

                // Check if email contains "@"
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(frame, "Invalid email format. Please include '@'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the input matches admin credentials
                if (email.equals("admin@gmail.com") && username.equals("admin") && password.equals("admin")) {
                    // Open admin page if credentials match
                    frame.dispose(); // Close login page
                    admin window = new admin();
                    window.frame.setVisible(true);
                    return; // Exit the action listener
                }

                // Check if the input matches any user credentials in the list
                if (isUserCredentialsValid(email, username, password)) {
                    // Open user page if user credentials match
                    frame.dispose(); // Close login page
                    userpage window = new userpage();
                    window.frame.setVisible(true);
                } else {
                    // If neither admin nor user credentials match, show error message
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
                }
            }
        });
    }

    // Load user data from file
    @SuppressWarnings("unchecked")
    private void loadUserData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            userList = (List<String[]>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            userList = new ArrayList<>();
        }
    }

    // Check if the user credentials are valid
    private boolean isUserCredentialsValid(String email, String username, String password) {
        if (userList != null) {
            for (String[] userDetails : userList) {
                if (userDetails[1].equals(email) && userDetails[2].equals(username) && userDetails[3].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
