package com.backend.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class SaverTest {
    @Test
    public void save() throws Exception {
        Transaction transaction = new Transaction();

        transaction.setTransaction_id(UUID.randomUUID().toString());
        transaction.setUser_id("345");
        transaction.setDescription("description");
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDate(new Date());
        Saver saver = new Saver(transaction);
        Assert.assertTrue(saver.save());
    }

}