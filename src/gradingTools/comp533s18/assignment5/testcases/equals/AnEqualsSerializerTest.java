package gradingTools.comp533s18.assignment5.testcases.equals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Assert;

import examples.serialization.SerializationTester;
import serialization.Serializer;
import serialization.SerializerFactory;
import serialization.SerializerSelector;
import serialization.simple.ASimpleSerializer;
import util.annotations.Comp533Tags;
import util.trace.Tracer;
import util.trace.port.serialization.extensible.ExtensibleSerializationTraceUtility;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.Project;
import gradingTools.comp533s18.assignment4.testcases.SubstringSequenceChecker;
import gradingTools.comp533s18.assignment5.testcases.ASerializerTest;
import gradingTools.comp533s18.assignment5.testcases.output.checks.ASerializationTraceChecker;
import gradingTools.shared.testcases.FactoryMethodTest;
import gradingTools.shared.testcases.ProxyTest;
import gradingTools.shared.testcases.utils.ALinesMatcher;
import gradingTools.shared.testcases.utils.LinesMatchKind;
import gradingTools.shared.testcases.utils.LinesMatcher;

public abstract class AnEqualsSerializerTest extends ASerializerTest {
	
	static AnEqualsSerializerTest singleton;
	protected Object originalObject;
	protected Object deserializedObject;

	protected abstract Object createSerializableObject();
	
	public AnEqualsSerializerTest() {
//		classTag = classTag();
//		checker = checker();
		
	}

	
	@Override
	protected void executeOperations(Object aProxy) throws Exception {
		originalObject = createSerializableObject();
		ExtensibleSerializationTraceUtility.setTracing();
		boolean prevBufferTracedMessages = Tracer.isBufferTracedMessages();
		createUsingFactoryMethod();
		Tracer.setBufferTracedMessages(false);

		deserializedObject = SerializationTester.translate(serializerProxy,originalObject);
		Tracer.setBufferTracedMessages(prevBufferTracedMessages);


		
		
	}
	
	@Override
	protected boolean checkOutput(Object aLocatable) {
		Assert.assertTrue("Original object " + originalObject + " not equal to deserialized object " + deserializedObject , originalObject.equals(deserializedObject));
		return true;
	}
	@Override
	protected boolean doTest() throws Exception {
//		createUsingFactoryMethod();
		executeOperations(rootProxy);
		return checkOutput(rootProxy);
		
//		output = getOutput();
//		
//		boolean aRetVal = checker().check(output);
//		Assert.assertTrue(checker().getRegex() + " not matched in output of TestSerialization", aRetVal);
//		return true;
	}

	
//	@Override
//	protected Class proxyClass() {
//		return SerializerFactory.class;
//	}
//	

	
//
//	@Override
//	protected void setActual(Object aProxy) {
//		// TODO Auto-generated method stub
//		
//	}

	
	
	


//	static AnAllSerializerTest singleton;
//	public static AnAllSerializerTest getSingleton() {
//		if (singleton == null) {
//			 singleton 
//		}
//		
//	}

}
