package UserInterface;

	import java.sql.*;  
	import javax.swing.*;

	public class MysqlCon {
		public static Connection dbConnector() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/java","root","root");  
			 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from employeeinfo");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			return con;
			}catch(Exception e){ System.out.println(e);}  
		    return null;
			} 
	}

