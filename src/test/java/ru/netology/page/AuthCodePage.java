package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ru.netology.data.AuthCode;
import ru.netology.data.SQLStrings;
import ru.netology.data.User;

import java.sql.DriverManager;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class AuthCodePage {
    private SelenideElement authCodeField = $("[data-test-id=code] input");
    private SelenideElement authCodeButton = $("[data-test-id=action-verify]");

    @SneakyThrows
    public String getAuthCode() {
        var runner = new QueryRunner();

        List<AuthCode> authCode;
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )

        ) {

            authCode = runner.query(connection, SQLStrings.getAuthCode(User.getUserId()), new BeanListHandler<>(AuthCode.class));

        }
        return String.valueOf(authCode.get(authCode.size()-1));

    }

    public void codeEnter (String code){
        authCodeField.setValue(code);
        authCodeButton.click();

        $("[data-test-id=dashboard]")
                .shouldHave(Condition.text("Личный кабинет"));
    }


}
