package gradingTools;

public class TestHeadlessOneStudentComp533 {
//	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 1";
//	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp533Assignments\\S20\\Assignment 7";
	static final String ASSIGNMENT_FOLDER = "C:\\Users\\dewan\\Downloads\\Assignment 4";

//	static final String STUDENT = "sdgeorge";
//	static final String STUDENT = "ram385";
	static final String STUDENT = "yanaga";

	static final String COURSE = "Comp533S22";
	static final String ASSIGNMENT = "Assignment4";
	
	public static void main(String[] args) {
		String[] myArgs = {
				COURSE, ASSIGNMENT, ASSIGNMENT_FOLDER, STUDENT
		};
		RunHeadlessOneStudent.main(myArgs);
	}

}
