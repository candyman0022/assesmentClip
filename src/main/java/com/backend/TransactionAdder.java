package com.backend;

import org.json.JSONObject;


public class TransactionAdder {
    public String userId;
    public JSONObject json;
    public TransactionAdder(String userId, JSONObject json) {
        this.userId = userId;
        this.json = json;
    }

    public void add() {

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
