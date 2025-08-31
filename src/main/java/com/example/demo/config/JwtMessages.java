package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import java.util.Map;

@Configuration
@ConfigurationProperties
@Data
public class JwtMessages {
    private JwtCategory jwt;
    private AuthCategory auth;
    private ValidationCategory validation;
    private DatabaseCategory database;

    @Data
    public static class JwtCategory {
        private TokenMessages errors;
        private TokenSuccessMessages success;
    }

    @Data
    public static class TokenMessages {
        private Map<String, String> token;
    }

    @Data
    public static class TokenSuccessMessages {
        private Map<String, String> token;
    }

    @Data
    public static class AuthCategory {
        private AuthErrors errors;
        private Map<String, String> success;
    }

    @Data
    public static class AuthErrors {
        private Map<String, String> login;
        private Map<String, String> registration;
    }

    @Data
    public static class ValidationCategory {
        private ValidationErrors errors;
    }

    @Data
    public static class ValidationErrors {
        private UserValidation user;
    }

    @Data
    public static class UserValidation {
        private Map<String, String> username;
        private Map<String, String> password;
        private Map<String, String> email;
    }

    @Data
    public static class DatabaseCategory {
        private Map<String, String> errors;
        private Map<String, String> success;
    }

    public String getTokenError(String key) {
        return jwt.getErrors().getToken().get(key);
    }

    public String getTokenSuccess(String key) {
        return jwt.getSuccess().getToken().get(key);
    }

    public String getAuthError(String category, String key) {
        Map<String, String> errors = category.equals("login") ? 
            auth.getErrors().getLogin() : auth.getErrors().getRegistration();
        return errors.get(key);
    }

    public String getAuthSuccess(String key) {
        return auth.getSuccess().get(key);
    }

    public String getValidationError(String entity, String field, String key) {
        if (!"user".equals(entity)) {
            return null;
        }
        UserValidation userValidation = validation.getErrors().getUser();
        Map<String, String> fieldErrors = switch (field) {
            case "username" -> userValidation.getUsername();
            case "password" -> userValidation.getPassword();
            case "email" -> userValidation.getEmail();
            default -> null;
        };
        return fieldErrors != null ? fieldErrors.get(key) : null;
    }

    public String getDatabaseError(String key) {
        return database.getErrors().get(key);
    }

    public String getDatabaseSuccess(String key) {
        return database.getSuccess().get(key);
    }
}
