package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement actionLogin = $("[data-test-id=action-login]");

    public LoginPage() {
        loginField.shouldBe(Condition.visible);
        passwordField.shouldBe(Condition.visible);
        actionLogin.shouldBe(Condition.visible);
        headingShouldBeVisible();
    }

    public AuthCodePage manualValidLogin(String user, String password) {
        loginField.setValue(user);
        passwordField.setValue(password);
        actionLogin.click();
        return new AuthCodePage();
    }

    public void headingShouldBeVisible() {
        $("[id='root'] p")
                .shouldHave(Condition.text("Мы гарантируем безопасность ваших данных"));
    }

    //todo: тут должны быть методы обработки ошибок и вввода неверных значений

}
