package com.example.demo.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    //@Value("${ALG_SE}") environment variable did not work for some reason
    public static final String ALG_SE = "superduper";

    public static final String ROLE = "role";
    public static final String BEARER = "Bearer ";
    public static final String HEADER_ERROR = "error";
    public static final String HEADER_RESPONSE = "response";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String HEADER_ACCESS_TOKEN = "access_token";

}
