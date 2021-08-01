package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class MoviesDatabaseCreation
 */
@WebServlet("/MoviesDatabaseCreation")
public class MoviesDatabaseCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		String DBURL="jdbc:mysql://localhost:3306/";
		String DBUser="root";
		String DBPwd="";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DBURL,DBUser,DBPwd);
			Statement stmt=conn.createStatement();
			String sql="CREATE DATABASE movieslist";
			stmt.executeUpdate(sql);
			out.println("Database created successfully");
			sql="USE movieslist";
			stmt.executeUpdate(sql);
			sql="CREATE TABLE moviesdetails(movieName varchar(200),actorName varchar(200),actressName varchar(200),year_of_release int(4),directorName varchar(200))";
			stmt.executeUpdate(sql);
			out.println("moviesdetails Table Created Successfully");
		}
		catch(Exception e){
			out.println(e.getMessage());
		}
	}
}
