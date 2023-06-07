package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$;

public class AuthCodePage {
    private SelenideElement authCodeField = $("[data-test-id=code] input");
    private SelenideElement authCodeButton = $("[data-test-id=action-verify]");

    public void codeEnter(String code) {
        authCodeField.setValue(code);
        authCodeButton.click();
    }
    public void shouldBeVisible(){
        $("[id='root'] p")
                .shouldHave(Condition.text("Необходимо подтверждение"));
    }


}
