package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.alibaba.fastjson.JSON;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class CcImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://upload.cc/image_upload";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("uploaded_file[]", imageFile).build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			return "https://upload.cc/"
					+ JSON.parseObject(response).getJSONArray("success_image").getJSONObject(0).getString("url");
		} catch (Exception e) {
			// ignore
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		return new HashMap<String, String>();
	}
}
