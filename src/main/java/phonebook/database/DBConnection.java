package phonebook.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static Connection connection;

    public static Connection createConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "root_12345";
            String url = "jdbc:mysql://localhost:3306/contactsbook?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
