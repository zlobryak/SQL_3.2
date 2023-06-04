package ru.netology.resources;

public class SQLStrings {
    public static String getUserId(){
        return "SELECT id FROM users WHERE login = 'vasya';";
    }

    public static String getAuthCode (String userId){
        return "SELECT code FROM auth_codes WHERE user_id = '" + userId + "';";
    }

    public static String truncateTable(String tableName){
        return "TRUNCATE TABLE app." + tableName +";";
    }

    public static String foreignKeyOff(){
        return "SET FOREIGN_KEY_CHECKS = 0;";
    }
    public static String foreignKeyOn(){
        return "SET FOREIGN_KEY_CHECKS = 1;";
    }
}
