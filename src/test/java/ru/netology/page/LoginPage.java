package ru.netology.page;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private  SelenideElement actionLogin = $("[data-test-id=action-login]");

    public void manualValidLogin(String user, String password){
        loginField.setValue(user);
        passwordField.setValue(password);
        actionLogin.click();
    }


}
