package org.activiti.designer.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ProcessTestActivitiAdhoc {

	// private String filename =
	// "D:\\workspaces\\khall\\activiti\\sdasd\\src\\main\\resources\\diagrams\\MyProcess.bpmn";

	// @Rule
	// public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		// ProcessEngine processEngine =
		// ProcessEngines.getDefaultProcessEngine();

//		ProcessEngine p1 = ProcessEngineConfiguration
//				.createStandaloneInMemProcessEngineConfiguration()
//				.setDatabaseSchemaUpdate(
//						ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
//				.setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
//				.setJobExecutorActivate(true).buildProcessEngine();
//		System.out.println(p1);
		ProcessEngine p2 = ProcessEngineConfiguration
				.createStandaloneInMemProcessEngineConfiguration()
				.buildProcessEngine();
		System.out.println(p2);
		// RepositoryService repositoryService =
		// activitiRule.getRepositoryService();
		// repositoryService.createDeployment().addInputStream("activitiAdhoc.bpmn20.xml",
		// new FileInputStream(filename)).deploy();
		// RuntimeService runtimeService = activitiRule.getRuntimeService();
		// Map<String, Object> variableMap = new HashMap<String, Object>();
		// variableMap.put("name", "Activiti");
		// ProcessInstance processInstance =
		// runtimeService.startProcessInstanceByKey("activitiAdhoc",
		// variableMap);
		// assertNotNull(processInstance.getId());
		// System.out.println("id " + processInstance.getId() + " "
		// + processInstance.getProcessDefinitionId());
	}
}