package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField name1;
	private JTextField surName1;
	private JTextField age1;
	private JTextField password1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
	private JTextField userName1;
	private JTextField eID;
	
	public SignUp() {
		intialize();
		connection = dbConnector();
	}
	
	public void intialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 356);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(86, 114, 60, 22);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(86, 147, 60, 22);
		contentPane.add(lblSurname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(86, 180, 60, 22);
		contentPane.add(lblAge);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(86, 248, 89, 22);
		contentPane.add(lblPassword);
		
		name1 = new JTextField();
		name1.setBounds(165, 116, 122, 20);
		contentPane.add(name1);
		name1.setColumns(10);
		
		surName1 = new JTextField();
		surName1.setBounds(165, 150, 122, 20);
		contentPane.add(surName1);
		surName1.setColumns(10);
		
		age1 = new JTextField();
		age1.setBounds(165, 183, 122, 20);
		contentPane.add(age1);
		age1.setColumns(10);
		
		password1 = new JTextField();
		password1.setBounds(165, 251, 122, 20);
		contentPane.add(password1);
		password1.setColumns(10);
		
		JLabel lblSignUp = new JLabel("SignUp");
		lblSignUp.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblSignUp.setBounds(123, 11, 103, 38);
		contentPane.add(lblSignUp);
		
		JButton btnDone = new JButton("Done!");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "insert into employeeinfo  (EID, name, surname, age, username, password) values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, eID.getText());
					pst.setString(2, name1.getText());
					pst.setString(3, surName1.getText());
					pst.setString(4, age1.getText());
					pst.setString(5, userName1.getText());
					pst.setString(6, password1.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnDone.setBounds(180, 283, 89, 23);
		contentPane.add(btnDone);
		
		JLabel usernamet = new JLabel("Username");
		usernamet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernamet.setBounds(86, 213, 69, 24);
		contentPane.add(usernamet);
		
		userName1 = new JTextField();
		userName1.setBounds(165, 217, 122, 20);
		contentPane.add(userName1);
		userName1.setColumns(10);
		
		JLabel lblEid = new JLabel("EID");
		lblEid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEid.setBounds(86, 81, 60, 22);
		contentPane.add(lblEid);
		
		eID = new JTextField();
		eID.setBounds(165, 84, 122, 20);
		contentPane.add(eID);
		eID.setColumns(10);
		
		JButton btnNewButton = new JButton("LogIn");
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 283, 69, 23);
		contentPane.add(btnNewButton);
	}
}
