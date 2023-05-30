package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.mode.SQLStrings;
import ru.netology.mode.User;
import ru.netology.page.LoginPage;

import java.sql.DriverManager;

public class LoginTest {
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
            runner.update(connection, SQLStrings.auth_codesDrop());
            runner.update(connection, SQLStrings.card_transactionsDrop());
            runner.update(connection, SQLStrings.cardsDrop());
            runner.update(connection, SQLStrings.usersDrop());
            runner.update(connection, SQLStrings.createTableUsers());
            runner.update(connection, SQLStrings.createTableCards());
            runner.update(connection, SQLStrings.createTableAuthCodes());
            runner.update(connection, SQLStrings.createTableCardTransactions());
            runner.update(connection, dataSQL, 1, faker.name().username(), "pass");
            runner.update(connection, dataSQL, 2, faker.name().username(), "pass");
        }
    }
    @AfterEach
    @SneakyThrows
    void cleanUp(){
        var runner = new QueryRunner();
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {
            runner.update(connection, SQLStrings.auth_codesDrop());
            runner.update(connection, SQLStrings.card_transactionsDrop());
            runner.update(connection, SQLStrings.cardsDrop());
            runner.update(connection, SQLStrings.usersDrop());
        }
    }

    @Test
    @DisplayName("Should login")
    void loginTest() {
        User user = new User("111-xxx-234-test-id", "TestUser", "TestPassword");
        LoginPage page = new LoginPage();

    }
}
