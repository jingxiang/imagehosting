package com.bushangbuxia.imagehosting;

import java.io.File;
import java.io.IOException;

import com.bushangbuxia.imagehosting.domain.ImageHostingOptions;
import com.bushangbuxia.imagehosting.domain.ImageHostingResponse;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public interface ImageHostingService {

	ImageHostingResponse upload(byte[] imageBytes, ImageHostingOptions options)throws IOException;

	ImageHostingResponse upload(File imageFile, ImageHostingOptions options)throws IOException;
}
