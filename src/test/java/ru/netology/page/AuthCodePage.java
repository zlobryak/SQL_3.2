package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.mode.AuthCode;
import ru.netology.mode.SQLStrings;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.$;

public class AuthCodePage {
    private SelenideElement authCodeField = $("[data-test-id=code] input");
    private SelenideElement authCodeButton = $("[data-test-id=action-verify]");

    private String authCode;

    @SneakyThrows
    public String getAuthCode() {
        var runner = new QueryRunner();
        AuthCode authCode;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {

            authCode = runner.query(connection, SQLStrings.getAuthCode(getUserId()), new BeanHandler<>(AuthCode.class));

        }
        return authCode.getCode();

    }

    @SneakyThrows
    public String getUserId() {
        var runner = new QueryRunner();
        AuthCode id;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            id = runner.query(connection, SQLStrings.getUserId(), new BeanHandler<>(AuthCode.class));
        }

        return id.getId();
    }


}
