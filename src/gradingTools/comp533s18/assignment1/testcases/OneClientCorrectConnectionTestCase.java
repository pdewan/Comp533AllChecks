package gradingTools.comp533s18.assignment1.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.utils.RunningProjectUtils;

public class OneClientCorrectConnectionTestCase extends PromptTestCase {
	
	
	public OneClientCorrectConnectionTestCase() {
//		super("Prompt printer test case");
		super();

	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			OneClientCorrectConnectionTestInputGenerator anOutputBasedInputGenerator = new OneClientCorrectConnectionTestInputGenerator();
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 15,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> System.out.println("*** " + name + " ***\n" + output));
			}
			
			if (anOutputBasedInputGenerator.isEnableAcceptComplete()) {
				if (anOutputBasedInputGenerator.isConnectComplete()) {
					if (anOutputBasedInputGenerator.isAcceptComplete()) {
						return pass();
					} else {
						return partialPass(0.66, "Server failed to accept connection from client");
					}
				} else {
					return partialPass(0.33, "Client failed to connect to server");
				}
			} else {
				return fail("Server failed to accept connections");
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void setupProcesses() {
		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", Arrays.asList());
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client", Arrays.asList());
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client", 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
