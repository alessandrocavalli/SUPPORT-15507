package org.camunda.support.SUPPORT_15507;

import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sendMessage")
public class SendMessage implements JavaDelegate{
	
	  private final Logger LOGGER = Logger.getLogger(JustCorrelate.class.getName());
	
	  @Autowired
	  private RuntimeService runtimeService;
	  
	  @Override
	  public void execute(DelegateExecution execution) throws Exception {
		
		String helloVariable = (String)execution.getVariable("HELLOVARIABLE");
		
		LOGGER.info("Here is the variable : " + helloVariable);
		
		if(helloVariable== null) {
			throw new Exception("I reproduced!!!");
		}
		
	}

}
