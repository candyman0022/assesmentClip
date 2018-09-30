package com.backend.model;


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Saver {

    private Transaction transaction;
    private Utils utils;

    public Saver(Transaction transaction) {
        this.transaction = transaction;
        utils = new Utils();
    }

    public boolean save() throws IOException {
        JSONObject jsonObject = new JSONObject(transaction);
        byte[] byteTransaction = jsonObject.toString().getBytes();

        String fileName = transaction.getUser_id() + "_" + transaction.getTransaction_id();

        Path file = FileSystems.getDefault().getPath(fileName).toAbsolutePath();

        Files.write(file, byteTransaction);

        File writtenFile = new File(utils.getFilePath(fileName));

        if(writtenFile != null) {
            System.out.println(jsonObject.toString());
            return true;
        } else return false;


    }
}
