package ru.netology.test;

import com.codeborne.selenide.Configuration;
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
    @SneakyThrows
    void loginTest() {
//        Configuration.holdBrowserOpen = true;
        User user = new User(
                DBInteraction.getUserId(),
                DBInteraction.getUserLogin(),
                DBInteraction.getUserPassword()
        );


        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        AuthCodePage authCodePage = loginPage.manualValidLogin(user.getLogin(), user.getPassword());
;
        var code = DBInteraction.getAuthCode();
        DashboardPage dashboardPage = authCodePage.codeEnter(code);
    }

    @Test
    @DisplayName("Wrong auth_code message should be visible")
    @SneakyThrows
    void wrongAuthCodeTest() {
//        Configuration.holdBrowserOpen = true;
        User user = new User(
                DBInteraction.getUserId(),
                DBInteraction.getUserLogin(),
                DBInteraction.getUserPassword()
        );

        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        AuthCodePage authCodePage = loginPage.manualValidLogin(user.getLogin(), user.getPassword());
        authCodePage.wrongCodeEnter();
    }


}
