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

    private String authCode;
    @SneakyThrows
    public void authCodeEnter() {
        var runner = new QueryRunner();
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {

            var user = runner.query(connection, SQLStrings.getUserId(), new BeanHandler<>(User.class));
            authCode = "SELECT * FROM auth_codes WHERE user_id = '" + user.getId() +"'";
            authCodeField.setValue(authCode);
            authCodeButton.click();
        }

    }


}
