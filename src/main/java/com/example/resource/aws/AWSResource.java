package com.example.resource.aws;

import com.example.resource.Resource;
import org.apache.log4j.Logger;

public abstract class AWSResource implements Resource {
    private final Logger log = Logger.getLogger(AWSResource.class.getName());

    public Object provisionResource() {
        log.info("Provisioning an AWS resource");
        return new Object();
    }

    public void deProvisionResource() {
        log.info("DeProvisioning an AWS resource");
    }

    public abstract void display();
}
