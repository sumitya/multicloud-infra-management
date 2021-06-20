package com.example.task.scheduled;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunFrequentlyTask {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // For running every day at certain time., this is mainly for hosting the service.
        scheduler.scheduleAtFixedRate(new Task(0), 1, 0, TimeUnit.HOURS);

    }

    static class Task implements Runnable {
        private int num;

        public Task(int num) {
            this.num = num;
        }

        public void run() {
            System.out.println(
                    "Number " + num + " Current time : "
                            + Calendar.getInstance().get(Calendar.SECOND));
        }
    }
}
