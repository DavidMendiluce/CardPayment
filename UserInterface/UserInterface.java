package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;

import MySQL.MysqlCon;

import javax.swing.JLabel;

public class UserInterface {

	private JFrame frame;
	private JTextField answer;
	private JTextField textUser;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
		connection = MysqlCon.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frame.setBounds(100, 100, 515, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(237, 106, 46, 14);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(142, 114, 67, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(142, 182, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textUser = new JTextField();
		textUser.setBounds(219, 106, 115, 34);
		frame.getContentPane().add(textUser);
		textUser.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(219, 172, 115, 34);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					  String query = "SELECT * FROM EmployeeInfo WHERE username = ? "
					  		+ "and password = ? ";
					  java.sql.PreparedStatement pst = connection.prepareStatement(query);
					  pst.setString(0, textUser.getText());
					  pst.setString(1, textPassword.getText());
					  
					  ResultSet rs = pst.executeQuery();
					  
				} catch (Exception e)
				{
					
					JOptionPane.showMessageDialog(null, e);
					
				}
			}
		});
		btnLogin.setBounds(219, 241, 89, 23);
		frame.getContentPane().add(btnLogin);
		
	
		
		
		
		
	}
}
