package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.User;
import ru.netology.page.AuthCodePage;
import ru.netology.page.LoginPage;
import ru.netology.data.SQLStrings;

import static com.codeborne.selenide.Selenide.open;

import org.apache.commons.dbutils.QueryRunner;
import java.sql.DriverManager;

public class LoginTest {
    @AfterAll
    @SneakyThrows
    static void cleanUp() {
        var runner = new QueryRunner();
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {
            //Очищаем таблицы после тестов
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


        LoginPage loginPage = new LoginPage();
        AuthCodePage authCodePage = new AuthCodePage();
        User user = new User();


        open("http://localhost:9999");
        loginPage.manualValidLogin(user.getLogin(), user.getPassword()); //Водит заранее заданные логин и парольи проверяет открытие страницы ввода проверочного кода
        var code = authCodePage.getAuthCode(); //Берет код из базы данных
        authCodePage.codeEnter(code); //Вводит код и проверяте открытие страницы "Личный кабинет"
    }

}
