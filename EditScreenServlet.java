package com.sahil.userManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet
{
	private final static String query = "select name,email,mobile,dob,gender,city from user where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter out = res.getWriter();
        //set content type
        res.setContentType("text/html");
        //link the bootstrap
        out.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        //get the id
        int id=Integer.parseInt(req.getParameter("id"));
        //load the JDBC driver
        try
        {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt?user=root&password=Sahil@aman152");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            //resultSet
            ResultSet rs = ps.executeQuery();
            rs.next();
            out.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
            out.println("<form action='edit?id="+id+"' method='post'>");
            out.println("<table class='table table-hover table-striped'>");
            out.println("<tr>");
            out.println("<td>Name</td>");
            out.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Email</td>");
            out.println("<td><input type='email' name='email' value='"+rs.getString(2)+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Mobile</td>");
            out.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Dob</td>");
            out.println("<td><input type='date' name='dob' value='"+rs.getString(4)+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Gender</td>");
            out.println("<td><input type='text' name='gender' value='"+rs.getString(5)+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>City</td>");
            out.println("<td><input type='text' name='city' value='"+rs.getString(6)+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
            out.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
        }
        catch(Exception e) {
            e.printStackTrace();
            out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
        }
        out.println("<button class='btn btn-outline-success d-block'><a href='home.html'>Home</a></button>");
        out.println("</div>");
        //close the stream
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }
}
