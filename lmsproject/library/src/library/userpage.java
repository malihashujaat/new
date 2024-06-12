package library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userpage {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userpage window = new userpage();
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
	public userpage() {
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
		lblNewLabel.setBounds(149, 7, 130, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setBounds(10, 21, 41, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loginpage window = new loginpage();
				window.frame.setVisible(true);;
              
            }
		});
		
		
		JButton btnNewButton_1 = new JButton("View Book");
		btnNewButton_1.setBounds(51, 102, 99, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ViewBook window = new ViewBook();
				window.frame.setVisible(true);
              
            }
		});
		
		JButton btnNewButton_2 = new JButton("Issue Book");
		btnNewButton_2.setBounds(226, 102, 99, 23);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
				Mybook window = new Mybook();
				window.frame.setVisible(true);
              
            }
		});
		
		JLabel lblNewLabel_1 = new JLabel("User Function:");
		lblNewLabel_1.setBounds(51, 68, 89, 23);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
