package com.backend.model;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumTransaction {

    String userId;

    public SumTransaction() {
    }

    public SumTransaction(String userId) {
        this.userId = userId;
    }

    public String sum() {

        File[] files = getFiles();

        BigDecimal sum = BigDecimal.ZERO;

        if (files.length == 0)
            return "Invalid User ID";

        for (File file : files) {
            try {
                String writtenFile = new String(Files.readAllBytes(Paths.get(String.valueOf(file))));
                JSONObject transactionJSON = new JSONObject(writtenFile);

                sum =  transactionJSON.getBigDecimal("amount").add(sum);

            } catch (IOException e) {
                return "There is no user with that ID";
            }
        }

        return "{ \"user_id\": " + userId + ", \"sum\": " + sum + " }";
    }

    private File[] getFiles() {
        File dir = new File(".");
        FileFilter fileFilter = new WildcardFileFilter(userId + "_*");
        return dir.listFiles(fileFilter);
    }

    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
