package com.example.resource.aws.impl;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.example.client.impl.LocalStackEC2Client;
import com.example.client.impl.LocalStackLambdaClient;
import com.example.resource.aws.AWSResource;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

public class LambdaResource extends AWSResource implements Callable<Object> {
    private final Logger log = Logger.getLogger(EC2Resource.class.getName());
    AWSLambda lambdaClient;

    public LambdaResource() {
        lambdaClient = new LocalStackLambdaClient().getResourceClient();
    }

    public Object provisionResource() {

        // @TODO - for now, just starting the existing function. Need to create lambda first.
        InvokeRequest req = new InvokeRequest()
                .withFunctionName("myFunctionName")
                .withPayload("{ ... }"); // optional

        InvokeResult result = lambdaClient.invoke(req);
        log.info("Lambda Function started ");
        return result.getLogResult();
    }

    public Object provisionResource(List<String> resources) {
        return null;
    }

    public void deProvisionResource(List<String> resources) {
        super.deProvisionResource();
        /// other deprovision code goes here
    }

    public void display() {}

    @Override
    public Object call() throws Exception {
        return provisionResource();
    }
}
