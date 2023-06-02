package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String login = "vasya";
    private String password = "qwerty123";
    @SneakyThrows
    public static String getUserId() {
        var runner = new QueryRunner();
        AuthCode id;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            id = runner.query(connection, SQLStrings.getUserId(), new BeanHandler<>(AuthCode.class));
        }

        return id.getId();
    }
}



