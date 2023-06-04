package ru.netology.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public void shouldBeVisible(){
        $("[data-test-id=dashboard]")
                .shouldHave(Condition.text("Личный кабинет"));
    }
}
