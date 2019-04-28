package gradingTools.comp533s18.assignment5.testcases;

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
import gradingTools.comp533s18.assignment5.testcases.output.checks.ASerializationTraceChecker;
import gradingTools.shared.testcases.FactoryMethodTest;
import gradingTools.shared.testcases.ProxyTest;
import gradingTools.shared.testcases.utils.ALinesMatcher;
import gradingTools.shared.testcases.utils.LinesMatchKind;
import gradingTools.shared.testcases.utils.LinesMatcher;

public abstract class ASerializerTest extends FactoryMethodTest {
	
//	static ASerializerTest singleton;

	


//	protected Map<String, Class> tagToSerializer;
	protected SerializerFactory serializerFactory;
	protected Serializer serializer;
	protected Serializer serializerProxy;
//	protected LinesMatcher linesMatcher;

	
	
	
	
	public ASerializerTest() {
//		classTag = classTag();
//		checker = checker();
		
	}
//	protected abstract SubstringSequenceChecker checker();
//	protected abstract String classTag();
	
	
	
	protected Object createUsingFactoryClassAndMethodTags() {
		if (serializerFactory == null ) {
			serializerFactory = (SerializerFactory) createInstance();
			serializerFactory = (SerializerFactory) BasicProjectIntrospection.
					forceCreateProxy(SerializerFactory.class, serializerFactory);
			serializer = serializerFactory.createSerializer();
			Assert.assertTrue(serializerFactory + " returned null instance", serializer != null);
			Assert.assertTrue(serializerFactory + " returned instance of " + ASimpleSerializer.class, !ASimpleSerializer.class.isInstance(serializer));
			serializerProxy = (Serializer) BasicProjectIntrospection.forceCreateProxy(Serializer.class, serializer); 
			rootProxy = serializerProxy;
			SerializerSelector.setSerializerFactory(serializerFactory);
		
		}
		return rootProxy;
	}
	
	
	
	
	
	@Override
	protected Class proxyClass() {
		return SerializerFactory.class;
	}
	

	

	@Override
	protected void setActual(Object aProxy) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
