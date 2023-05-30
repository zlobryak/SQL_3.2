package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.mode.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement actionLogin = $("[data-test-id=action-login]");
    public void validLogin(User user){
        login.setValue(user.getId());
        password.setValue(user.getPassword());
        actionLogin.click();
    }
}
