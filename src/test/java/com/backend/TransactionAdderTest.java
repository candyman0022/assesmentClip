package com.backend;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TransactionAdderTest {



    @Test
    public void add() throws Exception {
        JSONObject json = new JSONObject("{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-12-30\", \"user_id\": 345 }");
        TransactionAdder adder = new TransactionAdder("356", json);

        adder.add();
    }

}