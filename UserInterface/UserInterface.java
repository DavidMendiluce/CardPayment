package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.sql.*;


import MySQL.MysqlCon;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class UserInterface {

	private JFrame frame;
	
	

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
	
	public Connection dbConnector() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/java","root","root");  
			 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from employeeinfo");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			
			return con;
			}catch(Exception ex){ System.out.println(ex);  
		    throw new RuntimeException(ex);
			}
			} 
    
	Connection connection = null;
	private JTextField textUser;
	private JPasswordField textPassword;
	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
		connection = dbConnector();
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
		textUser.setBounds(219, 106, 188, 34);
		frame.getContentPane().add(textUser);
		textUser.setColumns(10);
		
	
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					  String query = "select * from employeeinfo where username=? and password=? ";
					  PreparedStatement pst = connection.prepareStatement(query);
					  
					  pst.setString(1, textUser.getText());
					  pst.setString(2, textPassword.getText());
					  
					  ResultSet rs = pst.executeQuery();
					  int count = 0;
					  while(rs.next()) {
						  count++;
					  }
					  if(count == 1) {
							JOptionPane.showMessageDialog(null, "Username and password is correct"); 
							frame.dispose();
							LoginSuccesful logSuccesful = new LoginSuccesful();
							logSuccesful.setVisible(true);
					  } else if (count > 1) {
						  JOptionPane.showMessageDialog(null, "Duplicate username and password is correct");
					  } else {
						  JOptionPane.showMessageDialog(null, "Username and password is not correct");
					  }
					  
				} catch (Exception e)
				{
					
					JOptionPane.showMessageDialog(null, e);
					
				}
				
			}
		});
		btnLogin.setBounds(219, 228, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(219, 170, 188, 34);
		frame.getContentPane().add(textPassword);
		
		JLabel label_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/icons8-customer-52.png")).getImage();
		label_1.setIcon(new ImageIcon(img));
		label_1.setBounds(25, 37, 107, 227);
		frame.getContentPane().add(label_1);
		
		JButton btnNewButton = new JButton("SignUp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				SignUp signUp = new SignUp();
				signUp.setVisible(true);
			}
		});
		btnNewButton.setBounds(318, 228, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
	
		
		
		
		
	}
}
