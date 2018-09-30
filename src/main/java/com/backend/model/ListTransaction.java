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

        File dir = new File(".");
        FileFilter fileFilter = new WildcardFileFilter(userId + "_*");
        File[] files = dir.listFiles(fileFilter);

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            Transaction transaction = new Transaction();

            try {
                String writtenFile = new String(Files.readAllBytes(Paths.get(String.valueOf(files[i]))));
                JSONObject transactionJSON = new JSONObject(writtenFile);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateTime = LocalDate.parse(transactionJSON.getString("date"),formatter);
                transaction.setAmount(transactionJSON.getBigDecimal("amount"));
                transaction.setDate(dateTime);
                transaction.setDescription(transactionJSON.getString("description"));
                transaction.setUser_id(userId);
                transaction.setTransaction_id(transactionJSON.getString("transaction_id"));
                transactions.add(transaction);
            } catch (IOException e) {
                return new String[0];
            }
        }

        Collections.sort(transactions);

        String[] orderedTransactions = new String[transactions.size()];

        for (int i = 0; i < transactions.size(); i++) {
            JSONObject object = new JSONObject(transactions.get(i));
            orderedTransactions[i] = object.toString();
        }
        return orderedTransactions;
    }
}
