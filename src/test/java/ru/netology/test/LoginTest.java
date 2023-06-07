package ru.netology.test;

//import com.codeborne.selenide.Configuration;
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


        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        loginPage.manualValidLogin(user.getLogin(), user.getPassword());

        AuthCodePage authCodePage = new AuthCodePage();
        var code = DBInteraction.getAuthCode();
        authCodePage.codeEnter(code);

        DashboardPage dashboardPage = new DashboardPage();
    }

}
