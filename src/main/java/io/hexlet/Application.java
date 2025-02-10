package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        // Соединение с базой данных тоже нужно отслеживать
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (var statement = conn.createStatement()) {
                statement.execute(sql);
            }

            var sql2 = "INSERT INTO users (username, phone) VALUES ('tom111my', '12222222'), ('rrrrr', '88888')";
            try (var statement2 = conn.createStatement()) {
                statement2.executeUpdate(sql2);
            }


            var sql5 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = conn.prepareStatement(sql5)) {
                preparedStatement.setString(1, "Request user1");
                preparedStatement.setString(2, "1234567890");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Request user 222");
                preparedStatement.setString(2, "00000");
                preparedStatement.executeUpdate();
            }

            var sql6 = "DELETE FROM users WHERE phone = ?";
            try (var preparedStatement = conn.prepareStatement(sql6)) {
                preparedStatement.setString(1, "88888");
                preparedStatement.executeUpdate();

            }

            System.out.println("--- РЕЗУЛЬТАТ ----------");
            var sql3 = "SELECT * FROM users";
            try (var statement3 = conn.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
            System.out.println("----------------------");

        }
    }
}