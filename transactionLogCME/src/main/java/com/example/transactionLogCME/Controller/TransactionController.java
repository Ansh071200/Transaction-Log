package com.example.transactionLogCME.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactionLogCME.Model.Transactions;
import com.example.transactionLogCME.Service.TransactionService;

@RestController
@RequestMapping("/transactionlog")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // gets all the transaction with timestamps
    @GetMapping("/transactions")
    public List<Transactions> getTransactions() {
        return transactionService.listTransactions();
    }

    // created specifically to check the balance, can be modified to be listed along
    // with the transactions
    @GetMapping("/balance")
    public int getBalance() {
        return transactionService.getAmount();
    }

    // add the transaction model object. jpa will take care of object->table
    // entities
    @PostMapping("/addtransaction")
    public String addTransaction(@RequestBody Transactions transaction) {
        return transactionService.createTransaction(transaction);
    }

    // updating records based on id.. will work only if the id already exists
    @PutMapping("/updatetransaction/{id}")
    public void updateTransaction(@PathVariable int id, @RequestBody Transactions transaction) {
        transactionService.changeTransaction(id, transaction);
    }

    // will return average debit value
    @GetMapping("/avgdebitvalue")
    public int avgdebitValue() {
        return transactionService.getAvgDebitValue();
    }

    // for filtering the transactions based on type
    @GetMapping("/filterbytype/{type}")
    public List<Transactions> filterbyType(@PathVariable String type) {
        return transactionService.gettByType(type);
    }
}
