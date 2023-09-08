package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.data.Role;
import com.example.demo.data.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.constants.Constants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class TokenUtil {

    public static String getAccessToken(Student student, Long current) {
        return JWT.create().withSubject(student.getUserName())
                .withExpiresAt(accessTokenLimit(current))
                .withClaim(ROLE, student.getRoles().stream().map(Role::getRole).toList())
                .sign(ALGORITHM);
    }

    public static Map<String, String> packageError(HttpServletResponse response, String errorMessage) {
        Map<String, String> error = new HashMap<>();
        response.setHeader(HEADER_ERROR,errorMessage);
        response.setStatus(FORBIDDEN.value());
        error.put(ERROR_MESSAGE, errorMessage);
        response.setContentType(APPLICATION_JSON_VALUE);
        return error;
    }

    public static Student getStudentFromToken(HttpServletRequest request, StudentRepository studentRepository) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length());
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String email = decodedJWT.getSubject();
           return studentRepository.findStudentByUserName(email);
        } else {
            return null;
        }
    }

    public static final Algorithm ALGORITHM = Algorithm.HMAC256("thisisnothere".getBytes());

    public static Date accessTokenLimit(Long current) {
        return new Date(current + 10 * 60 * 1000);
    }

}
