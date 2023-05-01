package config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection connection;
    public static Connection getConnection() {
        Dotenv DOTENV = Dotenv.load();
        String JDBCURL = "jdbc:postgresql://localhost:" + DOTENV.get("PORT") + "/school_db";
        String USERNAME = "postgres";
        String PASSWORD = DOTENV.get("PG_SECRET");
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
                System.out.printf("Database connected on: %s \n\n", DOTENV.get("PORT"));
            }
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
        }
        return connection;
    }
}


