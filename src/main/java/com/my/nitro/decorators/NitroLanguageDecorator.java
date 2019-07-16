/**
 * 
 */
package com.my.nitro.decorators;

import java.util.Map;

import org.apache.log4j.Logger;

import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;
import com.my.nitro.MethodBean;
import com.my.nitro.exceptions.NitroExceptionBaseClass;
import com.my.nitro.exceptions.NitroSessionExpiredException;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

public class NitroLanguageDecorator implements NitroDecoratorIntf{
	private final static Logger logger = Logger.getLogger(NitroLanguageDecorator.class);
	
	private NitroDecoratorIntf decorator;

	/* (non-Javadoc)
	 * @see com.bab.rmb.nitro.decorators.NitroDecoratorIntf#execute(java.util.Map, com.bab.rmb.nitro.MethodBean)
	 */
	
	public Result execute(Map<String, Object> inputMap, MethodBean bean) throws Exception { 
		logger.debug("inside SessionActiveDecorator" );
		if(inputMap.get(LANGUAGE)==null)
			throw new NitroExceptionBaseClass("the language is not set");
		Result result = this.decorator.execute(inputMap, bean);
		result.setParam(new Param(LANGUAGE, inputMap.get(LANGUAGE).toString(), STRING_TYPE)); 
		return result;
	}

	public NitroLanguageDecorator(NitroDecoratorIntf decorator){
		logger.debug("setting SessionActiveDecorator");
		this.decorator=decorator;
	}


}
