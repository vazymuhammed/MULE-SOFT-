package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetMovieDetails")
public class GetMovieDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String DBURL="jdbc:mysql://localhost:3306/movieslist";
		String DBUser="root";
		String DBPwd="";
		String input=request.getParameter("col_name");
		String value=request.getParameter("field_value");
		int year=0;
		String sql;
		String field="";
		switch(input){
		   case "all":
			     field="";
			     break;
		   case "actor":
			     field="actorName";
			     break;
		   case "actress":
			     field="actressName";
			     break;
		   case "year":
			     field="year_of_release";
			     year=Integer.parseInt(value);
			     break;
		   case "director":
			     field="directorName";
			     break;
		}
		if(input.equals("all")){
			sql="SELECT * FROM moviesdetails";
		}
		else if(input.equals("year")){
			sql="SELECT * FROM moviesdetails WHERE "+field+"="+year+"";
		}
		else
		{
			sql="SELECT * FROM moviesdetails WHERE "+field+"='"+value+"' ";
		}
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DBURL,DBUser,DBPwd);
			Statement stmt=conn.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			out.print("<html><body><table border='1'>");
			out.print("<caption>MOVIE TABLE DETAILS</caption>");
			out.print("<tr>");
			out.print("<th>MOVIE NAME</th>");
			out.print("<th>ACTOR NAME</th>");
			out.print("<th>ACTRESS NAME</th>");
			out.print("<th>YEAR OF RELEASE</th>");
			out.print("<th>DIRECTOR NAME</th>");
			out.print("</tr>");
			while(result.next()){
				out.print("<tr>");
				out.print("<td>"+result.getString("movieName")+"</td>");
				out.print("<td>"+result.getString("actorName")+"</td>");
				out.print("<td>"+result.getString("actressName")+"</td>");
				out.print("<td>"+result.getInt("year_of_release")+"</td>");
				out.print("<td>"+result.getString("directorName")+"</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			out.print("</body></html>");
		}
		catch(Exception e){
			out.println(e.getMessage());
		}
	}

}
