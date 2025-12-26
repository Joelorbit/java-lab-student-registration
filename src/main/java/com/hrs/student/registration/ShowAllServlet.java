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
        out.println(".wrap { width: 100%; max-width: 600px; border: 1px solid #e4e4e7; border-radius: 8px; overflow: hidden; }");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th { background: #f4f4f5; text-align: left; padding: 12px; font-size: 14px; color: #71717a; border-bottom: 1px solid #e4e4e7; }");
        out.println("td { padding: 12px; border-bottom: 1px solid #e4e4e7; font-size: 14px; }");
        out.println(".head { display: flex; justify-content: space-between; width: 600px; margin-bottom: 20px; }");
        out.println("</style></head><body>");
        out.println("<div class='head'><h2>Student List</h2><a href='index.html' style='text-decoration:none; color:#18181b; font-weight:500;'>+ New</a></div>");
        out.println("<div class='wrap'><table><thead><tr><th>Name</th><th>Email</th><th>Year</th></tr></thead><tbody>");

        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                out.println("<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getInt("year")+"</td></tr>");
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        out.println("</tbody></table></div></body></html>");
    }
}