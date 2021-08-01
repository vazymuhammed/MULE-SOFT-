package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertMovieList
 */
@WebServlet("/InsertMovieList")
public class InsertMovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String DBURL="jdbc:mysql://localhost:3306/movieslist";
		String DBUser="root";
		String DBPwd="";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DBURL,DBUser,DBPwd);
			String movieName=request.getParameter("movieName");
			movieName=movieName.toUpperCase();
			String actorName=request.getParameter("actorName");
			String actressName=request.getParameter("actressName");
			int yearOfRelease=Integer.parseInt(request.getParameter("yearOfRelease"));
			String directorName=request.getParameter("directorName");
			String sql="INSERT INTO moviesdetails VALUES(?,?,?,?,?)";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1,movieName);
			stmt.setString(2,actorName);
			stmt.setString(3,actressName);
			stmt.setInt(4,yearOfRelease);
			stmt.setString(5,directorName);
			int result=stmt.executeUpdate();
			if(result>0){
				out.println("record inserted successfully");
				out.print("<br><br>");
				out.print("<html><body><table border='1'>");
				out.print("<caption>MOVIE TABLE DETAILS</caption>");
				out.print("<tr>");
				out.print("<th>MOVIE NAME</th>");
				out.print("<th>ACTOR NAME</th>");
				out.print("<th>ACTRESS NAME</th>");
				out.print("<th>YEAR OF RELEASE</th>");
				out.print("<th>DIRECTOR NAME</th>");
				out.print("</tr>");
				sql="SELECT * FROM moviesdetails ORDER BY year_of_release";
				ResultSet selectResult=stmt.executeQuery(sql);
				while(selectResult.next()){
					out.print("<tr>");
					out.print("<td>"+selectResult.getString("movieName")+"</td>");
					out.print("<td>"+selectResult.getString("actorName")+"</td>");
					out.print("<td>"+selectResult.getString("actressName")+"</td>");
					out.print("<td>"+selectResult.getInt("year_of_release")+"</td>");
					out.print("<td>"+selectResult.getString("directorName")+"</td>");
					out.print("</tr>");
				}
				out.print("</table>");
				out.print("</body></html>");
			}
			
		}
		catch(Exception e){
			out.println(e.getMessage());
		}
	}

}
