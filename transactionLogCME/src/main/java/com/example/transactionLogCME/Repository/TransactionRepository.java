package com.example.transactionLogCME.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.transactionLogCME.Model.Transactions;

// our jpa repo for easier db querying.{the final layer}

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    @Query("SELECT t from Transactions t where t.transactionType = :type")
    List<Transactions> listTbyType(String type);

    @Query("SELECT SUM(t.transactionValue) from Transactions t")
    Integer getBalance();

    @Query("SELECT AVG(t.transactionValue) from Transactions t where t.transactionType = 'Debit'")
    Integer getAvgDebitValue();
}
