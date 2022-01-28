package gradingTools;

import gradingTools.gradebook.GradebookGradescopeConverter;

public class Comp533GradeBookToGradesScopeFileGenerator {
	static String[] onyensToEmail = new String[] {
		
	};
//	public static final String GRADEBOOK_TEMPLATE_FILE_NAME = 
//			"D:\\UNCGoogleDrive\\533\\grades\\gradebook_export-template.csv";
//	public static final String GRADEBOOK_TEMPLATE_FILE_NAME = 
//			"G:\\My Drive\\533\\grades\\gradebook_export-template.csv";
	public static final String GRADES_DIR =  "G:\\My Drive\\533\\grades\\s21\\";
	public static final String GRADEBOOK_TEMPLATE_FILE_NAME = 
//			"G:\\My Drive\\401-f15\\grades\\f18\\"
			GRADES_DIR
			+ "gradebook_export-template.csv";
	
	public static void main (String[] args) {
		GradebookGradescopeConverter.gradebookToGradescope(
				GRADEBOOK_TEMPLATE_FILE_NAME, 
				GRADES_DIR +
				"gradescope_import-S20.csv",
//				"D:\\UNCGoogleDrive\\533\\grades\\gradescope_import-S18.csv",				
				onyensToEmail);
		
	 
	}

}
