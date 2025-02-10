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

            var sql5 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = conn.prepareStatement(sql5)) {
                preparedStatement.setString(1, "User1");
                preparedStatement.setString(2, "123");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "User2");
                preparedStatement.setString(2, "456");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "User3");
                preparedStatement.setString(2, "789");
                preparedStatement.executeUpdate();
            }

            var userOne = new User("Andrey", "000000");
            System.out.println(userOne.getPhone());

            UserDAO.save(userOne);


            System.out.println("--- РЕЗУЛЬТАТ -------------------");
            var sql3 = "SELECT * FROM users";
            try (var statement3 = conn.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("username") + " ");
                    System.out.println(resultSet.getString("phone"));
                }
            }
            System.out.println("-------------------------------------");

        }
    }
}