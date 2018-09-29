package com.backend;

import com.backend.model.Transaction;
import org.json.JSONObject;

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

    public void add() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            Date date = format.parse(json.getString("date"));
            Transaction transaction = new Transaction();
            transaction.setAmount(json.getBigDecimal("amount"));
            transaction.setDate(date);
            transaction.setDescription(json.getString("description"));
            transaction.setUserId(userId);
            transaction.setTransactionId(UUID.randomUUID().toString());
        } catch (ParseException e) {
            e.printStackTrace();
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
