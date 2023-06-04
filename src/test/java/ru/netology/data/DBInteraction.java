package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.resources.SQLStrings;

import java.sql.DriverManager;

public class DBInteraction {
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
    public static String getUserLogin(){
        return "vasya";
    }
    public static String getUserPassword(){
        return "qwerty123";
    }
    @SneakyThrows
    public static void cleanUpDB(){             //Очищаем таблицы после тестов
        var runner = new QueryRunner();
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {
            runner.update(connection, SQLStrings.truncateTable("auth_codes"));
            runner.update(connection, SQLStrings.truncateTable("card_transactions"));
            runner.update(connection, SQLStrings.truncateTable("cards"));
            runner.update(connection, SQLStrings.foreignKeyOff());
            runner.update(connection, SQLStrings.truncateTable("users"));
            runner.update(connection, SQLStrings.foreignKeyOn());
        }
    }

}
