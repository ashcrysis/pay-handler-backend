package com.payhandler.asher.payhandlerspringboot.dtos;

import java.math.BigDecimal;

import com.payhandler.asher.payhandlerspringboot.domain.user.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email,
                String password, UserType userType) {

}
