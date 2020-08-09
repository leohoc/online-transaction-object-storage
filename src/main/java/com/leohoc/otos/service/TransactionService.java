package com.leohoc.otos.service;

import com.leohoc.otos.domain.entity.TransactionCode;
import com.leohoc.otos.domain.entity.TransactionEntity;
import com.leohoc.otos.infrasctructure.persistence.TransactionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransactionService {

    @Autowired
    private TransactionPersistence transactionPersistence;

    public TransactionEntity createTransaction(final String code, final String description) {
        final TransactionEntity transactionEntity = new TransactionEntity(code, description);
        transactionPersistence.persist(transactionEntity);
        return transactionEntity;
    }

    public TransactionEntity getTransaction(final String code) throws IOException {
        return transactionPersistence.getTransactionBy(new TransactionCode(code));
    }
}
