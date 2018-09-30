package com.backend;

import com.backend.model.Saver;
import com.backend.model.Transaction;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class TransactionAdder {
    public String userId;
    public JSONObject json;
    public TransactionAdder(String userId, JSONObject json) {
        this.userId = userId;
        this.json = json;
    }

    public boolean add() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(json.getString("date"),formatter);
        try {
            Transaction transaction = getTransaction(dateTime);
            Saver saver = new Saver(transaction);
            return saver.save();

        } catch (IOException e) {
            System.out.printf("Could not write file transaction");
            return false;
        }
    }

    private Transaction getTransaction(LocalDate dateTime) {
        Transaction transaction = new Transaction();
        transaction.setAmount(json.getBigDecimal("amount"));
        transaction.setDate(dateTime);
        transaction.setDescription(json.getString("description"));
        transaction.setUser_id(userId);
        transaction.setTransaction_id(UUID.randomUUID().toString());
        return transaction;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
}
