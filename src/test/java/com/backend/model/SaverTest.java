package com.backend.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class SaverTest {
    @Test
    public void save() throws Exception {
        Transaction transaction = new Transaction();

        String now = "2016-11-09";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate formatDateTime = LocalDate.parse(now, formatter);

        transaction.setTransaction_id(UUID.randomUUID().toString());
        transaction.setUser_id("345");
        transaction.setDescription("description");
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDate(formatDateTime);
        Saver saver = new Saver(transaction);
        Assert.assertTrue(saver.save());
    }

}