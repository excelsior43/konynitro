package com.my.nitro;


import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

public class NitroService implements ServiceInterface { 
	
	public Result invoke(String methodId, Object[] inputObjects)  throws java.lang.Exception{
		NitroObjectFactory factory=new NitroObjectFactory();
		return factory.invoke(this, methodId, inputObjects); 
	}

	
	
	/* (non-Javadoc)
	 * @see com.konylabs.middleware.common.JavaService2#invoke(java.lang.String, java.lang.Object[], com.konylabs.middleware.controller.DataControllerRequest, com.konylabs.middleware.controller.DataControllerResponse)
	 */
	public Result invoke(String methodId, Object[] inputObjects, DataControllerRequest request, DataControllerResponse response)
			throws Exception {
		return new NitroObjectFactory().invoke(this, methodId, inputObjects);
	}
}
