package com.example.resource;

import com.example.resource.aws.impl.EC2Resource;
import com.example.resource.aws.impl.LambdaResource;
import com.example.resource.aws.impl.S3Resource;
import com.example.resource.azure.AzureResource;

public class ResourceFactory {
    public Resource createResource(String resType) {
        Resource res = null;

        if (resType.equals("s3")) {
            res = new S3Resource();
        } else if (resType.equals("ec2")) {
            res = new EC2Resource();
        } else if (resType.equals("lambda")) {
            res = new LambdaResource();
        } else if (resType.equals("azure")) {
            res = new AzureResource();
        }
        return res;
    }
}
