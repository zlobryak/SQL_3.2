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
        AuthCodePage.headingShouldBeVisible();
    }

    public void codeEnter(String code) {
        authCodeField.setValue(code);
        authCodeButton.click();
    }
    public static void headingShouldBeVisible(){
        $("[id='root'] p")
                .shouldHave(Condition.text("Необходимо подтверждение"));
    }


}
