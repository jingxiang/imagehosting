package com.bushangbuxia.imagehosting.service;
/**
 *	@author kalman03
 *	@since	2019-08-17
 */

import java.io.File;
import java.io.IOException;

import com.bushangbuxia.imagehosting.domain.ImageHostingReqConfig;

public interface InnerImageUploadService {

	String upload(File imageFile,ImageHostingReqConfig reqConfig)throws IOException;
}
