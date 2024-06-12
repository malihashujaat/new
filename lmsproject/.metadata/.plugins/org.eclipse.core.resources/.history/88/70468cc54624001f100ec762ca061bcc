package library;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class admin {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin window = new admin();
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
	public admin() {
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
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel.setBounds(150, 11, 129, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("User");
		btnNewButton.setBounds(45, 78, 122, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the admin page
                User window = new User();
                window.frame.setVisible(true); // Open the user view page
            }
		});
		JButton btnNewButton_1 = new JButton("Book");
		btnNewButton_1.setBounds(217, 78, 122, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the admin page
                Book window = new Book();
				window.frame.setVisible(true);
            }
		});
		
		JButton btnNewButton_2 = new JButton("Issue");
		btnNewButton_2.setBounds(217, 130, 122, 23);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the admin page
                Issue window = new Issue();
				window.frame.setVisible(true);
           
            }
		});
		JButton btnNewButton_3 = new JButton("Return Book");
		btnNewButton_3.setBounds(45, 130, 122, 23);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Returnbook window = new Returnbook();
				window.frame.setVisible(true);
              
            }
		});
		
		JButton btnNewButton_5 = new JButton("<");
		btnNewButton_5.setBounds(10, 11, 41, 23);
		frame.getContentPane().add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loginpage window = new loginpage();
				window.frame.setVisible(true);;
              
            }
		});
		
		
		JLabel lblNewLabel_1 = new JLabel("Admin Function:");
		lblNewLabel_1.setBounds(45, 53, 107, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}

}
