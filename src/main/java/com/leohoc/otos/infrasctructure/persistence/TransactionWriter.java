package com.leohoc.otos.infrasctructure.persistence;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.leohoc.otos.domain.entity.TransactionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class TransactionWriter {

    private static Logger LOGGER = LogManager.getLogger();
    private static final String BUCKET_NAME = "otos-transaction";

    public void persist(TransactionEntity transactionEntity) {

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        InputStream inputStream = new ByteArrayInputStream(transactionEntity.toCSV().getBytes());

        try {
            s3.putObject(BUCKET_NAME, transactionEntity.getCodePartition().toString(), inputStream, new ObjectMetadata());
        } catch (AmazonServiceException e) {
            LOGGER.error("m=persist", e);
        }
    }
}
