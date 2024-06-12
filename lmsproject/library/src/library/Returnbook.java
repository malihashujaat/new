package library;

import java.awt.EventQueue;
import java.io.*;
import java.util.List;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Returnbook {

    public JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private List<String[]> issueList;
    private static final String ISSUE_DATA_FILE = "issues.dat";
    private static final int FINE_PER_DAY = 100;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Returnbook window = new Returnbook();
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
    public Returnbook() {
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

        loadIssueData(); // Load issue data from file at the beginning

        JLabel lblNewLabel = new JLabel("Return Book");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(169, 11, 89, 21);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("<");
        btnNewButton.setBounds(10, 12, 46, 23);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                admin window = new admin();
                window.frame.setVisible(true);
            }
        });

        JButton btnNewButton_1 = new JButton("Return");
        btnNewButton_1.setBounds(269, 178, 89, 23);
        frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookId = textField.getText();
                String returnDate = textField_1.getText(); // Get the return date entered by the user

                if (isBookIdValid(bookId)) {
                    // Get the issue details for the book
                    String[] issueDetails = getIssueDetails(bookId);
                    if (issueDetails != null) {
                        // Compare the return dates
                        if (isReturnDateValid(returnDate, issueDetails[3])) {
                            JOptionPane.showMessageDialog(frame, "Book returned successfully!");
                            // Update the return status to "Yes"
                            issueDetails[5] = "Yes";
                            issueDetails[6] = "0"; // No fine
                            // Save the updated issue data to file
                            saveIssueData();
                        } else {
                            // Calculate the fine
                            int fine = calculateFine(returnDate, issueDetails[3]);
                            JOptionPane.showMessageDialog(frame, "Book returned late! Fine: Rs " + fine);
                            // Update the return status to "Yes" and record the fine
                            issueDetails[5] = "Yes";
                            issueDetails[6] = String.valueOf(fine);
                            // Save the updated issue data to file
                            saveIssueData();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Issue details not found for Book ID: " + bookId, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Book ID not found. Please enter a valid Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        textField = new JTextField();
        textField.setBounds(126, 86, 124, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Enter ID of the Book you want to Return");
        lblNewLabel_1.setBounds(38, 46, 220, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Issue ID");
        lblNewLabel_2.setBounds(40, 89, 46, 14);
        frame.getContentPane().add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBounds(126, 117, 124, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Date of Return");
        lblNewLabel_3.setBounds(40, 120, 82, 14);
        frame.getContentPane().add(lblNewLabel_3);
    }

    // Load issue data from file
    @SuppressWarnings("unchecked")
    private void loadIssueData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ISSUE_DATA_FILE))) {
            issueList = (List<String[]>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            issueList = null;
        }
    }

    // Check if the Book ID is valid
    private boolean isBookIdValid(String bookId) {
        if (issueList != null) {
            for (String[] issue : issueList) {
                if (issue[0].equals(bookId)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Get issue details for a specific book ID
    private String[] getIssueDetails(String bookId) {
        if (issueList != null) {
            for (String[] issue : issueList) {
                if (issue[0].equals(bookId)) {
                    return issue;
                }
            }
        }
        return null;
    }

    // Check if the return date entered by the user is valid
    private boolean isReturnDateValid(String enteredReturnDate, String actualReturnDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date enteredDate = dateFormat.parse(enteredReturnDate);
            Date actualDate = dateFormat.parse(actualReturnDate);
            return enteredDate.compareTo(actualDate) <= 0; // Return true if entered date is before or equal to actual date
        } catch (ParseException e) {
            return false; // Return false if parsing fails
        }
    }

    // Calculate the fine based on the return date
    private int calculateFine(String returnDate, String actualReturnDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date enteredDate = dateFormat.parse(returnDate);
            Date actualDate = dateFormat.parse(actualReturnDate);
            long diffInMillies = enteredDate.getTime() - actualDate.getTime();
            long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
            int fine = (int) diffInDays * FINE_PER_DAY;
            return fine;
        } catch (ParseException e) {
            return 0; // Return 0 if parsing fails
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
}
