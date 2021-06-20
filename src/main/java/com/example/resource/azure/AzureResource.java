package com.example.resource.azure;

import com.example.resource.Resource;
import org.apache.log4j.Logger;

import java.util.List;

public class AzureResource implements Resource {
    private final Logger log = Logger.getLogger(AzureResource.class.getName());

    public Object provisionResource() {
        log.info("Provisioning an Azure resource");
        return new Object();
    }

    @Override
    public Object provisionResource(List<String> resources) {
        return null;
    }

    public void deProvisionResource() {
        log.info("DeProvisioning an Azure resource");
    }


    public void deProvisionResource(List<String> instanceIds) {
        log.info("DeProvisioning an Azure resource");
    }

    @Override
    public Object call() throws Exception {
        return provisionResource();
    }
}
