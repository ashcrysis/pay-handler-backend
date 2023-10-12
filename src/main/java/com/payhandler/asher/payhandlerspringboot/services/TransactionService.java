package com.payhandler.asher.payhandlerspringboot.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payhandler.asher.payhandlerspringboot.domain.transaction.Transaction;
import com.payhandler.asher.payhandlerspringboot.domain.user.User;
import com.payhandler.asher.payhandlerspringboot.dtos.TransactionDTO;
import com.payhandler.asher.payhandlerspringboot.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {

        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        if (!this.authorizeTransaction(sender, transaction.value())) {
            throw new Exception("Transaction not authorized.");
        }

        Transaction new_transaction = new Transaction();

        new_transaction.setAmount(transaction.value());
        new_transaction.setSender(sender);
        new_transaction.setReceiver(receiver);
        new_transaction.setTimestamp(LocalDateTime.now());
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(new_transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transaction made with sucess");
        this.notificationService.sendNotification(receiver, "Transaction received with sucess");
        return new_transaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        boolean authorizationResponse = true;
        if (authorizationResponse == true) {
            return true;
        } else {
            return false;
        }
    }
}
