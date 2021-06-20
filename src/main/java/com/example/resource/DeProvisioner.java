package com.example.resource;

import com.example.db.DBOperations;
import com.example.task.scheduled.RunOnceTask;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DeProvisioner {

    private DBOperations dbOperations;
    private final Logger log = Logger.getLogger(RunOnceTask.class.getName());
        /**
         * A. Scheduled DeProvisioning(after 12 hrs)
         *         // 1. query from table to see resource.
         *         // 2. get the current state of the resource.
         *         //      a. API to get the current state of all resource available in table. -- Use ResourceGroup - it works on tagging.
         *         // 3. For active resources, diff(current_time - resource_creation_time), if diff > threshold, then send email.
         *         // 4. Create SNS topic, send email to user.
         *
         *
         * B. On-demand DeProvisioning
         *  1. query from table to see resource list.
         *  2. Deprovision resource sequentially.
         *
         * */

        public void deProvision() {
            log.info("Resource DeProvisioning started at: " + new Date());
            dbOperations = new DBOperations();
            //  1. query from table to see resource map. (resource_type,List(resource_name) , ec2->List(i-123) )
            Map<String,List<String>> resourceMap = dbOperations.getResourcesFromDb();

            resourceMap.entrySet().forEach(entry -> {
                //String resourcName = entry.getKey();
                List<String> resourcesName = entry.getValue();
                // 2. Deprovision sequentially.
                Resource resource = new ResourceFactory().createResource(entry.getKey());
                resource.deProvisionResource(entry.getValue());
                log.info("DeProvisioning "+entry.getKey() + " " + entry.getValue());

                // 3. update state of resource in DB, with resource deporvisioned
                try {
                    dbOperations.updateStateInDB(resourcesName,"deprovisioned");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            dbOperations.closeDbConnection();

            log.info("Resource DeProvisioning finished at: " + new Date());
          }
}
