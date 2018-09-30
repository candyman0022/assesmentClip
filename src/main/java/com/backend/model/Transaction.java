package com.backend.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class Transaction implements Comparable<Transaction>{
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String user_id;
    private String transaction_id;

    @Override
    public int compareTo(Transaction o) {
        return o.getDate().compareTo(getDate());
    }
}
