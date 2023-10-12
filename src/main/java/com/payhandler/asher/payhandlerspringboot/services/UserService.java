package com.payhandler.asher.payhandlerspringboot.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payhandler.asher.payhandlerspringboot.domain.user.User;
import com.payhandler.asher.payhandlerspringboot.domain.user.UserType;
import com.payhandler.asher.payhandlerspringboot.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant user's cannot make transactions.");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Not enough balance..");
        }
    }

    public User findUserById(Long id) throws Exception {

        return this.repository.findById(id).orElseThrow(() -> new Exception("User not found."));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}
