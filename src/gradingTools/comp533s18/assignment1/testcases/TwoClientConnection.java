package gradingTools.comp533s18.assignment1.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.config.ExecutionSpecificationSelector;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.MaxValue;
import util.trace.Tracer;
@MaxValue(20)
public class TwoClientConnection extends BasicTestCase {
	
	
	public TwoClientConnection() {
//		super("Prompt printer test case");
		super("Two client correct connection test case");
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			TwoClientCorrectConnectionTestInputGenerator anOutputBasedInputGenerator = new TwoClientCorrectConnectionTestInputGenerator();
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 40,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			
			if (anOutputBasedInputGenerator.isEnableAcceptComplete()) {
				if (anOutputBasedInputGenerator.areConnectsComplete()) {
					if (anOutputBasedInputGenerator.areAcceptsComplete()) {
						return pass();
					} else {
						System.out.println("Accept 0: " + anOutputBasedInputGenerator.isAccepted0Complete());
						System.out.println("Accept 1: " + anOutputBasedInputGenerator.isAccepted1Complete());
						return partialPass(0.66, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());					
//						return partialPass(0.66, "Server failed to accept connection from at least one client");					
					}
				} else {
					System.out.println("Connect 0: " + anOutputBasedInputGenerator.isConnect0Complete());
					System.out.println("Connect 1: " + anOutputBasedInputGenerator.isConnect1Complete());
					return partialPass(0.33, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//					return partialPass(0.33, "At least one client failed to connect to server");
				}
			} else {
				return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//				return fail("Server failed to accept connections");
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void setupProcesses() {
		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client_0", "Client_1"));
		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client_0", "Client_1"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", StaticArguments.DEFAULT_SERVER_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client_0", StaticArguments.DEFAULT_CLIENT_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client_1", StaticArguments.DEFAULT_CLIENT_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setGraderResourceReleaseTime("Server", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().setGraderResourceReleaseTime("Client_0", 15000);
		ExecutionSpecificationSelector.getExecutionSpecification().setGraderResourceReleaseTime("Client_1", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));

	}
}
