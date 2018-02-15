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

public class OneClientCorrectReadWriteTestCase extends PromptTestCase {
	private boolean atomic;
	
	public OneClientCorrectReadWriteTestCase(boolean atomic) {
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
			if (anOutputBasedInputGenerator.isClientWriteComplete()) {
				correct++;
			} else {
				message.append("Improper client write.");
			}
			if (anOutputBasedInputGenerator.isServerReadComplete()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append(" ");
				}
				message.append("Improper server read.");
			}
			if (atomic) {
				if (anOutputBasedInputGenerator.isServerWriteComplete()) {
					correct++;
				} else {
					if (message.length() > 0) {
						message.append(" ");
					}
					message.append("Improper server write.");
				}
				if (anOutputBasedInputGenerator.isClientReadComplete()) {
					correct++;
				} else {
					if (message.length() > 0) {
						message.append(" ");
					}
					message.append("Improper client read.");
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
