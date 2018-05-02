package gradingTools.comp533s18.assignment5.testcases.output;

import java.util.Arrays;

import org.junit.Assert;

import examples.serialization.SerializationTester;
import serialization.SerializerFactory;
import serialization.SerializerSelector;
import util.annotations.Comp533Tags;
import util.trace.Tracer;
import util.trace.port.serialization.extensible.ExtensibleSerializationTraceUtility;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.all.checks.AWholeNumberChecker;
import gradingTools.shared.testcases.FactoryMethodTest;
import gradingTools.shared.testcases.ProxyTest;

public abstract class AnOutputSerializerTest extends FactoryMethodTest {
	
	static AnOutputSerializerTest singleton;
	protected SubstringSequenceChecker checker;
	
	public AnOutputSerializerTest() {
		checker = checker();
	}
	protected abstract SubstringSequenceChecker checker();
	
	@Override
	public boolean doTest() {
		String anOutput = getOutput();
		boolean aRetVal = checker.check(anOutput);
		Assert.assertTrue(checker.getRegex() + " not matched in output of TestSerialization", aRetVal);
		return true;
	}
	abstract protected String[] proxyClassTags() ;

	
	@Override
	protected Class proxyClass() {
		return SerializerFactory.class;
	}
	

	@Override
	protected void executeOperations(Object aProxy) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setActual(Object aProxy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkOutput(Object aLocatable) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public String getOutput() {
		
		String aKey = Arrays.toString(proxyClassTags());
		output = (String) BasicProjectIntrospection.getUserObject(aKey);
		if (output == null) {
			ExtensibleSerializationTraceUtility.setTracing();

		SerializerFactory aSerializerFactory = (SerializerFactory) createInstance();
		SerializerSelector.setSerializerFactory(aSerializerFactory);
		BasicProjectExecution.redirectOutput();
		SerializationTester.testSerialization();		
		output = BasicProjectExecution.restoreAndGetOut();
		BasicProjectIntrospection.putUserObject(aKey, output);
		ExtensibleSerializationTraceUtility.setTracing(false);

		}
		
		return output;
	}
//	static AnAllSerializerTest singleton;
//	public static AnAllSerializerTest getSingleton() {
//		if (singleton == null) {
//			 singleton 
//		}
//		
//	}

}
