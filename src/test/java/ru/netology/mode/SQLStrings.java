package ru.netology.mode;

public class SQLStrings {
    public static String usersDrop() {
        return "DROP TABLE IF EXISTS users;";
    }

    public static String card_transactionsDrop() {
        return "DROP TABLE IF EXISTS card_transactions;";
    }

    public static String auth_codesDrop() {
        return "DROP TABLE IF EXISTS auth_codes;";
    }

    public static String cardsDrop() {
        return "DROP TABLE IF EXISTS cards;";
    }

    public static String createTableUsers() {
        return "CREATE TABLE users\n" +
                "(\n" +
                "    id       CHAR(36) PRIMARY KEY,\n" +
                "    login    VARCHAR(255) UNIQUE NOT NULL,\n" +
                "    password VARCHAR(255)        NOT NULL,\n" +
                "    status   VARCHAR(255)        NOT NULL DEFAULT 'active'\n" +
                ");";
    }

    public static String createTableCards() {
        return "CREATE TABLE cards\n" +
                "(\n" +
                "    id                 CHAR(36) PRIMARY KEY,\n" +
                "    user_id            CHAR(36)           NOT NULL,\n" +
                "    number             VARCHAR(19) UNIQUE NOT NULL,\n" +
                "    balance_in_kopecks INT                NOT NULL,\n" +
                "    FOREIGN KEY (user_id) REFERENCES users (id)\n" +
                ");";
    }

    public static String createTableAuthCodes() {
        return "CREATE TABLE auth_codes\n" +
                "(\n" +
                "    id      CHAR(36) PRIMARY KEY,\n" +
                "    user_id CHAR(36)   NOT NULL,\n" +
                "    code    VARCHAR(6) NOT NULL,\n" +
                "    created TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "    FOREIGN KEY (user_id) REFERENCES users (id)\n" +
                ");";
    }

    public static String createTableCardTransactions() {
        return "CREATE TABLE card_transactions\n" +
                "(\n" +
                "    id                CHAR(36) PRIMARY KEY,\n" +
                "    source            VARCHAR(19) NOT NULL,\n" +
                "    target            VARCHAR(19) NOT NULL,\n" +
                "    amount_in_kopecks INT         NOT NULL,\n" +
                "    created           TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP\n" +
                ");";
    }

    public static String getUserId(){
        return "SELECT id FROM users WHERE login ='vasya';";
    }

    public static String getAuthCodeById(String id){
        return "SELECT code FROM auth_codes WHERE user_id='" + id + "';";
    }

}
