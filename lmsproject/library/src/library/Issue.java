package library;

import java.awt.EventQueue;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Issue {
    public JFrame frame;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_1;
    private JTable table;

    private DefaultTableModel tableModel;
    public static List<String[]> issueList = new ArrayList<>();
    private static int issueIdCounter = 0;
    private static final String ISSUE_DATA_FILE = "issues.dat";
    private static final String DATE_PATTERN = "\\d{2}/\\d{2}/\\d{4}";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Issue window = new Issue();
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
    public Issue() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * @return 
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 820, 354);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        loadIssueData(); // Load issue data from file at the beginning

        JLabel lblNewLabel = new JLabel("Issue Book");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(74, 15, 89, 20);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(153, 68, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("What book do you want to issue");
        lblNewLabel_1.setBounds(20, 46, 250, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Book Name:");
        lblNewLabel_2.setBounds(20, 71, 80, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Date of Issue:");
        lblNewLabel_3.setBounds(20, 99, 80, 14);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Date of Return:");
        lblNewLabel_4.setBounds(20, 129, 89, 14);
        frame.getContentPane().add(lblNewLabel_4);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(153, 96, 86, 20);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(153, 126, 86, 20);
        frame.getContentPane().add(textField_2);

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

        JButton btnNewButton_1 = new JButton("Issue");
        btnNewButton_1.setBounds(150, 212, 89, 23);
        frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
                String bookName = textField.getText();
                String issueDate = textField_1.getText();
                String returnDate = textField_2.getText();
                String userId = textField_3.getText();
                String returnstatus = "";

                if (!isValidDate(issueDate)) {
                    JOptionPane.showMessageDialog(frame, "Please enter the issue date in the format dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidDate(returnDate)) {
                    JOptionPane.showMessageDialog(frame, "Please enter the return date in the format dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidBookName(bookName)) {
                    JOptionPane.showMessageDialog(frame, "The book name is not valid or does not exist in the library.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidUserId(userId)) {
                    JOptionPane.showMessageDialog(frame, "The user ID is not valid or does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Generate unique issue ID
                String issueId = "I" + ++issueIdCounter;
                String fine = null;
                // Add issue details to the list
                String[] issueDetails = {issueId, bookName, issueDate, returnDate, userId, returnstatus,fine};
                issueList.add(issueDetails);

                // Add issue details to the table model
                tableModel.addRow(new Object[]{issueId, bookName, issueDate, returnDate, userId, returnstatus, 0});

                // Save issue data to file
                saveIssueData();

                // Clear the text fields after adding the issue
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
            }
        });

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(153, 151, 86, 20);
        frame.getContentPane().add(textField_3);

        JLabel lblNewLabel_5 = new JLabel("User ID:");
        lblNewLabel_5.setBounds(20, 154, 46, 14);
        frame.getContentPane().add(lblNewLabel_5);

        tableModel = new DefaultTableModel(new Object[]{"Issue ID", "Book Name", "Issue Date", "Return Date", "User ID", "Return Status", "Fine"}, 0);
        table = new JTable(tableModel);
        table.setBounds(297, 20, 281, 230);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(249, 25, 545, 279);
        frame.getContentPane().add(scrollPane);

        JLabel lblAllIssueBook = new JLabel("All Issue Book");
        lblAllIssueBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAllIssueBook.setBounds(409, 0, 149, 20);
        frame.getContentPane().add(lblAllIssueBook);

        for (String[] issue : issueList) {
            // Check if the book has been returned
            String returnStatus = issue[5];
            String fine = issue[6];
            // Update the return status and fine if available
            if (returnStatus.equals("Yes")) {
            	tableModel.addRow(new Object[]{issue[0], issue[1], issue[2], issue[3], issue[4], returnStatus, fine});



            } else {
                // If the book hasn't been returned yet, show return status as "No" and fine as 0tableModel.addRow(new Object[]{issue[0], issue[1], issue[2], issue[3], issue[4], returnStatus, fine});

            	tableModel.addRow(new Object[]{issue[0], issue[1], issue[2], issue[3], issue[4], returnStatus, fine});


            }
        }
    }


    // Save issue data to file
    private void saveIssueData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ISSUE_DATA_FILE))) {
            out.writeObject(issueList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load issue data from file
    @SuppressWarnings("unchecked")
    private void loadIssueData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ISSUE_DATA_FILE))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                issueList = (List<String[]>) obj;
            } else {
                issueList = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            issueList = new ArrayList<>();
        }
    }

    private boolean isValidUserId(String userId) {
        return User.isValidUserId(userId);
    }

    // Validate date format
    private boolean isValidDate(String date) {
        return Pattern.matches(DATE_PATTERN, date);
    }

    // Validate book name
    private boolean isValidBookName(String bookName) {
        if (Book.bookList == null) {
            return false;
        }
        
        for (String[] book : Book.bookList) {
            if (book[1].equals(bookName)) {
                return true;
            }
        }
        return false;
    }

    public static List<String[]> getIssueList() {
        return issueList;
    }
}
