import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/api/appointments")
public class AppointmentApiServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String dateStr = request.getParameter("appointment_date");
        String timeStr = request.getParameter("time");
        
        int userId = 3; //mock user (ali@student.com)
        
        int doctorId = 0;
        try {
            if(request.getParameter("doctor_id") != null) {
                doctorId = Integer.parseInt(request.getParameter("doctor_id"));
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("ERROR: Invalid Doctor Selected.");
            return;
        }

        String url = "jdbc:sqlserver://localhost;databaseName=university_clinic__db;encrypt=true;trustServerCertificate=true;";
        String user = "ali@student.com";
        String password = "Pa$$w0rd";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, password);

            //STORED PROCEDURE - SUBMIT AN APPOINTMENT
            String sql = "{call dbo.sp_SubmitAppointment(?, ?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setDate(1, java.sql.Date.valueOf(dateStr)); // Converts standard YYYY-MM-DD input string
            cstmt.setString(2, timeStr);
            cstmt.setInt(3, userId);
            cstmt.setInt(4, doctorId);

            // Run execution transaction loop
            cstmt.executeUpdate();

            cstmt.close();
            conn.close();

            response.getWriter().write("Appointment Added Successfully via Secure Stored Procedure Call.");

        } catch(Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("DATABASE ERROR: " + e.getMessage());
        }
    }
}