package org.camunda.support.SUPPORT_15507;

import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstanceModificationBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("simple")
public class Simple implements JavaDelegate {

	
	@Autowired
	private RuntimeService runtimeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
		String oldPIstring = (String)execution.getVariable("procInstId");
		
		List<Execution> executions = runtimeService
				.createExecutionQuery().processInstanceId(oldPIstring)
			    .list();
		

        Map oldVariables = runtimeService.getVariables(executions.get(0).getId());
	
	
		ProcessInstantiationBuilder processInstantiationBuilder =
		//		runtimeService.createProcessInstanceByKey("processNew")
				runtimeService.createProcessInstanceByKey("SUPPORT-15507")
				.setVariables(oldVariables);

		
		List<String> oldActivities = runtimeService.getActiveActivityIds(oldPIstring);

		
		ProcessInstanceModificationBuilder processInstanceModificationBuilder =
				runtimeService.createProcessInstanceModification(oldPIstring);

		if (oldActivities.contains("Old_Task")) {
			processInstantiationBuilder.startBeforeActivity("New_Task");
			processInstanceModificationBuilder.cancelAllForActivity("Old_Task");
		}
		if (oldActivities.contains("Old_SubTask")) {
			processInstantiationBuilder.startBeforeActivity("New_SubTask");
			processInstanceModificationBuilder.cancelAllForActivity("Old_SubTask");
		}
		
		// Erstelle erst die GlobalenTasks in den passenden Zuständen
		processInstantiationBuilder.execute();
		// Dann breche die dazugehörigen Zustände ab
		processInstanceModificationBuilder.execute();
	
	}

}
