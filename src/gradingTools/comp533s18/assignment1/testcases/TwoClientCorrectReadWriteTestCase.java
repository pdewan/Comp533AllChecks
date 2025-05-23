package gradingTools.comp533s18.assignment1.testcases;

import java.util.Arrays;

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
import util.trace.Tracer;

public class TwoClientCorrectReadWriteTestCase extends BasicTestCase {
	private boolean atomic;
	
	public TwoClientCorrectReadWriteTestCase(boolean atomic) {
//		super("Prompt printer test case");
		super("Two client correct read write - " + (atomic ? "Atomic" : "Non-atomic") + " test case");
		this.atomic = atomic;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			TwoClientCorrectReadWriteTestInputGenerator anOutputBasedInputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic);
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 45,
						anOutputBasedInputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int possible = 4;
//			StringBuilder message = new StringBuilder();
			if (anOutputBasedInputGenerator.isClientWriteComplete()) {
				correct++;
			} else {
//				message.append("Improper client write.");
			}
			if (anOutputBasedInputGenerator.isServerReadComplete()) {
				correct++;
			} else {
//				if (message.length() > 0) {
//					message.append(" ");
//				}
//				message.append("Improper server read.");
			}
			if (anOutputBasedInputGenerator.areServerWritesComplete()) {
				correct++;
			} else {
//				if (message.length() > 0) {
//					message.append(" ");
//				}
//				message.append("Improper server write(s).");
			}
			if (anOutputBasedInputGenerator.areClientReadsComplete()) {
				correct++;
			} else {
//				if (message.length() > 0) {
//					message.append(" ");
//				}
//				message.append("Improper client read(s).");
			}
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				return fail("In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//				return fail(message.toString());
			} else {
				return partialPass(((double)correct)/possible, "In " + anOutputBasedInputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + anOutputBasedInputGenerator.getLastNotFound());
//				return partialPass(((double)correct)/possible, message.toString());
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private void setupProcesses() {
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
