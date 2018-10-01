package com.backend.model;

import com.backend.interfaces.Lister;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTransactionFileSystem implements Lister{

    private Utils utils = new Utils();
    ListTransactionFileSystem() {
    }

    public String[] list(String userId) {

        File[] files = utils.getFiles(userId);

        List<Transaction> transactions = new ArrayList<>();

        for (File file : files) {
            Transaction transaction = new Transaction();
            try {
                JSONObject transactionJSON = utils.getJsonObject(file);

                LocalDate dateTime = getLocalDate(transactionJSON);
                buildTransaction(transactions, transaction, transactionJSON, dateTime,userId);
            } catch (IOException e) {
                return new String[0];
            }
        }
        Collections.sort(transactions);
        return buildOrderedTransactions(transactions);
    }

    private String[] buildOrderedTransactions(List<Transaction> transactions) {
        String[] orderedTransactions = new String[transactions.size()];

        for (int i = 0; i < transactions.size(); i++) {
            JSONObject object = new JSONObject(transactions.get(i));
            orderedTransactions[i] = object.toString();
        }
        return orderedTransactions;
    }

    private LocalDate getLocalDate(JSONObject transactionJSON) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(transactionJSON.getString("date"),formatter);
    }

    private void buildTransaction(List<Transaction> transactions, Transaction transaction, JSONObject transactionJSON, LocalDate dateTime, String userId) {
        transaction.setAmount(transactionJSON.getBigDecimal("amount"));
        transaction.setDate(dateTime);
        transaction.setDescription(transactionJSON.getString("description"));
        transaction.setUser_id(userId);
        transaction.setTransaction_id(transactionJSON.getString("transaction_id"));
        transactions.add(transaction);
    }
}
