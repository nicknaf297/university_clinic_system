import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {

    public static void main(String[] args) {

        String url =
            "jdbc:sqlserver://localhost;" +
            "databaseName=university_clinic__db;" +
            "encrypt=true;" +
            "trustServerCertificate=true";

        String user = "ali@student.com";
        String password = "Pa$$w0rd";

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn =
                DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Database connected successfully!");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}