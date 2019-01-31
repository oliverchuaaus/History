package com.ing.kristoffer.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ing.kristoffer.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

	public List<Transaction> findByCustomerIDAndTransactionDateBetweenOrderByTransactionDate(String customerID, LocalDateTime start,
			LocalDateTime end);
}
