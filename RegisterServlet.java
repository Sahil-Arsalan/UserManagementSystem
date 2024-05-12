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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
	private final static String query="insert into user(name,email,mobile,dob,gender,city) values(?,?,?,?,?,?)";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//get PrintWriter
		PrintWriter out=resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//link the bootstrap
		out.print("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//get the values
		String name=req.getParameter("userName");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String dob=req.getParameter("dob");
		String gender=req.getParameter("gender");
		String city=req.getParameter("city");
		//create the jdbc connection
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt?user=root&password=Sahil@aman152");
			//create statement
			PreparedStatement psmt=con.prepareStatement(query);
			//set the values in database
			psmt.setString(1, name);
			psmt.setString(2, email);
			psmt.setString(3, mobile);
			psmt.setString(4, dob);
			psmt.setString(5, gender);
			psmt.setString(6, city);
			
			int count=psmt.executeUpdate();
			out.println("<div class='card' style='margin:auto; width:300px; margin-top:100px'>");
			if(count>0)
			{
				out.print("<h3 class='bg-danger text-light text-center'>Record Registered sucessfully</h3>");
			}
			else
			{
				out.print("<h3 class='bg-danger text-light text-center'>Record not Registered </h3>");
			}
		}
		catch(Exception e)
		{
			out.print("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		out.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
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
