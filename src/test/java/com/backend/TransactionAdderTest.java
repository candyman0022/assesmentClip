package com.backend;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TransactionAdderTest {



    @Test
    public void add() throws Exception {
        JSONObject json = new JSONObject("{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-10-30\", \"user_id\": 345 }");
        TransactionAdder adder = new TransactionAdder("356", json);

        Assert.assertTrue(adder.add());
    }

}