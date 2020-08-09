package com.leohoc.otos.application;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.leohoc.otos.domain.entity.TransactionEntity;
import com.leohoc.otos.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TransactionController {

    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TransactionService transactionService;

    @PutMapping(value = "/transactions")
    public ResponseEntity putTransaction(final String code, final String description) {
        LOGGER.info("m=putTransaction, code={}, description={}", code, description);
        TransactionEntity transactionEntity = transactionService.createTransaction(code, description);
        return new ResponseEntity(transactionEntity, HttpStatus.OK);
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity getTransaction(final String code) {

        LOGGER.info("m=getTransaction, code={}", code);

        try {
            TransactionEntity transactionEntity = transactionService.getTransaction(code);
            return new ResponseEntity(transactionEntity, HttpStatus.OK);
        } catch (AmazonS3Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("m=getTransaction", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
