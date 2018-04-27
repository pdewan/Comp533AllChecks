package gradingTools.comp533s18.assignment2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s18.assignment1.testcases.OneClientCorrectConnectionTestCase;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	OneClientCorrectConnectionTestCase.class,
})
public class Assignment2Suite {
}
