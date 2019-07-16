/**
 * 
 */
package com.my.nitro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.konylabs.middleware.dataobject.Result;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */
public class MethodBean {
	Object[] executionParameters;
	Method method;
	private NitroService nitroService;



/**
 * @param nitroService
 * @param method2
 * @param objects
 */
public MethodBean(NitroService nitroService, Method method, Object[] objects) {
	super();
	this.nitroService=nitroService;
	this.method = method;
	this.executionParameters = objects;
}

public Result execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	return (Result) this.method.invoke(nitroService, executionParameters);
}


/**
 * @return the method
 */
public Method getMethod() {
	return method;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "MethodBean [executionParameters=" + Arrays.toString(executionParameters) + ", method=" + method
			+ ", nitroService=" + nitroService + "]" + executionParameters[0];
}

}
