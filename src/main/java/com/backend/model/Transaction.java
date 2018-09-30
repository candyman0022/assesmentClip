package com.backend.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data

public class Transaction {
    private BigDecimal amount;
    private String description;
    private Date date;
    private String user_id;
    private String transaction_id;
}
