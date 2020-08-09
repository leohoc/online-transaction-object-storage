package com.leohoc.otos.domain.entity;

import java.util.UUID;

public class TransactionEntity {

    private static final int PARTITIONS_COUNT = 100;

    private UUID code;
    private String description;

    public TransactionEntity(String code, String description) {
        this.code = UUID.fromString(code);
        this.description = description;
    }

    public UUID getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Long getCodePartition() {
        return code.getMostSignificantBits() % 100;
    }

    public String toCSV() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(code);
        stringBuilder.append(";");
        stringBuilder.append(description);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
}
