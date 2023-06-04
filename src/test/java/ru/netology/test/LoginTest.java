package ru.netology.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBInteraction;
import ru.netology.data.User;
import ru.netology.page.AuthCodePage;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @AfterAll
    @SneakyThrows
    static void cleanUp() {
        DBInteraction.cleanUpDB();
    }

    @Test
    @DisplayName("Should login")
    void loginTest() {
//        Configuration.holdBrowserOpen = true;

        LoginPage loginPage = new LoginPage();
        AuthCodePage authCodePage = new AuthCodePage();
        DashboardPage dashboardPage = new DashboardPage();
        User user = new User(
                DBInteraction.getUserId(),
                DBInteraction.getUserLogin(),
                DBInteraction.getUserPassword()
        );

        open("http://localhost:9999");

        loginPage.manualValidLogin(user.getLogin(), user.getPassword()); //Водит заранее заданные логин и парольи проверяет открытие страницы ввода проверочного кода
        authCodePage.shouldBeVisible(); //Проверяет открыте страницы подтверждения
        var code = authCodePage.getAuthCode(); //Берет код из базы данных
        authCodePage.codeEnter(code); //Вводит код и проверяте открытие страницы "Личный кабинет"
        dashboardPage.shouldBeVisible(); //Проверяет открытие личного кабинета
    }

}
