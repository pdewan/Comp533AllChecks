package gradingTools.comp533s18.assignment1.testcases;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
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

public class TwoClientMessageRatioTestCase extends BasicTestCase {
	
	
	boolean atomic = false;
	public TwoClientMessageRatioTestCase(boolean atomic) {
//		super("Prompt printer test case");
		super("Two client message ratios - " + (atomic ? "Atomic" : "Non-atomic") + " test case");
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			TwoClientMessageRatioTestInputGenerator anOutputBasedInputGenerator = new TwoClientMessageRatioTestInputGenerator(atomic);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 30,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> System.out.println("*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int possible = 6;
			int numSeen = anOutputBasedInputGenerator.getClient0WriteCount();
			int expected = 1;
			StringBuilder message = new StringBuilder();
			if (numSeen == expected) {
				correct++;
			} else {
				message.append("Incorrect number of client 0 writes (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getClient0ReadCount();
			expected = atomic ? 1 : 0;
			if (numSeen == expected) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client 1 reads (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getClient1ReadCount();
			expected = 1;
			if (numSeen == expected) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client 1 writes (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getClient1WriteCount();
			expected = 0;
			if (numSeen == expected) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client 0 reads (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getServerWriteCount();
			expected = atomic ? 2 : 1;
			if (numSeen == expected) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of server writes (saw " + numSeen + ", expected " + expected + ").");
			}
			numSeen = anOutputBasedInputGenerator.getServerReadCount();
			expected = 1;
			if (numSeen == expected) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of server reads (saw " + numSeen + ", expected " + expected + ").");
			}
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				return fail(message.toString());
			} else {
				return partialPass(((double)correct)/possible, message.toString());
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
		ExecutionSpecificationSelector.getExecutionSpecification().setGraderResourceReleaseTime("Client_0", 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().setGraderResourceReleaseTime("Client_1", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
