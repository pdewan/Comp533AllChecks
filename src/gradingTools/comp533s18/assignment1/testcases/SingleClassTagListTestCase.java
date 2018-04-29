package gradingTools.comp533s18.assignment1.testcases;

import java.util.Arrays;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class SingleClassTagListTestCase extends BasicTestCase {
	private final String[] tags;
	public String[] getTags() {
		return tags;
	}


	protected boolean checkAllTags = true;
	
	
	public SingleClassTagListTestCase(String... aTags) {
//		super("Prompt printer test case");
		super("Single class tagged '" + Arrays.toString(aTags) + "' test case");
		this.tags = aTags;
	}
	
	public SingleClassTagListTestCase(boolean aCheckAllTags, String... aTags) {
		this(aTags);
		checkAllTags = aCheckAllTags;

	}
	
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		boolean aCurrentCheckAllTags = BasicProjectIntrospection.isCheckAllSpecifiedTags();
		if (!checkAllTags) {
			BasicProjectIntrospection.setCheckAllSpecifiedTags(checkAllTags);
		}
		Set<Class> aClasses = BasicProjectIntrospection.findClassesByTag(project, tags);
		if (!checkAllTags) {
			BasicProjectIntrospection.setCheckAllSpecifiedTags(aCurrentCheckAllTags);
		}
		if (checkAllTags) {
    	if (aClasses.size() == 1) {
    		return pass();
    	}
    	if (aClasses.size() > 1) {
    		return partialPass(0.5, "Multiple classes tagged:" + Arrays.toString(tags) + " " + aClasses);
    	}
		} else {
			if (aClasses.size() > 1) {
	    		return pass();
	    	}
		}
    	return fail("No class tagged: " + Arrays.toString(tags));
	}
}
