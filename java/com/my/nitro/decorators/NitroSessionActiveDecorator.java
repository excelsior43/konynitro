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

public class NitroSessionActiveDecorator implements NitroDecoratorIntf{
	private final static Logger logger = Logger.getLogger(NitroSessionActiveDecorator.class);
	private NitroDecoratorIntf decorator;

	/* (non-Javadoc)
	 * @see com.bab.rmb.nitro.decorators.NitroDecoratorIntf#execute(java.util.Map, com.bab.rmb.nitro.MethodBean)
	 */
	
	public Result execute(Map<String, Object> inputMap, MethodBean bean) throws Exception {
		logger.debug("inside SessionActiveDecorator" );
		if(inputMap.get(IS_SESSION_ALIVE)==null || !((String)inputMap.get(IS_SESSION_ALIVE)).equalsIgnoreCase(YES))
			throw new NitroSessionExpiredException("Session is Expired");
		Result result = this.decorator.execute(inputMap, bean);
		result.setParam(new Param(IS_SESSION_ALIVE, YES, STRING_TYPE)); 
		return result;
	}

	public NitroSessionActiveDecorator(NitroDecoratorIntf decorator){
		logger.debug("setting SessionActiveDecorator");
		this.decorator=decorator;
	}

	


}


