package gradingTools;

import grader.basics.execution.BasicProjectExecution;

public class TestForkHeadlessOneStudentComp533 {
//	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 1";
	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 0-1";

//	static final String STUDENT = "sdgeorge";
	static final String STUDENT = "kakiryan";
	static final String COURSE = "Comp524F20";
	
	public static void main(String[] args) {
		String[] myArgs = {
				COURSE, ASSIGNMENT, STUDENT
		};
//		BasicProjectExecution.runProject(TestHeadlessOneStudent.class, myArgs, false);
		BasicProjectExecution.runProcess(TestHeadlessOneStudentComp533.class, myArgs, true);

	}

}
