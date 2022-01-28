package gradingTools;

public class HeadlessOneStudentComp533S21 {
//	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp524F20Assignments\\Assignment 1";
	static final String ASSIGNMENT = "C:\\Users\\dewan\\Downloads\\Comp533Assignments\\S20\\Assignment 7";
	

//	static final String STUDENT = "sdgeorge";
//	static final String STUDENT = "ram385";
	static final String COURSE = "Comp533S21";
	
	public static void main(String[] args) {
		String[] myArgs = {
				COURSE, args[0], args[1]
		};
		RunHeadlessOneStudent.main(myArgs);
	}

}
