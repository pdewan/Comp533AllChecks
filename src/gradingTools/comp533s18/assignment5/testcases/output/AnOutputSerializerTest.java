package gradingTools.comp533s18.assignment5.testcases.output;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import gradingTools.comp533s18.assignment5.testcases.output.checks.ASerializationTraceChecker;
import gradingTools.shared.testcases.FactoryMethodTest;
import gradingTools.shared.testcases.ProxyTest;

public abstract class AnOutputSerializerTest extends FactoryMethodTest {
	
	static AnOutputSerializerTest singleton;
//	protected SubstringSequenceChecker checker;
//	protected Class integerSerializerClass;
//	protected Class shortSerializerClass;
//	protected Class longSerializerClass;
//	protected Class doubleSerializerClass;
//	protected Class floatSerializerClass;
//	protected Class stringSerializerClass;
//	protected Class booleanSerializerClass;
//	protected Class enumSerializerClass;
//	protected Class arraySerializerClass;
//	protected Class collectionSerClass;
//	protected Class nullSerializerClass;
//	protected Class mapSerializerClass;
//	protected Class beanSerializerClass;
//	protected Class listSerializerClass;
	protected String[] tags = {
			Comp533Tags.BOOLEAN_SERIALIZER,
			Comp533Tags.INTEGER_SERIALIZER,
			Comp533Tags.BEAN_SERIALIZER,
			Comp533Tags.SHORT_SERIALIZER,
			Comp533Tags.LONG_SERIALIZER,
			Comp533Tags.DOUBLE_SERIALIZER,
			Comp533Tags.FLOAT_SERIALIZER,
			Comp533Tags.STRING_SERIALIZER,
			Comp533Tags.FLOAT_SERIALIZER,
			Comp533Tags.ENUM_SERIALIZER,
			Comp533Tags.ARRAY_SERIALIZER,
			Comp533Tags.LIST_PATTERN_SERIALIZER,			
			Comp533Tags.MAP_SERIALIZER,
			Comp533Tags.COLLECTION_SERIALIZER,
			Comp533Tags.NULL_SERIALIZER
			
	};
//	protected SubstringSequenceChecker checker;
//	protected String classTag;

	protected Map<String, Class> tagToSerializer;
	
	protected void initSerializers() {
		Project aProject = CurrentProjectHolder.getCurrentProject();
		tagToSerializer = new HashMap();
		for (String aTag:tags) {
			putClassForTag(aProject, aTag);
		}
		
		
	}
	
	protected void putClassForTag(Project aProject, String aTag) {
		Class aClass = BasicProjectIntrospection.findUniqueClassByTag(aProject, aTag);
		tagToSerializer.put(aTag, aClass);
		
	}
	
	
	public AnOutputSerializerTest() {
//		classTag = classTag();
//		checker = checker();
		
	}
	protected abstract SubstringSequenceChecker checker();
	protected abstract String classTag();
	
	protected Class taggedClass() {
		String aTag = classTag();
		tagToSerializer = getTagToSerializer();
		Class aClass = tagToSerializer.get(aTag);
		assertTrue("Unique class not found for tag " + aTag, aClass != null);
		return aClass;
	}
	
	@Override
	protected boolean doTest() {
		output = getOutput();
		
		boolean aRetVal = checker().check(output);
		Assert.assertTrue(checker().getRegex() + " not matched in output of TestSerialization", aRetVal);
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
	
	public static final String TAG_TO_MAP = "TAG_TO_MAP";
	
	public Map<String, Class> getTagToSerializer() {
		tagToSerializer = (Map<String, Class>) BasicProjectIntrospection.getUserObject(TAG_TO_MAP);
		if (tagToSerializer == null) {
			initSerializers();
			BasicProjectIntrospection.putUserObject(TAG_TO_MAP, tagToSerializer);			
		}
		return tagToSerializer;
	}
	

	@Override
	public String getOutput() {
		
		String aKey = Arrays.toString(proxyClassTags());
		output = (String) BasicProjectIntrospection.getUserObject(aKey);
		if (output == null) {
			ExtensibleSerializationTraceUtility.setTracing();
			boolean prevBufferTracedMessages = Tracer.isBufferTracedMessages();
			Tracer.setBufferTracedMessages(false);

		SerializerFactory aSerializerFactory = (SerializerFactory) createInstance();
		aSerializerFactory = (SerializerFactory) BasicProjectIntrospection.
				forceCreateProxy(SerializerFactory.class, aSerializerFactory);
		Serializer aSerializer = aSerializerFactory.createSerializer();
		Assert.assertTrue(aSerializerFactory + " returned null instance", aSerializer != null);
		Assert.assertTrue(aSerializerFactory + " returned instance of " + ASimpleSerializer.class, !ASimpleSerializer.class.isInstance(aSerializer));
		Serializer aSerializerProxy = (Serializer) BasicProjectIntrospection.forceCreateProxy(Serializer.class, aSerializer); 
//		SerializerSelector.setSerializerFactory(aSerializerFactory);
		BasicProjectExecution.redirectOutput();
		SerializationTester.testSerialization(aSerializerProxy);		
		output = BasicProjectExecution.restoreAndGetOut();
		BasicProjectIntrospection.putUserObject(aKey, output);
		ExtensibleSerializationTraceUtility.setTracing(false);
		Tracer.setBufferTracedMessages(prevBufferTracedMessages);

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
