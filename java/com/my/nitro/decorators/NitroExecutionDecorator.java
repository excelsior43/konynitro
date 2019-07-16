/**
 * 
 */
package com.my.nitro.decorators;

import java.util.Map;

import com.konylabs.middleware.dataobject.Result;
import com.my.nitro.MethodBean;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */
public class NitroExecutionDecorator implements NitroDecoratorIntf{

	/* (non-Javadoc)
	 * @see com.bab.rmb.nitro.decorators.NitroDecoratorIntf#execute(java.util.Map, com.bab.rmb.nitro.MethodBean)
	 */
	
	public Result execute(Map<String, Object> inputMap, MethodBean bean) throws Exception { 
		// TODO Auto-generated method stub
		return bean.execute();
	}

	/* (non-Javadoc)
	 * @see com.bab.rmb.nitro.decorators.NitroDecoratorIntf#execute(java.util.Map, com.bab.rmb.nitro.MethodBean)
	 */
	/*
	@Override
	public Result execute(Map<String, Object> inputMap, MethodBean bean) throws Exception {
		return bean.execute();
	}
*/
}
