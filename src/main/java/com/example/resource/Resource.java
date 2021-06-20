package com.example.resource;

import java.util.List;
import java.util.concurrent.Callable;

public interface Resource extends Callable<Object> {

    public Object provisionResource();

    public Object provisionResource(List<String> resources);

    public void deProvisionResource();

    public void deProvisionResource(List<String> resources);
}
