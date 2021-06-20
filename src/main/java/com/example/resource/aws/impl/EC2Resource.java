package com.example.resource.aws.impl;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.example.client.impl.LocalStackEC2Client;
import com.example.resource.aws.AWSResource;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class EC2Resource extends AWSResource implements Callable<Object> {
    private final Logger log = Logger.getLogger(EC2Resource.class.getName());
    AmazonEC2 ec2Client;

    public EC2Resource() {
        ec2Client = new LocalStackEC2Client().getResourceClient();
    }

    public Object provisionResource() {
        super.provisionResource();
        RunInstancesRequest runInstancesRequest =
                new RunInstancesRequest();

        runInstancesRequest
                .withInstanceType(InstanceType.T1Micro)
                .withMinCount(1)
                .withMaxCount(1)
                .withKeyName("my-key-pair")
                .withSecurityGroups("my-security-group");

        RunInstancesResult runInstancesResult = ec2Client.runInstances(runInstancesRequest);
        Instance instance = runInstancesResult.getReservation().getInstances().get(0);
        log.info("EC2 Instance Creation Request Submitted with instanceId: " + instance.getInstanceId());
        return instance.getInstanceId();
    }

    public Object provisionResource(List<String> resources) {
        return null;
    }

    public void deProvisionResource(List<String> instanceIds) {
        // @TODO list '[i-1d9a8fb5a7a48d681']' does not exist, handle it

        super.deProvisionResource();
        DescribeInstancesResult res= ec2Client.describeInstances(new DescribeInstancesRequest().withInstanceIds());

        StopInstancesRequest stopInstancesRequest = new StopInstancesRequest()

                .withInstanceIds(instanceIds);
        ec2Client.stopInstances(stopInstancesRequest);
    }

    public void display() {
    }

    @Override
    public Object call() throws Exception {
        return provisionResource();
    }
}
