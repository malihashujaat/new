package library;

import java.awt.EventQueue;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class User {
    public JFrame frame;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private DefaultTableModel tableModel;
    public static List<String[]> userList = new ArrayList<>();
    private static int userIdCounter = 0;
    private static final String USER_DATA_FILE = "users.dat";

    public static boolean isValidUserId(String userId) {
        for (String[] user : userList) {
            if (user[0].equals(userId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    User window = new User();
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
    public User() {
        loadUserData();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 578, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        tableModel = new DefaultTableModel(new Object[]{"User ID", "Email", "Username", "Password"}, 0);
        table = new JTable(tableModel);
        table.setBounds(166, 22, 300, 228);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(239, 23, 300, 228);
        frame.getContentPane().add(scrollPane);

        JButton btnNewButton = new JButton("<");
        btnNewButton.setBounds(10, 11, 41, 23);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                admin window = new admin();
                window.frame.setVisible(true);
            }
        });

        JButton btnNewButton_1 = new JButton("Add User");
        btnNewButton_1.setBounds(114, 187, 89, 23);
        frame.getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("Add User");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(43, 45, 64, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("View Details");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(346, 0, 81, 21);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Email:");
        lblNewLabel_2.setBounds(10, 70, 46, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Username:");
        lblNewLabel_3.setBounds(10, 106, 71, 14);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_3_1 = new JLabel("Password:");
        lblNewLabel_3_1.setBounds(10, 142, 71, 14);
        frame.getContentPane().add(lblNewLabel_3_1);

        textField = new JTextField();
        textField.setBounds(114, 67, 89, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(114, 103, 89, 20);
        frame.getContentPane().add(textField_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(114, 139, 89, 20);
        frame.getContentPane().add(passwordField);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String username = textField_1.getText();
                String password = new String(passwordField.getPassword());

                // Check if the username already exists
                if (isUsernameExists(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method without adding the user
                }

                // Generate a unique user ID
                String userId = "U" + ++userIdCounter;

                // Add user details to the list
                String[] userDetails = {userId, email, username, password};
                userList.add(userDetails);

                // Add user details to the table model
                tableModel.addRow(new Object[]{userId, email, username, password});

                // Save user data to file
                saveUserData();

                // Clear the input fields
                textField.setText("");
                textField_1.setText("");
                passwordField.setText("");
            }
        });

        for (String[] user : userList) {
            if (user.length == 4) { // Ensure the array has the correct length
                tableModel.addRow(new Object[]{user[0], user[1], user[2], user[3]});
            }
        }
    }

    private boolean isUsernameExists(String username) {
        for (String[] user : userList) {
            if (user[2].equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    private void saveUserData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            out.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadUserData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                userList = (List<String[]>) obj;
            } else {
                userList = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            userList = new ArrayList<>();
        }
    }
}
