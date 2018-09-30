package com.backend.model;


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver {

    private Transaction transaction;

    public Saver(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean save() throws IOException {
        JSONObject jsonObject = new JSONObject(transaction);
        byte[] byteTransaction = jsonObject.toString().getBytes();

        String fileName = transaction.getUser_id() + "_" + transaction.getTransaction_id();

        Path file = FileSystems.getDefault().getPath(fileName).toAbsolutePath();

        Files.write(file, byteTransaction);

        String filePath = FileSystems.getDefault().getPath(fileName).toAbsolutePath().toString();

        File writtenFile = new File(filePath);

        if(writtenFile != null) {
            System.out.println(jsonObject.toString());
            return true;
        } else return false;


    }
}
