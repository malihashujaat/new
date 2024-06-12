package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Mybook {
    public JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mybook window = new Mybook();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Mybook() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Issued Book");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(172, 7, 85, 19);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("These are the Books you Issued:");
        lblNewLabel_1.setBounds(10, 37, 251, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton = new JButton("<");
        btnNewButton.setBounds(10, 7, 46, 23);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // Assuming userpage is where you navigate back after viewing issued books
                userpage window = new userpage();
                window.frame.setVisible(true);
            }
        });

        tableModel = new DefaultTableModel(new Object[]{"Issue ID", "Book Name", "Issue Date", "Return Date"}, 0);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 49, 414, 201);
        frame.getContentPane().add(scrollPane);

        // Populate the table with issue data
        if (Issue.issueList != null) {
            for (String[] issue : Issue.issueList) {
                if ("".equals(issue[5])) {  // Check if the return status is "No"
                    tableModel.addRow(new Object[]{issue[0], issue[1], issue[2], issue[3]});
                }
            }
        }
    }
}
