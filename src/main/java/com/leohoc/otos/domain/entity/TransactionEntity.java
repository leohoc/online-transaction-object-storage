package com.leohoc.otos.domain.entity;

public class TransactionEntity {

    private final TransactionCode transactionCode;
    private final String description;

    public TransactionEntity(String code, String description) {
        this.transactionCode = new TransactionCode(code);
        this.description = description;
    }

    public TransactionEntity(TransactionCode transactionCode, String description) {
        this.transactionCode = transactionCode;
        this.description = description;
    }

    public TransactionCode getTransactionCode() {
        return transactionCode;
    }

    public String getDescription() {
        return description;
    }
}
