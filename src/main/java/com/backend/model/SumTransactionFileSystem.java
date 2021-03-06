package com.backend.model;

import com.backend.interfaces.Calculate;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class SumTransactionFileSystem implements Calculate{
    private Utils utils = new Utils();

    SumTransactionFileSystem() {
    }

    public String sum(String userId) {
        File[] files = utils.getFiles(userId);
        BigDecimal sum = BigDecimal.ZERO;

        if (files.length == 0)
            return "Invalid User ID";

        for (File file : files) {
            try {
                JSONObject transactionJSON = utils.getJsonObject(file);
                sum =  transactionJSON.getBigDecimal("amount").add(sum);
            } catch (IOException e) {
                return "There is no user with that ID";
            }
        }
        return "{ \"user_id\": " + userId + ", \"sum\": " + sum + " }";
    }
}
