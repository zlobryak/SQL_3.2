package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.mode.SQLStrings;
import ru.netology.mode.User;
import ru.netology.page.LoginPage;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @AfterEach
    @SneakyThrows
    void cleanUp() {
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
            runner.update(connection, SQLStrings.createTableUsers());
            runner.update(connection, SQLStrings.createTableCards());
            runner.update(connection, SQLStrings.createTableAuthCodes());
            runner.update(connection, SQLStrings.createTableCardTransactions());
        }
    }

    @Test
    @DisplayName("Should login")
    @SneakyThrows
    void loginTest() {
        Configuration.holdBrowserOpen = true;

        var runner = new QueryRunner();
        LoginPage page = new LoginPage();

        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {

            var vasyaId= runner.query(connection, SQLStrings.getUserId(), new BeanHandler<>(User.class));
            runner.update(connection, SQLStrings.insertAuthCode
                    (
                            "1",
                            vasyaId.getId(),
                            "1234"));
        }

        open("http://localhost:9999");
        page.manualValidLogin("vasya", "qwerty123");
    }
}
