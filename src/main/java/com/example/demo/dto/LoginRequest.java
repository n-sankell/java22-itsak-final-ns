package com.example.demo.dto;

import com.example.demo.validator.AlphaNumeric;

import javax.validation.constraints.NotBlank;

public record LoginRequest(@AlphaNumeric @NotBlank String username, @NotBlank String password) {

}
