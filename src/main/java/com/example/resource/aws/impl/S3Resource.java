package com.example.resource.aws.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.example.client.impl.LocalStackS3Client;
import com.example.resource.aws.AWSResource;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

public class S3Resource extends AWSResource implements Callable<Object> {
    private final Logger log = Logger.getLogger(S3Resource.class.getName());
    private AmazonS3 s3Client;
    private Bucket bucket;

    public S3Resource() {
        s3Client = new LocalStackS3Client().getResourceClient();

    }

    public Object provisionResource() {
        super.provisionResource();

        Boolean isExists = s3Client.doesBucketExistV2("test2");
        if(!isExists){
            bucket = s3Client.createBucket("test2");
            log.info("Created Bucket: " + bucket.getName());
            return bucket.getName();
        } else{
            log.info("S3 Bucket already exist, skipping creation");
            return new Boolean(isExists);
        }


    }

    @Override
    public Object provisionResource(List<String> resources) {
        return null;
    }

    public void deProvisionResource() {
        super.deProvisionResource();
        s3Client.deleteBucket("test1");
        log.info("Deleted Bucket: ");
    }

    @Override
    public void deProvisionResource(List<String> resources) {

    }

    public void display() {
    }

    @Override
    public Object call() throws Exception {
        return provisionResource();
    }
}
