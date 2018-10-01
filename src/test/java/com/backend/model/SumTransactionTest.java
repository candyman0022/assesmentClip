package com.backend.model;

import com.backend.interfaces.Calculate;
import org.junit.Assert;
import org.junit.Test;

public class SumTransactionTest {
    @Test
    public void testSum() {
        Calculate sumTransaction = new SumTransactionFileSystem();
        String sum = sumTransaction.sum("345");

        Assert.assertNotEquals("Invalid User ID",sum);
    }

    @Test
    public void testSumInvalidUserId() {
        Calculate sumTransaction = new SumTransactionFileSystem();
        String sum = sumTransaction.sum("3455");

        Assert.assertEquals("Invalid User ID",sum);
    }
}