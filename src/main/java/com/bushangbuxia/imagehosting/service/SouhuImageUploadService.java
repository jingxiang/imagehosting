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
public class SouhuImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "http://changyan.sohu.com/api/2/comment/attachment";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("file", imageFile).build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			response = response.replaceAll("\\\\\"", "\"");
			response = response.substring(1,response.length()-1);
			return JSON.parseObject(response).getString("url");
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
