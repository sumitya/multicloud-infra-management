package com.example.client.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.example.client.ResourceClient;

public class LocalStackEC2Client implements ResourceClient {
    public AmazonEC2 getResourceClient() {

        AmazonEC2 ec2Client = AmazonEC2ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("foobar", "foobar")))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:4566", "us-east-1"))
                .build();

        return ec2Client;
    }
}
