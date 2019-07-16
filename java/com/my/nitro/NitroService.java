package com.my.nitro;

import org.springframework.beans.BeansException;    
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

public class NitroService implements ServiceInterface, ApplicationContextAware { 
	
	public Result invoke(String methodId, Object[] inputObjects)  throws java.lang.Exception{
		NitroObjectFactory factory=new NitroObjectFactory();
		return factory.invoke(this, methodId, inputObjects); 
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
		
	}
	private ApplicationContext applicationContext=null;
	
	/* (non-Javadoc)
	 * @see com.konylabs.middleware.common.JavaService2#invoke(java.lang.String, java.lang.Object[], com.konylabs.middleware.controller.DataControllerRequest, com.konylabs.middleware.controller.DataControllerResponse)
	 */
	public Result invoke(String methodId, Object[] inputObjects, DataControllerRequest request, DataControllerResponse response)
			throws Exception {
		return new NitroObjectFactory().invoke(this, methodId, inputObjects);
	}
}
