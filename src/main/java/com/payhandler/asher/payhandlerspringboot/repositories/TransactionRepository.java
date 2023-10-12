package com.payhandler.asher.payhandlerspringboot.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import com.payhandler.asher.payhandlerspringboot.domain.transaction.Transaction;
import com.payhandler.asher.payhandlerspringboot.services.UserService;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
