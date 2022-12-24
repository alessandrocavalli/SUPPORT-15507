package org.camunda.support.SUPPORT_15507;

import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is an easy adapter implementation
 * illustrating how a Java Delegate can be used
 * from within a BPMN 2.0 Service Task.
 */
@Component("correlate")
public class JustCorrelate implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(JustCorrelate.class.getName());
  
  @Autowired
  private RuntimeService runtimeService;
  
  public void execute(DelegateExecution execution) throws Exception {
    
	  String messageName = "message1";
	  	  
	  runtimeService.createMessageCorrelation(messageName)
      .processInstanceId(execution.getProcessInstanceId())
      .setVariable("HELLOVARIABLE", "Hi")
      .correlate();

  }

}
