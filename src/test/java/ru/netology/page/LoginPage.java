package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.mode.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private  SelenideElement actionLogin = $("[data-test-id=action-login]");
    public void validLogin(User user){
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        actionLogin.click();
    }
    public void manualValidLogin(String user, String password){
        loginField.setValue(user);
        passwordField.setValue(user);
        actionLogin.click();
    }
}
