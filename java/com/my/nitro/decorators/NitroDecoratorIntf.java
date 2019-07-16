/**
 * 
 */
package com.my.nitro.decorators;

import java.util.Map;

import com.konylabs.middleware.dataobject.Result;
import com.my.nitro.MethodBean;

/**
 * @author Yasir,sonu.yasir@gmail.com
 *
 */
public interface NitroDecoratorIntf { 
	Result execute(Map<String,Object> inputMap, MethodBean bean) throws Exception;  
	static final String LANGUAGE="Lang";
	static final String STRING_TYPE = "String";
	static final String YES = "yes";
	static final String IS_SESSION_ALIVE = "isSessionAlive";
	static final String OP_STATUS = "opstatus";
	static final String ZERO = "0";
	static final String EAI_RETURN_CODE_MSG = "EAIRtrnCd";
	static final String EAI_RETURN_CODE = "000000";
	
}
