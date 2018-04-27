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
import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.utils.RunningProjectUtils;
import util.trace.Tracer;

public class OneClientMessageRatioTestCase extends BasicTestCase {
	
	boolean atomic = false;
	public OneClientMessageRatioTestCase(boolean atomic) {
//		super("Prompt printer test case");
		super("One client message ratios - " + (atomic ? "Atomic" : "Non-atomic") + " test case");
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			OneClientMessageRatioTestInputGenerator anOutputBasedInputGenerator = new OneClientMessageRatioTestInputGenerator(atomic);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 15,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int possible = 4;
			
			int numSeen = anOutputBasedInputGenerator.getClientWriteCount();
			int expected = 1;
			StringBuilder message = new StringBuilder();
			if (numSeen == expected) {
				correct++;
			} else {
				message.append("Incorrect number of client writes (saw " + numSeen + ", expected " + expected + ").");
			}
			
			numSeen = anOutputBasedInputGenerator.getClientReadCount();
			expected = atomic ? 1 : 0;
			if (numSeen == expected) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Incorrect number of client reads (saw " + numSeen + ", expected " + expected + ").");
			}
			
			numSeen = anOutputBasedInputGenerator.getServerWriteCount();
			expected = atomic ? 1 : 0;
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
		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.DEFAULT_SERVER_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client", StaticArgumentsTestCase.DEFAULT_CLIENT_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client", 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
