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
public class NeteaseImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "http://you.163.com/xhr/file/upload.json";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("file", imageFile).build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			return JSON.parseObject(response).getString("data");
		} catch (Exception e) {
			//ignore
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		return new HashMap<String, String>();
	}
}
