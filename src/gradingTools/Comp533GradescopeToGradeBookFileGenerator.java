package gradingTools;

import java.io.File;
import java.util.Map;

import gradingTools.gradebook.GradebookGradescopeConverter;

public class Comp533GradescopeToGradeBookFileGenerator {
//	static String[] onyensToEmail = new String[] {
//		"whglaser:whglaser@ad.unc.edu",
//		"ellenecs:ellenecs@email.unc.edu"
//	};
//	public static final String GRADES_FOLDER = "G:\\My Drive\\533\\grades\\";
	public static final String GRADES_FOLDER = Comp533GradeBookToGradesScopeFileGenerator.GRADES_DIR;


//	public static final String GRADESCOPE_FILE_NAME = "Comp533_S20_Final_scores.csv";
//	public static final String SAKAI_FILE_NAME = "Final_scores_S20_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "Final";
	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_0_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_0_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_0_Sakai";
	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_1_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_1_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_1_Sakai";
	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_2_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_2_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_2_Sakai";
	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_3_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_3_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_3_Sakai";
	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_4_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_4_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_4_Sakai";

//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_5_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_5_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_5_Sakai";
	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_6_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_6_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_6_Sakai";
//	
//	public static final String GRADESCOPE_FILE_NAME = "G_Assignment_7_scores.csv";
//	public static final String SAKAI_FILE_NAME = "G_Assignment_7_Sakai.csv";
//	public static final String GRADE_COLUMN_NAME = "G_Assignment_7_Sakai";
	
	public static final String GRADESCOPE_FILE_NAME = "Midterm_scores.csv";
	public static final String SAKAI_FILE_NAME = "Midterm_Sakai.csv";
	public static final String GRADE_COLUMN_NAME = "Midterm_Sakai";

	
	static String[] onyensToEmail = new String[] {
//		"whglaser:whglaser@ad.unc.edu"
	};

	public static void main (String[] args) {
//		Map aMap = GradebookConverter.gradeBookToMap(new File("D:\\UNCGoogleDrive\\401-f15\\grades\\gradebook_export-F17 -Template.csv"));
		GradebookGradescopeConverter.gradescopeToGradebook(
				GRADES_FOLDER + GRADESCOPE_FILE_NAME, 
				GRADES_FOLDER + SAKAI_FILE_NAME,
				Comp533GradeBookToGradesScopeFileGenerator.GRADEBOOK_TEMPLATE_FILE_NAME,
				onyensToEmail,
				GRADE_COLUMN_NAME);
		
	 
	}

}
