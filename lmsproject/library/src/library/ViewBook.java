package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ViewBook {
    public JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private static final String BOOK_DATA_FILE = "books.dat";
    private List<String[]> bookList;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewBook window = new ViewBook();
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
    public ViewBook() {
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

        JLabel lblNewLabel = new JLabel("View Book");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(152, 11, 104, 19);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("These are the books which are available in the Library:");
        lblNewLabel_1.setBounds(10, 37, 336, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton = new JButton("<");
        btnNewButton.setBounds(10, 7, 46, 23);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userpage window = new userpage();
                window.frame.setVisible(true);
            }
        });

        tableModel = new DefaultTableModel(new Object[]{"Book ID", "Book Name", "Genre", "Price"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 49, 414, 201);
        frame.getContentPane().add(scrollPane);

        // Load book data from file
        loadBookData();

        // Populate the table with book data from the bookList
        if (bookList != null) {
            for (String[] book : bookList) {
                tableModel.addRow(book);
            }
        }
    }

    // Load book data from file
    @SuppressWarnings("unchecked")
    private void loadBookData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(BOOK_DATA_FILE))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                bookList = (List<String[]>) obj;
            } else {
                bookList = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            bookList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
