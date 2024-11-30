package com.example.transactionLogCME.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transactionLogCME.Model.Transactions;
import com.example.transactionLogCME.Repository.TransactionRepository;

// all the buisness logic goes here.

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public String createTransaction(Transactions transaction) {

        // get the current timestamp
        LocalDateTime timeStamp = LocalDateTime.now();
        transaction.setTimeStamp(timeStamp);

        // change the transaction type based on transaction value
        if (transaction.getTransactionValue() > 0) {
            transaction.setTransactionType("Credit");
        } else {
            transaction.setTransactionType("Debit");
        }

        int currBalance = transactionRepository.getBalance();

        System.out.println(currBalance);

        if (transaction.getTransactionType().equals("Debit") && transaction.getTransactionValue() > currBalance) {
            return "Transaction Couldn't be Processed. Insufficient Balance";
        }
        transactionRepository.save(transaction);
        return "Transaction Successfull";
    }

    public List<Transactions> listTransactions() {
        // list all the transactions
        return transactionRepository.findAll();
    }

    public int getAmount() {
        // utility function to get the total balance. since we already have the
        // sign along with the transacion, we can directly sum them all up.
        // return transactionRepository.findAll().stream()
        // .mapToInt(t -> t.getTransactionValue())
        // .sum();
        return transactionRepository.getBalance();
    }

    // updates the transaction
    public void changeTransaction(int id, Transactions transaction) {
        // checks if transaction exists, if not then adds it else updates
        // transactionRepository.save(transaction); {will not work here due to generated
        // id}
        Transactions dbTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Element not found with ID : " + id));

        dbTransaction.setProdName(transaction.getProdName());
        dbTransaction.setTransactionValue(transaction.getTransactionValue());
        dbTransaction.setDescription(transaction.getDescription());
        dbTransaction.setTransactionType(transaction.getTransactionValue() > 0 ? "Credit" : "Debit");
        dbTransaction.setTimeStamp(LocalDateTime.now());

        // crushed the existing variables
        transactionRepository.save(dbTransaction);
    }

    public int getAvgDebitValue() {
        return transactionRepository.getAvgDebitValue();
    }

    public List<Transactions> gettByType(String type) {
        return transactionRepository.listTbyType(type);
    }
}
