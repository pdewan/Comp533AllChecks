package gradingTools.comp533s18.assignment4.testcases;

import java.util.Arrays;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Comp533Tags;
import util.trace.Tracer;

public class TwoClientCorrectReadWriteTestCase extends BasicTestCase {
	private boolean atomic;
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	private static int RUNTIME = 60;
	
	private static String formatName(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
		StringBuilder sb = new StringBuilder("Two client correct read write test case - ");
		if (atomic) {
			sb.append("Atomic");
		} else {
			sb.append("Non-atomic");
		}
		sb.append(" (using");
		boolean hasMethod = false;
		if (doNIO) {
			hasMethod = true;
			sb.append(" NIO");
		}
		if (doRMI) {
			if (hasMethod) {
				sb.append(",");
			}
			sb.append(" RMI");
		}
		if (doGIPC) {
			if (hasMethod) {
				sb.append(",");
			}
			sb.append(" GIPC");
		}
		sb.append(")");
		
		return sb.toString();
	}
	
	public TwoClientCorrectReadWriteTestCase(boolean atomic, boolean doNIO, boolean doRMI, boolean doGIPC) {
		super(formatName(atomic, doNIO, doRMI, doGIPC));
		this.atomic = atomic;

		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
		outputBasedInputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC);

	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
//			TwoClientCorrectReadWriteTestInputGenerator aTwoClientOutputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC);
//			outputBasedInputGenerator = new TwoClientCorrectReadWriteTestInputGenerator(atomic, doNIO, doRMI, doGIPC);
			outputBasedInputGenerator.clear();
			TwoClientCorrectReadWriteTestInputGenerator aTwoClientOutputGenerator = (TwoClientCorrectReadWriteTestInputGenerator) outputBasedInputGenerator;

			interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, RUNTIME,
						aTwoClientOutputGenerator);
				String incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			int correct = 0;
			int possible = atomic ? 4 : 2;
			int[] scoring = new int[] {0,0};
			if (doNIO) {
				check(scoring, aTwoClientOutputGenerator.isClient0NIOWriteComplete());
				check(scoring, aTwoClientOutputGenerator.isServerNIORead0Complete());
				check(scoring, aTwoClientOutputGenerator.isServerNIOWrite1Complete());
				check(scoring, aTwoClientOutputGenerator.isClient1NIOReadComplete());
				if (atomic) {
					check(scoring, aTwoClientOutputGenerator.isServerNIOWrite0Complete());
					check(scoring, aTwoClientOutputGenerator.isClient0NIOReadComplete());
				}
			}
			if (doRMI) {
				check(scoring, aTwoClientOutputGenerator.isClient0RMIWriteComplete());
				check(scoring, aTwoClientOutputGenerator.isServerRMIRead0Complete());
				check(scoring, aTwoClientOutputGenerator.isServerRMIWrite1Complete());
				check(scoring, aTwoClientOutputGenerator.isClient1RMIReadComplete());
				if (atomic) {
					check(scoring, aTwoClientOutputGenerator.isServerRMIWrite0Complete());
					check(scoring, aTwoClientOutputGenerator.isClient0RMIReadComplete());
				}
			}
			if (doGIPC) {
				check(scoring, aTwoClientOutputGenerator.isClient0GIPCWriteComplete());
				check(scoring, aTwoClientOutputGenerator.isServerGIPCRead0Complete());
				check(scoring, aTwoClientOutputGenerator.isServerGIPCWrite1Complete());
				check(scoring, aTwoClientOutputGenerator.isClient1GIPCReadComplete());
				if (atomic) {
					check(scoring, aTwoClientOutputGenerator.isServerGIPCWrite0Complete());
					check(scoring, aTwoClientOutputGenerator.isClient0GIPCReadComplete());
				}
			}
			correct = scoring[0];
			possible = scoring[1];
			if (correct == possible) {
				return pass();
			} else if (correct == 0) {
				return fail("In " + aTwoClientOutputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + aTwoClientOutputGenerator.getLastNotFound());
			} else {
				return partialPass(((double)correct)/possible, "In " + aTwoClientOutputGenerator.getLastNotFoundSource() + ", no line found matching regex: " + aTwoClientOutputGenerator.getLastNotFound());
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	private static void check(int[] scoring, boolean condition) {
		scoring[1] ++;
		if (condition) {
			scoring[0] ++;
		}
	}
	
	private static void setupProcesses() {
		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server", Comp533Tags.CUSTOM_IPC));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client", Comp533Tags.CUSTOM_IPC));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client", Comp533Tags.CUSTOM_IPC));
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Registry", StaticArgumentsTestCase.TEST_REGISTRY_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.TEST_SERVER_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client_0", StaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client_1", StaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Registry", 500);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 2000);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client_0", 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client_1", 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
