package com.backend;

import com.backend.model.Saver;
import com.backend.model.Transaction;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class TransactionAdder {
    public String userId;
    public JSONObject json;
    public TransactionAdder(String userId, JSONObject json) {
        this.userId = userId;
        this.json = json;
    }

    public boolean add() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            Date date = format.parse(json.getString("date"));
            Transaction transaction = new Transaction();
            transaction.setAmount(json.getBigDecimal("amount"));
            transaction.setDate(date);
            transaction.setDescription(json.getString("description"));
            transaction.setUser_id(userId);
            transaction.setTransaction_id(UUID.randomUUID().toString());

            Saver saver = new Saver(transaction);
            return saver.save();

        } catch (ParseException e) {
            System.out.printf("Could not parse JSON Transaction");
            return false;
        } catch (IOException e) {
            System.out.printf("Could not write file transaction");
            return false;
        }
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
