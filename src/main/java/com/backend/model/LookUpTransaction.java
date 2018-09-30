package com.backend.model;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LookUpTransaction {

    private static final String TRANSACTION_NOT_FOUND = "Transaction not found";
    private String userId;
    private String transactionId;
    private Utils utils;

    public LookUpTransaction() {
        utils = new Utils();
    }

    public LookUpTransaction(String userId, String transactionId) {
        this.userId = userId;
        this.transactionId = transactionId;
        utils = new Utils();
    }

    public String search() {

        String fileName = userId + "_" + transactionId;

        String writtenFile;
        try {
            writtenFile = new String(Files.readAllBytes(Paths.get(utils.getFilePath(fileName))));
            if(!writtenFile.isEmpty()) {
                JSONObject transaction = new JSONObject(writtenFile);

                if(transaction.get("user_id").equals(userId)) {
                    return writtenFile;
                } else {
                    return TRANSACTION_NOT_FOUND;
                }

            }
        } catch (IOException e) {
            return TRANSACTION_NOT_FOUND;
        }

        return TRANSACTION_NOT_FOUND;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
