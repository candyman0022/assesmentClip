package com.backend;

import com.backend.interfaces.Adder;
import com.backend.model.TransactionAdderFileSystem;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TransactionAdderFileSystemTest {



    @Test
    public void add() throws Exception {
        JSONObject json = new JSONObject("{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-10-30\", \"user_id\": 345 }");
        Adder adder = new TransactionAdderFileSystem();

        Assert.assertTrue(adder.add("356",json));
    }

}