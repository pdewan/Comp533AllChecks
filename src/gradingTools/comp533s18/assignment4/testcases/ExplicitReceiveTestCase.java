package gradingTools.comp533s18.assignment4.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import org.junit.Test;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.execution.ExecutionSpecificationSelector;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.comp110.assignment4.Assignment4Requirements;
import gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Comp533Tags;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name ")
public class ExplicitReceiveTestCase extends BasicTestCase {
	
	
	
	public ExplicitReceiveTestCase() {
		super("Async Receive Test Case");


	}
	
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		setupProcesses();
		String incOutput = "";
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			EcplicitReceiveTestInputGenerator anOutputBasedInputGenerator = 
					new EcplicitReceiveTestInputGenerator();
			RunningProject interactiveInputProject = null;
			try {
				interactiveInputProject = RunningProjectUtils.runProject(project, 25, anOutputBasedInputGenerator);
				 incOutput = interactiveInputProject.await();
			} catch (Exception e){
				
			}
			if (interactiveInputProject != null) {
				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			}
			
			if (!anOutputBasedInputGenerator.isNonsenseSetupComplete()) {
			
				return partialPass(0.80, "No nonsense");
			}
			
			return fail("No nonsense");
			
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	public static final String GIPC_PROCESS_TEAM = "GIPCDistributedProgram";
//	public static final String CLIENT_0 = "Client_0";
//	public static final String CLIENT_1 = "Client_1";


	
	protected void setupProcesses() {
		ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList(GIPC_PROCESS_TEAM));
		ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses(GIPC_PROCESS_TEAM, 
				Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
		ExecutionSpecificationSelector.getExecutionSpecification().setProcesses(GIPC_PROCESS_TEAM, 
				Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_SERVER, Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags(
				Comp533Tags.EXPLICIT_RECEIVE_SERVER, Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_SERVER));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags(
				Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1));
		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags(
				Comp533Tags.EXPLICIT_RECEIVE_CLIENT2, Arrays.asList(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2));
		
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime(Comp533Tags.EXPLICIT_RECEIVE_CLIENT1, 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime(Comp533Tags.EXPLICIT_RECEIVE_CLIENT2, 5000);
		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
