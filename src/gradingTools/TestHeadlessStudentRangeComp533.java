package gradingTools;

import java.util.Arrays;

public class TestHeadlessStudentRangeComp533 {
//	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 1";
	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp533Assignments\\S20\\Assignment 7";

//	static final String STUDENT = "sdgeorge";
//	static final String STUDENT1 = "kakiryan";
//	static final String STUDENT2 = "kila";
	static final String STUDENT1 = "^";
	static final String STUDENT2 = "ram385";

	static final String COURSE = "Comp53321";
	
	public static void main(String[] args) {
		String[] myArgs = {
				COURSE, ASSIGNMENT, STUDENT1, STUDENT2
		};
//		System.out.println ("Test headless student range\n" + Arrays.toString(myArgs));
		RunHeadlessStudentRange.main(myArgs);
	}

}
