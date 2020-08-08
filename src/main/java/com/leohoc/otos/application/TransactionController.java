package com.leohoc.otos.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private static Logger LOGGER = LogManager.getLogger();

    @PutMapping(value = "/transactions")
    public ResponseEntity putTransaction(String code, String description) {

        LOGGER.info("m=putTransaction, code={}, description={}", code, description);

        return new ResponseEntity(HttpStatus.OK);
    }

}
