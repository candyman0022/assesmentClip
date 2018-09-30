package com.backend.model;

import org.junit.Assert;
import org.junit.Test;

public class ListTransactionTest {

    @Test
    public void testShow() {
        ListTransaction lister = new ListTransaction("345");

        String[] list = lister.list();

        Assert.assertTrue(list.length > 0);


    }

    @Test
    public void testNoTransactions() {
        ListTransaction lister = new ListTransaction("38775");

        String[] list = lister.list();

        Assert.assertTrue(list.length == 0);
    }
}