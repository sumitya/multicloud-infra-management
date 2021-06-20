package com.example.client.impl;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.client.ResourceClient;

public class AWSS3Client implements ResourceClient {

    public AmazonS3 getResourceClient() {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(region)
                .build();

        return s3client;
    }


}
