package com.backend.model;

import com.backend.interfaces.Lister;
import org.junit.Assert;
import org.junit.Test;

public class ListTransactionFileSystemTest {

    @Test
    public void testShow() {
        Lister lister = new ListTransactionFileSystem();

        String[] list = lister.list("345");
        Assert.assertTrue(list.length > 0);


    }

    @Test
    public void testNoTransactions() {
        Lister lister = new ListTransactionFileSystem();

        String[] list = lister.list("38775");
        Assert.assertTrue(list.length == 0);
    }
}