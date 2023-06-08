package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$;

public class AuthCodePage {
    private SelenideElement authCodeField = $("[data-test-id=code] input");
    private SelenideElement authCodeButton = $("[data-test-id=action-verify]");

    public AuthCodePage() {
        authCodeField.shouldBe(Condition.visible);
        authCodeButton.shouldBe(Condition.visible);
        headingShouldBeVisible();
    }

    public DashboardPage codeEnter(String code) {
        authCodeField.setValue(code);
        authCodeButton.click();
        return new DashboardPage();
    }

    public void wrongCodeEnter() {
        authCodeField.setValue("0");
        authCodeButton.click();
        errorMessage();
    }

    public void headingShouldBeVisible() {
        $("[id='root'] p")
                .shouldHave(Condition.text("Необходимо подтверждение"));
    }

    public void errorMessage() {
        $("[data-test-id='error-notification']")
                .shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз"));

    }


}
