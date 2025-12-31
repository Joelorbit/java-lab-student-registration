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
        String yearStr = request.getParameter("year");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int year = Integer.parseInt(yearStr);

            // 1. Validation: Year must be 2023 or later
            if (year < 2023) {
                renderError(out, "Invalid Year", "Registration is only open for years 2023 and onwards.", "#f59e0b", "#78350f", "#fffbeb");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                // 2. Check if Email exists
                PreparedStatement check = conn.prepareStatement("SELECT id FROM students WHERE email = ?");
                check.setString(1, email);
                ResultSet rs = check.executeQuery();

                if (rs.next()) {
                    // Duplicate Found
                    renderError(out, "User already exists", "The email <b>"+email+"</b> is already registered.", "#ef4444", "#991b1b", "#fef2f2");
                } else {
                    // 3. No duplicate and valid year - Save it
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, email, year) VALUES (?, ?, ?)");
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setInt(3, year);
                    ps.executeUpdate();
                    response.sendRedirect("show_all");
                }
            }
        } catch (NumberFormatException e) {
            renderError(out, "Input Error", "Please enter a valid numeric year.", "#ef4444", "#991b1b", "#fef2f2");
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    // Helper method to keep your code clean and dry (Don't Repeat Yourself)
    private void renderError(PrintWriter out, String title, String msg, String border, String text, String bg) {
        out.println("<html><body style='font-family:sans-serif; display:flex; justify-content:center; align-items:center; height:100vh;'>");
        out.println("<div style='border:1px solid "+border+"; padding:20px; border-radius:8px; background:"+bg+"; text-align:center;'>");
        out.println("<h3 style='color:"+text+"; margin:0;'>"+title+"</h3>");
        out.println("<p style='color:"+text+";'>"+msg+"</p>");
        out.println("<a href='index.html' style='color:#18181b; font-weight:bold;'>Go Back</a>");
        out.println("</div></body></html>");
    }
}