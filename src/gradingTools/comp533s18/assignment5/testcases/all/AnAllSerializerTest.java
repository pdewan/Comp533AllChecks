package gradingTools.comp533s18.assignment5.testcases.all;

import java.util.Arrays;

import examples.serialization.SerializationTester;
import serialization.SerializerFactory;
import serialization.SerializerSelector;
import util.annotations.Comp533Tags;
import util.trace.Tracer;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.shared.testcases.FactoryMethodTest;
import gradingTools.shared.testcases.ProxyTest;

public abstract class AnAllSerializerTest extends FactoryMethodTest {
	static AnAllSerializerTest singleton;
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
		SerializerFactory aSerializerFactory = (SerializerFactory) createInstance();
		SerializerSelector.setSerializerFactory(aSerializerFactory);
		BasicProjectExecution.redirectOutput();
		SerializationTester.testSerialization();		
		output = BasicProjectExecution.restoreAndGetOut();
		BasicProjectIntrospection.putUserObject(aKey, output);
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
