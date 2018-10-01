package com.backend.model;

import com.backend.interfaces.Searcher;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LookUpTransactionFileSystem implements Searcher{

    private static final String TRANSACTION_NOT_FOUND = "Transaction not found";
    private Utils utils;

    public LookUpTransactionFileSystem() {
        utils = new Utils();
    }

    public String search(String userId, String transactionId) {

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
        } catch (Exception e) {
            return TRANSACTION_NOT_FOUND;
        }
        return TRANSACTION_NOT_FOUND;
    }
}
