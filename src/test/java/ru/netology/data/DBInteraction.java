package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.DriverManager;
import java.util.List;

public class DBInteraction {
    public static String truncateTable(String tableName){
        return "TRUNCATE TABLE app." + tableName +";";
    }

    public static String getAuthCode (String userId){
        return "SELECT code FROM auth_codes WHERE user_id = '" + userId + "' ORDER BY created DESC LIMIT 1;";
    }
    @SneakyThrows
    public static String getUserId() {
        var runner = new QueryRunner();
        DataHelper.AuthCode id;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            id = runner.query
                    (
                            connection,
                            "SELECT id FROM users WHERE login = 'vasya';",
                            new BeanHandler<>(DataHelper.AuthCode.class)
                    );
        }

        return id.getId();
    }

    @SneakyThrows
    public static void cleanUpDB(){             //Очищаем таблицы после тестов
        var runner = new QueryRunner();
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {
            runner.update(connection, truncateTable("auth_codes"));
            runner.update(connection, truncateTable("card_transactions"));
            runner.update(connection, truncateTable("cards"));
            runner.update(connection, "SET FOREIGN_KEY_CHECKS = 0;");
            runner.update(connection, truncateTable("users"));
            runner.update(connection, "SET FOREIGN_KEY_CHECKS = 1;");
        }
    }

    @SneakyThrows
    public static String getAuthCode() {
        var runner = new QueryRunner();

        List<DataHelper.AuthCode> authCode;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {

            authCode = runner.query(connection, getAuthCode(getUserId()), new BeanListHandler<>(DataHelper.AuthCode.class));

        }
        return String.valueOf(authCode.get(authCode.size() - 1));

    }
}
