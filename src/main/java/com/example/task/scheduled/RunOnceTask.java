package com.example.task.scheduled;

import com.example.resource.DeProvisioner;
import com.example.resource.ResourceProvisioner;
import org.apache.log4j.Logger;

import java.util.*;

public class RunOnceTask extends TimerTask {
    private final Logger log = Logger.getLogger(RunOnceTask.class.getName());
    static List<String> resourceTypes;
    String activityType;

    public RunOnceTask(String activityType){
        this.activityType = activityType;
    }

    @Override
    public void run() {
        log.info("Started the Application...");
        if(activityType.equals("P")){
            // @TODO the parameter to provision would arrive from UI
            new ResourceProvisioner().provision(resourceTypes);

        } else{
            new DeProvisioner().deProvision();
        }

        completeTask();

        log.info("Completed the Application...");

    }

    private void completeTask() {
        try {
            //assuming it takes 5 secs to complete the task
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Timer timer = null;
        resourceTypes = new ArrayList<String>();
        for(int i = 1 ; i < 20 ; i++){
            resourceTypes.add("s3");
            resourceTypes.add("ec2");
            //resourceTypes.add("lambda");
            TimerTask timerTask = new RunOnceTask("D");
            timer = new Timer(true);
            timer.schedule(timerTask, 5);

            //cancel after sometime
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        timer.cancel();
    }
}
