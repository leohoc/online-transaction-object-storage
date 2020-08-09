package com.leohoc.otos.infrasctructure.persistence;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CSVInput;
import com.amazonaws.services.s3.model.CSVOutput;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.StringInputStream;
import com.leohoc.otos.domain.entity.TransactionCode;
import com.leohoc.otos.domain.entity.TransactionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class TransactionPersistence {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String BUCKET_NAME = "otos-transaction";

    public void persist(TransactionEntity transaction) {

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        InputStream inputStream = new ByteArrayInputStream(transaction.getDescription().getBytes());

        try {
            s3.putObject(BUCKET_NAME, transaction.getTransactionCode().getS3KeyName(), inputStream, new ObjectMetadata());
        } catch (AmazonServiceException e) {
            LOGGER.error("m=persist", e);
        }
    }

    public TransactionEntity getTransactionBy(TransactionCode transactionCode) throws IOException {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        final S3Object s3Object = s3.getObject(BUCKET_NAME, transactionCode.getS3KeyName());
        final InputStreamReader streamReader = new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8);
        final BufferedReader bufferedReader = new BufferedReader(streamReader);
        return new TransactionEntity(transactionCode, bufferedReader.readLine());
    }
}
