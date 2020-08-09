package com.leohoc.otos.domain.entity;

import java.util.UUID;

public class TransactionCode {

    private static final String SLASH = "/";
    private static final int PARTITIONS_COUNT = 100;

    private final UUID code;

    public TransactionCode(final String code) {
        this.code = UUID.fromString(code);
    }

    public UUID getCode() {
        return code;
    }

    public String getS3KeyName() {
        return partition() + SLASH + getCode();
    }

    private Long partition() {
        return (getCode().getMostSignificantBits() & Long.MAX_VALUE) % PARTITIONS_COUNT;
    }
}
