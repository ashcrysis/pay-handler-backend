package com.payhandler.asher.payhandlerspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payhandler.asher.payhandlerspringboot.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
