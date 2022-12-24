# SUPPORT-15507
Spring Boot Application using [Camunda](http://docs.camunda.org).

Trying to reproduce this issue
[SUPPORT-15507](https://jira.camunda.com/browse/SUPPORT-15507).

## How does it work?
We tried to reproduce this issue, with no success:

### Versioning an existing process definition
1. Launch the application
2. Start a process instance (via Cockpit is ok) of the process definition SUPPORT-15507.
3. Open the modeler, and change the names of the user task Old_SubTask to New_SubTask and Old_Task to New_Task
4. Upload the process definition
5. Go in the cockpit, copy and paste somewhere the Process Instance Id (the one started at step 2)
6. Start a process intance (via Cockpit is ok) of the process definition named "simple", with a variable named "procInstId" and as value just paste the process instance id copied at the previous step.
7. Check the logs, and/or the new "migrated" PI, it still has the variables...

### Using another process definition
1. Delete the database
2. Go in the class simple.java and switch the "migrating to" process definition commenting/decommenting lines 37 and 38 
3. Build and Launch the application
4. Start a process instance (via Cockpit is ok) of the process definition SUPPORT-15507.
5. Go in the cockpit, copy and paste somewhere the Process Instance Id
6. Start a process intance (via Cockpit is ok) of the process definition named "simple", with a variable named "procInstId" and as value just paste the process instance id copied at the previous step.
7. Check the logs, and/or the new "migrated" PI, it still has the variables...

### Notes
#### The "simple.java" delegate just do what the custumer code does when migrating.
#### There is no need to send a messagge at the end of the subprocess, since in the customer's application it was failing before sending it, so a normal end event is ok.
#### In my opinion it is possible that the variables were not really copied in customer env. 

<!-- Tweet
New @Camunda example: Camunda Spring Boot Application - Spring Boot Application using [Camunda](http://docs.camunda.org). https://github.com/camunda-consulting/code/tree/master/snippets/SUPPORT-15507
-->
