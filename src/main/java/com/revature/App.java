package com.revature;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(
            new FileInputStream("db.properties")
        );
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String query;

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Scanner sc = new Scanner(System.in)) {
            System.out.println("postgres=> ");
            query = sc.nextLine();
            Statement statement = connection.createStatement();
            boolean isResultSet = statement.execute(query);

            if (isResultSet) {
                ResultSet resultSet = statement.getResultSet();
                ResultSetMetaData rSetMetaData = resultSet.getMetaData();

                while(resultSet.next()) {
                    for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) {
                        System.out.println(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } else {
                System.out.println(statement.getUpdateCount() + " rows affected");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
