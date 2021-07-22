package com.bushangbuxia;

import java.io.File;
import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.bushangbuxia.imagehosting.DefaultImageHostingService;
import com.bushangbuxia.imagehosting.ImageHostingService;
import com.bushangbuxia.imagehosting.constants.ImageHostingPlatform;
import com.bushangbuxia.imagehosting.domain.ImageHostingOptions;
import com.bushangbuxia.imagehosting.domain.ImageHostingResponse;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		try {
			ImageHostingService imageHostingService = new DefaultImageHostingService();
			File imageFile = new File("C:\\Users\\kalman\\Desktop\\微信截图_20210719224153.png");
			ImageHostingOptions options = new ImageHostingOptions();
			options.setHostingPlatforms(Arrays.asList(ImageHostingPlatform.FREE_IMAGE_HOST));
			ImageHostingResponse response = imageHostingService.upload(imageFile, options);
			System.out.println(JSON.toJSONString(response));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
