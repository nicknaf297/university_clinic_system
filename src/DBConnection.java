import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url =
                "jdbc:sqlserver://localhost:1433;" +
                "databaseName=university_clinic_db;" +
                "encrypt=true;trustServerCertificate=true";

            String user = "ali@student.com";
            String pass = "Pa$$w0rd";

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}