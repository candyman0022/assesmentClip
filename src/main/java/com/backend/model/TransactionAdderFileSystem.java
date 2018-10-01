package com.backend.model;

import com.backend.interfaces.Adder;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class TransactionAdderFileSystem implements Adder{
    public TransactionAdderFileSystem() {

    }

    @Override
    public boolean add(String userId, JSONObject json) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(json.getString("date"),formatter);
        try {
            Transaction transaction = getTransaction(dateTime,json,userId);
            Saver saver = new Saver(transaction);
            return saver.save();

        } catch (IOException e) {
            System.out.printf("Could not write file transaction");
            return false;
        }
    }

    private Transaction getTransaction(LocalDate dateTime, JSONObject json, String userId) {
        Transaction transaction = new Transaction();
        transaction.setAmount(json.getBigDecimal("amount"));
        transaction.setDate(dateTime);
        transaction.setDescription(json.getString("description"));
        transaction.setUser_id(userId);
        transaction.setTransaction_id(UUID.randomUUID().toString());
        return transaction;
    }
}
