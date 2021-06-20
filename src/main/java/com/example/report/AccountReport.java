package com.example.report;

public class AccountReport {

    //this information would come from database.

    // 1. query from table to see resource.
    // 2. get the current state of the resource.
    //      a. API to get the current state of all resource available in table. -- Use ResourceGroup - it works on tagging.
    // 3. For active resources, diff(current_time - resource_creation_time), if diff > threshold, then send email.
    // 4. Create SNS topic, send email to user.
}
