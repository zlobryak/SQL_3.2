package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DataHelper {
    public static String getUserLogin(){
        return "vasya";
    }
    public static String getUserPassword(){
        return "qwerty123";
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String userId;
        private String code;

    }
}
