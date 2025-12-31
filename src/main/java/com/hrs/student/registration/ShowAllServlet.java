package com.hrs.student.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/show_all")
public class ShowAllServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><style>");
        out.println("body { font-family: -apple-system, sans-serif; padding: 40px; display: flex; flex-direction: column; align-items: center; }");
        out.println(".wrap { width: 100%; max-width: 700px; border: 1px solid #e4e4e7; border-radius: 8px; overflow: hidden; }");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th { background: #f4f4f5; text-align: left; padding: 12px; font-size: 14px; color: #71717a; border-bottom: 1px solid #e4e4e7; }");
        out.println("td { padding: 12px; border-bottom: 1px solid #e4e4e7; font-size: 14px; }");
        // Added styling for the remove button
        out.println(".btn-remove { background: #fee2e2; color: #ef4444; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-size: 12px; font-weight: 600; }");
        out.println(".btn-remove:hover { background: #fecaca; }");
        out.println(".head { display: flex; justify-content: space-between; width: 700px; margin-bottom: 20px; }");
        out.println("</style></head><body>");
        
        out.println("<div class='head'><h2>Student List</h2><a href='index.html' style='text-decoration:none; color:#18181b; font-weight:500;'>+ New</a></div>");
        out.println("<div class='wrap'><table><thead><tr><th>Name</th><th>Email</th><th>Year</th><th style='text-align:right;'>Actions</th></tr></thead><tbody>");

        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id"); // Assuming your column name is 'id'
                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getInt("year") + "</td>");
                
                // Add the Remove Button in a form
                out.println("<td style='text-align:right;'>");
                out.println("<form action='delete_student' method='POST' style='margin:0;' onsubmit='return confirm(\"Are you sure?\");'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit' class='btn-remove'>Remove</button>");
                out.println("</form>");
                out.println("</td>");
                
                out.println("</tr>");
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        out.println("</tbody></table></div></body></html>");
    }
}