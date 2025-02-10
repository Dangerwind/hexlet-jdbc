package io.hexlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
// Тут еще импорт класса User

public class UserDAO {
    private static Connection connection;

    public UserDAO(Connection conn) {
        connection = conn;
    }

    public static void save(User user) throws SQLException {
        // Если пользователь новый, выполняем вставку
        // Иначе обновляем
        if (user.getId() == null) {
            var sql = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPhone());
                preparedStatement.executeUpdate();
                var generatedKeys = preparedStatement.getGeneratedKeys();
                // Если идентификатор сгенерирован, извлекаем его и добавляем в сохраненный объект
                if (generatedKeys.next()) {
                    // Обязательно устанавливаем id в сохраненный объект
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving an entity");
                }
            }
        } else {
            // Здесь код обновления существующей записи
        }
    }
    }