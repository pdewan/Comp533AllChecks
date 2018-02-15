package gradingTools.comp533s18.assignment1.testcases;

import java.util.Arrays;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.utils.RunningProjectUtils;

public class OneClientCorrectValuesTestCase extends PromptTestCase {
	private boolean atomic;
	
	public OneClientCorrectValuesTestCase(boolean atomic) {
//		super("Prompt printer test case");
		super();
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			OneClientCorrectReadWriteTestInputGenerator anOutputBasedInputGenerator = new OneClientCorrectReadWriteTestInputGenerator(atomic);
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
			
			int correct = 0;
			int possible = atomic ? 4 : 2;
			StringBuilder message = new StringBuilder();
			if (anOutputBasedInputGenerator.clientWriteMatchesCorrect()) {
				correct++;
			} else {
				message.append("Client writes wrong value.");
			}
			if (anOutputBasedInputGenerator.serverReadMatchesClientWrite()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Server read doesn't match client write.");
			}
			if (atomic) {
				if (anOutputBasedInputGenerator.serverWriteMatchesRead()) {
					correct++;
				} else {
					if (message.length() > 0) {
						message.append(" ");
					}
					message.append("Server write doesn't match server read.");
				}
				if (anOutputBasedInputGenerator.clientReadMatchesServerWrite()) {
					correct++;
				} else {
					if (message.length() > 0) {
						message.append(" ");
					}
					message.append("Client read doesn't match server write.");
				}
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
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", Arrays.asList());
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client", Arrays.asList());
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client", 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));

	}
}
