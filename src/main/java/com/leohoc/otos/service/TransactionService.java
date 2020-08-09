package com.leohoc.otos.service;

import com.leohoc.otos.domain.entity.TransactionEntity;
import com.leohoc.otos.infrasctructure.persistence.TransactionWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionWriter transactionWriter;

    public TransactionEntity createTransaction(final String code, final String description) {
        final TransactionEntity transactionEntity = new TransactionEntity(code, description);
        transactionWriter.persist(transactionEntity);
        return transactionEntity;
    }
}
