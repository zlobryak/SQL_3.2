package ru.netology.test;

//import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBInteraction;
import ru.netology.data.DataHelper;
import ru.netology.page.AuthCodePage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @AfterAll
    static void cleanUp() {
        DBInteraction.cleanUpDB();
    }

    @Test
    @DisplayName("Should login")
    void loginTest() {
//        Configuration.holdBrowserOpen = true;
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        AuthCodePage authCodePage = loginPage                .manualValidLogin(DataHelper.getUserLogin(), DataHelper.getUserPassword());
        var code = DBInteraction.getAuthCode();
        authCodePage.codeEnter(code);
    }

    @Test
    @DisplayName("Wrong auth_code message should be visible")
    void wrongAuthCodeTest() {
//        Configuration.holdBrowserOpen = true;
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        AuthCodePage authCodePage = loginPage
                .manualValidLogin(DataHelper.getUserLogin(), DataHelper.getUserPassword());
        authCodePage.wrongCodeEnter();
    }


}
