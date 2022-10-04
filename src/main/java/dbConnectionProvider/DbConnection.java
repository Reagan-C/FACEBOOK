package dbConnectionProvider;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\JavaPractise\\FACEBOOK\\FB.db");

            if (connection != null) {
                System.out.println("connected successfully");
            } else {
                System.out.println("Connection Doomed");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return connection;
    }
}
