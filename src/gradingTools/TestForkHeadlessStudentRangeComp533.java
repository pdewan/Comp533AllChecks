package gradingTools;

import grader.basics.execution.BasicProjectExecution;

public class TestForkHeadlessStudentRangeComp533 {
//	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 1";
	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 0-1";

//	static final String STUDENT = "sdgeorge";
//	static final String STUDENT = "kakiryan";
	static final String COURSE = "Comp524F20";
	static final String STUDENT1 = "^";
	static final String STUDENT2 = "kyuyeon";
	
	public static void main(String[] args) {
		String[] myArgs = {
				COURSE, ASSIGNMENT, STUDENT1, STUDENT2
		};
		BasicProjectExecution.runProject(TestHeadlessStudentRangeComp533.class, myArgs, false);
	}

}
