package com.backend.model;

import org.junit.Assert;
import org.junit.Test;

public class SumTransactionTest {
    @Test
    public void testSum() {
        SumTransaction sumTransaction = new SumTransaction("345");
        String sum = sumTransaction.sum();

        Assert.assertNotEquals("Invalid User ID",sum);
    }

    @Test
    public void testSumInvalidUserId() {
        SumTransaction sumTransaction = new SumTransaction("3455");
        String sum = sumTransaction.sum();

        Assert.assertEquals("Invalid User ID",sum);
    }
}