package com.my.nitro;

import com.konylabs.middleware.common.JavaService;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

public interface ServiceInterface extends   JavaService,   JavaService2 {

	public Result invoke(String methodId, Object[] object)  throws java.lang.Exception; 
	public Result invoke(String methodId, Object[] object, DataControllerRequest request, DataControllerResponse response)  throws java.lang.Exception;;
}
