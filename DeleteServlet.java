package com.sahil.userManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet
{
	private final static String query="delete from user where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//get PrintWriter
		PrintWriter out=resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//link the bootstrap
		out.print("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//get the values
		int id=Integer.parseInt(req.getParameter("id"));
		//create the jdbc connection
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt?user=root&password=Sahil@aman152");
			//create statement
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			//set the values in database
			int count=psmt.executeUpdate();
			out.println("<div class='card' style='margin:auto; width:300px; margin-top:100px'>");
			if(count>0)
			{
				out.print("<h3 class='bg-danger text-light text-center'>Record Deleted sucessfully</h3>");
			}
			else
			{
				out.print("<h3 class='bg-danger text-light text-center'>Record not Deleted </h3>");
			}
		}
		catch(Exception e)
		{
			out.print("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		out.print("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		out.print("&nbsp;");
		out.print("<a href='showData'><button class='btn btn-outline-success'>Show User</button></a>");
		out.println("</div>");
		//close the stream
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req,resp);
	}
}
