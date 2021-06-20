package com.example.client.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.resourcegroupstaggingapi.AWSResourceGroupsTaggingAPI;
import com.example.client.ResourceClient;

public class AWSGroupsTaggingAPIClient implements ResourceClient {

    public AWSResourceGroupsTaggingAPI getResourceClient() {

        AWSResourceGroupsTaggingAPI taggingAPI = com.amazonaws.services.resourcegroupstaggingapi.AWSResourceGroupsTaggingAPIClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("foobar", "foobar")))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:4566", "us-east-1"))
                .build();

        return taggingAPI;

    }
}
