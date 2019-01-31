package com.ing.kristoffer.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.kristoffer.model.Classification;
import com.ing.kristoffer.model.Customer;
import com.ing.kristoffer.model.Transaction;
import com.ing.kristoffer.model.TransactionType;
import com.ing.kristoffer.repository.TransactionRepository;

@Service
public class CustomerService {
	@Autowired
	private TransactionRepository repo;

	public Customer findCustomerTransactions(String customerID, YearMonth yearMonth) {
		Customer customer = new Customer();
		customer.setCustomerID(customerID);
		customer.setMonth(yearMonth);
		LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
		LocalDateTime end = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);
		List<Transaction> transactions = repo
				.findByCustomerIDAndTransactionDateBetweenOrderByTransactionDate(customerID, start, end);
		customer.setTransactions(transactions);
		populateClassification(customer);
		return customer;
	}

	public void populateClassification(Customer customer) {
		List<Transaction> transactions = customer.getTransactions();
		List<Transaction> beforeMiddayList = transactions.stream()
				.filter(txn -> txn.getTransactionDate().isBefore(txn.getTransactionDate().withHour(12)))
				.collect(Collectors.toList());
		List<Transaction> afterMiddayList = transactions.stream()
				.filter(txn -> txn.getTransactionDate().isAfter(txn.getTransactionDate().withHour(12)))
				.collect(Collectors.toList());

		int transactionSize = transactions.size();
		int beforeMiddaySize = beforeMiddayList.size();
		int afterMiddaySize = afterMiddayList.size();

		if (1.0 * beforeMiddaySize / transactionSize > 0.50) {
			customer.setClassification(Classification.MorningPerson);
		} else if (1.0 * afterMiddaySize / transactionSize > 0.50) {
			customer.setClassification(Classification.AfternoonPerson);
		}

		BigDecimal balanceValueSum = transactions.stream()
				.filter(txn -> TransactionType.Balance.equals(txn.getTransactionType()))
				.map(Transaction::getTransactionAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal depositValueSum = transactions.stream()
				.filter(txn -> TransactionType.Deposit.equals(txn.getTransactionType()))
				.map(Transaction::getTransactionAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal withdrawalValueSum = transactions.stream()
				.filter(txn -> TransactionType.Withdrawal.equals(txn.getTransactionType()))
				.map(Transaction::getTransactionAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		customer.setBalance(balanceValueSum.add(depositValueSum).subtract(withdrawalValueSum));

		if (depositValueSum.compareTo(BigDecimal.ZERO) != 0) {
			if (withdrawalValueSum.divide(depositValueSum, RoundingMode.HALF_EVEN)
					.compareTo(new BigDecimal("0.80")) > 0) {
				customer.setClassification(Classification.BigSpender);
			} else if (withdrawalValueSum.divide(depositValueSum, RoundingMode.HALF_EVEN)
					.compareTo(new BigDecimal("0.25")) < 0) {
				customer.setClassification(Classification.PotentialSaver);
			}
		}

		Transaction bigWithdrawal = transactions.stream()
				.filter(txn -> TransactionType.Withdrawal.equals(txn.getTransactionType())
						&& txn.getTransactionAmount().compareTo(new BigDecimal("1000")) > 0)
				.findAny().orElse(null);
		if (bigWithdrawal != null) {
			customer.setClassification(Classification.BigTicketSpender);
		}

		List<Transaction> depositList = transactions.stream()
				.filter(txn -> TransactionType.Deposit.equals(txn.getTransactionType())).collect(Collectors.toList());
		List<Transaction> withdrawalList = transactions.stream()
				.filter(txn -> TransactionType.Withdrawal.equals(txn.getTransactionType()))
				.collect(Collectors.toList());
		for (Transaction transaction : depositList) {
			LocalDateTime depositDate = transaction.getTransactionDate();
			LocalDate oneWeekLater = depositDate.toLocalDate().plusDays(7);
			BigDecimal withdrawalValueSumForWeek = withdrawalList.stream()
					.filter(txn -> txn.getTransactionDate().isAfter(depositDate)
							&& txn.getTransactionDate().toLocalDate().isBefore(oneWeekLater))
					.map(Transaction::getTransactionAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
			if (withdrawalValueSumForWeek.divide(transaction.getTransactionAmount(), RoundingMode.HALF_EVEN)
					.compareTo(new BigDecimal("0.75")) > 0) {
				if (Classification.BigSpender.equals(customer.getClassification())) {
					customer.setClassification(Classification.PotentialLoan);
				} else {
					customer.setClassification(Classification.FastSpender);
				}
				break;
			}
		}
	}
}
