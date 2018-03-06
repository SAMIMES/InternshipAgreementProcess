package main;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

public class GestionStage {

	public static void main(String[] args) {
		ProcessEngine processEngine=ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
		 
		RepositoryService repositoryService=processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("diagrams/GestionStage.bpmn").deploy();
	 		 
		RuntimeService runtimeService=processEngine.getRuntimeService();
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("gestionStage");
	 
	}

}