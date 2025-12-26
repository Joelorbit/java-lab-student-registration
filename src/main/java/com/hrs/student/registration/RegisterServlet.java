package com.hrs.student.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String year = request.getParameter("year");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DBConnection.getConnection()) {
            // Check if Email exists
            PreparedStatement check = conn.prepareStatement("SELECT id FROM students WHERE email = ?");
            check.setString(1, email);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                // Duplicate Found - Modern Error UI
                out.println("<html><body style='font-family:sans-serif; display:flex; justify-content:center; align-items:center; height:100vh;'>");
                out.println("<div style='border:1px solid #ef4444; padding:20px; border-radius:8px; background:#fef2f2; text-align:center;'>");
                out.println("<h3 style='color:#991b1b; margin:0;'>User already exists</h3>");
                out.println("<p style='color:#b91c1c;'>The email <b>"+email+"</b> is already registered.</p>");
                out.println("<a href='index.html' style='color:#18181b; font-weight:bold;'>Go Back</a>");
                out.println("</div></body></html>");
            } else {
                // No duplicate - BOOM Save it
                PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, email, year) VALUES (?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setInt(3, Integer.parseInt(year));
                ps.executeUpdate();
                response.sendRedirect("show_all");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}