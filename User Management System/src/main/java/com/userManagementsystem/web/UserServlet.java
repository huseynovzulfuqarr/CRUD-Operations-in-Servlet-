package com.userManagementsystem.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.userManagementsystem.dao.UserDao;
import com.userManagementsystem.model.User;
@WebServlet("/user")
public class UserServlet extends HttpServlet{
private UserDao userDao;

public UserServlet(UserDao userDao) {
	this.userDao = userDao;
}
public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	String action=request.getServletPath();
	switch(action) {
	case "/new":
		try {
			showNewForm(request,response);
		} catch (ClassNotFoundException | SQLException | IOException | ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
	case "/insert":
		try {
			insertUser(request,response);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/delete":
		try {
			delete(request,response);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/update":
		try {
			update(request, response);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
	case "/edit":
		try {
			showEditForm(request, response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	default:
		try {
			listUser(request,response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	}
	}
public void listUser(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	List<User> users=this.userDao.getAllUsers();
	request.setAttribute("users",users);
	RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
	dispatcher.forward(request, response);
	
}
private void update(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
	int id=Integer.parseInt(request.getParameter("id"));
	String name=request.getParameter("name");
	String surname=request.getParameter("surname");
	String gmail=request.getParameter("gmail");
	String country=request.getParameter("country");
	User user=new User(name, surname, country, gmail);
	this.userDao.updateUser(user);
	response.sendRedirect("list");
	
}
private void delete(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
	int id=Integer.parseInt(request.getParameter("id"));
	this.userDao.deleteUserByid(id);
	response.sendRedirect("list");
	
}
private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
	int id=Integer.parseInt(request.getParameter("id"));
	User existUser=userDao.getUserById(id);
	RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
	request.setAttribute("user", existUser);
	dispatcher.forward(request, response);
}
	private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
	}
private void insertUser(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
	String name=request.getParameter("name");
	String surname=request.getParameter("surname");
	String gmail=request.getParameter("gmail");
	String country=request.getParameter("country");
	User user=new User(name, surname, country, gmail);
	this.userDao.insertUser(user);
	response.sendRedirect("list");
}
	
	
	
}
