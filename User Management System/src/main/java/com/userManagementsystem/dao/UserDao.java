package com.userManagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userManagementsystem.model.User;

public class UserDao {
private String url="jdbc:mysql://localhost:3306/demo";
private String username="root";
private String password="01092003Zh";
private static final String insert_user_sql="INSERT INTO users "+" (name,surname,country,gmail)"
		+ "VALUES "+"(?,?,?,?)";
private static final String select_user_by_id="select name,surname,country,gmail where id=?";
private static final String getAllUsers="select *from users";
private static final String deleteById="delete from users where id=? ";
private static final String updateUsersById="update users set name=?,surname=?,country=?,gmail=? where id=?";
public Connection getConnection() throws ClassNotFoundException, SQLException {
	Connection con=null;
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection(url,username,password);
	return con;
}
public void insertUser(User user) throws ClassNotFoundException, SQLException {
	Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement(insert_user_sql);
	stmt.setString(1, user.getName());
	stmt.setString(2, user.getSurname());
	stmt.setString(3, user.getCountry());
	stmt.setString(4, user.getGmail());
	stmt.executeUpdate();
	}
public boolean updateUser(User user) throws ClassNotFoundException, SQLException {
	boolean isUpdated;
	Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement(updateUsersById);
	stmt.setString(1, user.getName());
	stmt.setString(2, user.getSurname());
	stmt.setString(3, user.getCountry());
	stmt.setString(4, user.getGmail());
	isUpdated=stmt.executeUpdate()>0;
	return isUpdated;
}
public User getUserById(int id) throws ClassNotFoundException, SQLException {
	User user=null;
	Connection con =getConnection();
	PreparedStatement stmt=con.prepareStatement(select_user_by_id);
	stmt.setInt(1, id);
	System.out.println(stmt);
	ResultSet rs=stmt.executeQuery();
	while(rs.next()) {
		String name=rs.getString("name");
		String surname=rs.getString("surname");
		String gmail=rs.getString("gmail");
		String country=rs.getString("country");
	user=new User(name,surname,gmail,country);
	}
	
	return user;
}
public List<User> getAllUsers() throws ClassNotFoundException, SQLException{
	List<User> users=new ArrayList();
	Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement(getAllUsers);
	ResultSet rs=stmt.executeQuery();
	while(rs.next()) {
		int id=rs.getInt("id");
		String name=rs.getString("name");
		String surname=rs.getString("surname");
		String gmail=rs.getString("gmail");
		String country=rs.getString("country");
		users.add(new User(id, name, surname, country, gmail));
	}
	return users;
}
public boolean deleteUserByid(int id) throws ClassNotFoundException, SQLException {
	boolean isDeleted;
	Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement(deleteById);
	stmt.setInt(1, id);
	isDeleted=stmt.executeUpdate()>0;
	return isDeleted;
}





}
