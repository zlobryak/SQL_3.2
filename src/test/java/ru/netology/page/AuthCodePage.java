package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.mode.SQLStrings;
import ru.netology.mode.User;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.$;

public class AuthCodePage {
    private SelenideElement authCodeField = $("[data-test-id=code] input");
    private SelenideElement authCodeButton = $("[data-test-id=action-verify]");

    @SneakyThrows
    private String authCode() {
        var runner = new QueryRunner();
        var authCodeSQl = "SELECT code FROM auth_codes WHERE user_id='04b98f30-59a4-4781-8f20-5e6f9bd8c973';";
        String authCode;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            authCode = runner.query(connection, authCodeSQl,
                    new BeanHandler<>(String.class));

        }
        return authCode;
    }


    public void authCodeEnter() {
        authCodeField.setValue(authCode());
        authCodeButton.click();

    }

}
