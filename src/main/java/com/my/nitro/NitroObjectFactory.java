package com.my.nitro;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.konylabs.middleware.dataobject.Result;
import com.my.nitro.decorators.NitroDecoratorIntf;
import com.my.nitro.decorators.NitroExecutionDecorator;
import com.my.nitro.decorators.NitroSessionActiveDecorator;
import com.my.nitro.exceptions.NitroMethodNotFoundException;
import com.my.nitro.exceptions.NitroParameterNotFoundException;
import com.my.nitro.services.annotations.NitroDecorator;
import com.my.nitro.services.annotations.NitroParameter;
import com.my.nitro.services.annotations.NitroServiceMethod;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

public class NitroObjectFactory {
	
	private HashMap<String,Object> mapOfValues=null;
	private NitroDecoratorIntf decorator=null;
	private String methodId;
	
	final public Result invoke(NitroService nitroService, String methodId, Object[] inputObjects) throws Exception {
		this.methodId=methodId;
		initialize(inputObjects);
		initializeFieldAnnotations(nitroService); // injected object with the annotated fields
		MethodBean method=initializeMethod(nitroService);
		Result returnResult = decorator.execute(mapOfValues, method);
		return returnResult;
	}

	/**
	 * @param nitroService2
	 * @param inputObjects
	 * @throws IllegalAccessException 
	 */
	private void initializeFieldAnnotations(NitroService nitroService) throws NitroParameterNotFoundException {
		Field [] fields=nitroService.getClass().getDeclaredFields();
		for(Field field: fields){
			if(field.isAnnotationPresent(NitroParameter.class)){
				NitroParameter param=field.getAnnotation(NitroParameter.class);
				field.setAccessible(true);
				try{
					field.set(nitroService, readFromListOfValues(param.value()));
				}catch(Exception e){
					throw new NitroParameterNotFoundException(String.format("Unable to find parameter : %s in method : %s : class %s", param.value().toString(), this.methodId, this.getClass()));
				}
			}
		}
		
	}

	/**
	 * @param nitroService
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws NitroMethodNotFoundException 
	 * @throws NitroParameterNotFoundException 
	 */
	private MethodBean initializeMethod(NitroService nitroService) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, NitroMethodNotFoundException, NitroParameterNotFoundException {
		Method[] methods = nitroService.getClass().getDeclaredMethods();
		for (Method method : methods) {
				Annotation[] annotations = method.getDeclaredAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof NitroServiceMethod && ((NitroServiceMethod)annotation).value().equalsIgnoreCase(this.methodId)) {
						List<Object> generatedParameters = new ArrayList<Object>();
						Parameter[] methodParameters = method.getParameters();
						for (Parameter methodParameter : methodParameters) 
						{
							Annotation[] methodParameterAnnotations = methodParameter.getAnnotations();
							for (Annotation methodParameterAnnotation : methodParameterAnnotations) {
								if (methodParameterAnnotation instanceof NitroParameter) {
									NitroParameter paramAnnotationO = (NitroParameter) methodParameterAnnotation;
									generatedParameters.add(readFromListOfValues(paramAnnotationO.value()));
								}
							}
						}
						attachAllDecoratorsForThisMethod(method);
						return new MethodBean(nitroService, method, generatedParameters.toArray(new Object[generatedParameters.size()]));
					}
			}
			
		}
		throw new NitroMethodNotFoundException(String.format("Unable to Find method %s in class %s", methodId, this.getClass()));
	}

	
	/**
	 * @param method
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void attachAllDecoratorsForThisMethod(Method method) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.decorator=new NitroExecutionDecorator();
		Annotation[] allMethodAnnotations=method.getDeclaredAnnotations();
		for(Annotation annotation:allMethodAnnotations){
			if(annotation instanceof NitroDecorator){
				NitroDecorator oNitroDecorator=(NitroDecorator)annotation;
				if(NitroExecutionDecorator.class == oNitroDecorator.value())
					throw new IllegalArgumentException(String.format("%s can be only specified by the framework", NitroExecutionDecorator.class));
				Class<NitroDecoratorIntf> decoratorInterfaceClass = (Class<NitroDecoratorIntf>) Class.forName(oNitroDecorator.value().getName());
				Constructor<NitroDecoratorIntf> constructor = decoratorInterfaceClass.getConstructor(NitroDecoratorIntf.class);
				decorator=(NitroDecoratorIntf) constructor.newInstance(new Object[] { decorator });
			}
		}
	}

	private void initialize(Object[] inputObjects) {
		this.mapOfValues=(HashMap<String, Object>)inputObjects[1];
		
	}
	private Object readFromListOfValues(String key) throws NitroParameterNotFoundException {
		Object theValue=this.mapOfValues.get(key);
		if(theValue==null)
			throw new NitroParameterNotFoundException(String.format("Unable to find parameter : %s in method : %s : class %s", key, this.methodId, this.getClass()));
		return theValue;
	}
}
