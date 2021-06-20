package com.example.resource;

import com.example.db.DBOperations;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResourceProvisioner {
    private final Logger log = Logger.getLogger(ResourceProvisioner.class.getName());
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()-4);
    private List<Resource> allTasks = new ArrayList<>();
    private Connection conn;
    private DBOperations dbOperations;

    public void provision(List<String> resourceTypes) {
        log.info("Resource Provisioning started at: " + new Date());
        dbOperations = new DBOperations();
        Map<String, String> tags = new HashMap<>();
        tags.put("department", "engineering");
        tags.put("env", "dev");

        for (String resourceType : resourceTypes) {
            Resource resource = new ResourceFactory().createResource(resourceType);
            allTasks.add(resource);
        }

        try {
            List<Future<Object>> futures = executorService.invokeAll(allTasks);
            for (int j = 0; j < futures.size(); j++) {
                // @TODO - send param provisionUser from UI in future
                if(!futures.get(j).get().equals(true)){
                    dbOperations.registerResourceInDb(UUID.randomUUID().toString(),futures.get(j).get().toString(), resourceTypes.get(j), tags.toString());
                    log.info(resourceTypes.get(j) + " resource is provisioned");
                    dbOperations.registerStateInDb(futures.get(j).get().toString(),resourceTypes.get(j), "provisioned");
                }
            }

            dbOperations.closeDbConnection();
            log.info("Database connection is closed");

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred while executing the submitted task");
            e.printStackTrace();
        }

        executorService.shutdown();
        // @TODO - need to fix this
        //new TagResource("test1",tags).tagResourceGroups();

        log.info("Resource Provisioning finished at: " + new Date());
    }
}
