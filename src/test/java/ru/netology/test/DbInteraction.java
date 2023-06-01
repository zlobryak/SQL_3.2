package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.mode.SQLStrings;
import ru.netology.mode.User;

import java.sql.DriverManager;

public class DbInteraction {
    @BeforeEach
    @SneakyThrows
    void setUp() {
        var faker = new Faker();
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";

        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {
//            runner.update(connection, SQLStrings.auth_codesDrop());
//            runner.update(connection, SQLStrings.card_transactionsDrop());
//            runner.update(connection, SQLStrings.cardsDrop());
//            runner.update(connection, SQLStrings.usersDrop());
//            runner.update(connection, SQLStrings.createTableUsers());
//            runner.update(connection, SQLStrings.createTableCards());
//            runner.update(connection, SQLStrings.createTableAuthCodes());
//            runner.update(connection, SQLStrings.createTableCardTransactions());
            runner.update(connection, dataSQL, 6, faker.name().username(), "pass");
        }
    }

    @Test
    @SneakyThrows
    void stubTest() {
        var countSQL = "SELECT COUNT(*) FROM users;";
        var usersSQL = "SELECT * FROM users;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            var count = runner.query(conn, countSQL, new ScalarHandler<>());
            System.out.println(count);
            var first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
            System.out.println(first);
            var all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
            System.out.println(all);
        }
    }
}