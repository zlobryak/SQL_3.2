package ru.netology.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    public DashboardPage() {
        headingShouldBeVisible();
    }

    public void headingShouldBeVisible(){
        $("[data-test-id=dashboard]")
                .shouldHave(Condition.text("Личный кабинет"));
    }
}
