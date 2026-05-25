import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/api/doctors")
public class DoctorApiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String url = "jdbc:sqlserver://localhost;databaseName=university_clinic__db;encrypt=true;trustServerCertificate=true;";
        String user = "ali@student.com";
        String password = "Pa$$w0rd";

        StringBuilder json = new StringBuilder();
        json.append("[");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, password);

            //USE STORED PROCEDURE - Only See Doctor Names
            String sql = "{call dbo.sp_GetDoctorCatalog}";
            CallableStatement cstmt = conn.prepareCall(sql);
            ResultSet rs = cstmt.executeQuery();

            boolean first = true;
            while(rs.next()) {
                if(!first) json.append(",");
                first = false;

                json.append("{")
                    .append("\"id\":").append(rs.getInt("id")).append(",")
                    .append("\"name\":\"").append(rs.getString("name")).append("\"")
                    .append("}");
            }

            json.append("]");
            
            cstmt.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
            // Formats any remaining database trace messages cleanly for the JSON collector
            json = new StringBuilder("[{\"error\":\"" + e.getMessage().replace("\"", "\\\"") + "\"}]");
        }

        response.getWriter().write(json.toString());
    }
}