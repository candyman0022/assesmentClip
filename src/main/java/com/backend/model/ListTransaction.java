package com.backend.model;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTransaction {

    private String userId;

    public ListTransaction(String userId) {
    this.userId = userId;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] list() {

        File[] files = getFiles();

        List<Transaction> transactions = new ArrayList<>();

        for (File file : files) {
            Transaction transaction = new Transaction();
            try {
                String writtenFile = new String(Files.readAllBytes(Paths.get(String.valueOf(file))));
                JSONObject transactionJSON = new JSONObject(writtenFile);

                LocalDate dateTime = getLocalDate(transactionJSON);
                buildTransaction(transactions, transaction, transactionJSON, dateTime);
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

    private File[] getFiles() {
        File dir = new File(".");
        FileFilter fileFilter = new WildcardFileFilter(userId + "_*");
        return dir.listFiles(fileFilter);
    }

    private LocalDate getLocalDate(JSONObject transactionJSON) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(transactionJSON.getString("date"),formatter);
    }

    private void buildTransaction(List<Transaction> transactions, Transaction transaction, JSONObject transactionJSON, LocalDate dateTime) {
        transaction.setAmount(transactionJSON.getBigDecimal("amount"));
        transaction.setDate(dateTime);
        transaction.setDescription(transactionJSON.getString("description"));
        transaction.setUser_id(userId);
        transaction.setTransaction_id(transactionJSON.getString("transaction_id"));
        transactions.add(transaction);
    }
}
