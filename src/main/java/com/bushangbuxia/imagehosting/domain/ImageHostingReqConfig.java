package com.bushangbuxia.imagehosting.domain;

import lombok.Data;

/**
 *	@author kalman03
 *	@since	2019-08-17
 */
@Data
public class ImageHostingReqConfig {

	private int connetionTimeOut = 1000*3;
	
	private int socketTimeout = 1000*3;
}
