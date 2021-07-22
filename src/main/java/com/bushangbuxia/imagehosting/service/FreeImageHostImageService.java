package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

import com.alibaba.fastjson.JSON;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class FreeImageHostImageService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://freeimage.host/json";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("source", imageFile)
				.addPart("type", new StringBody("file", ContentType.DEFAULT_TEXT))
				.addPart("action", new StringBody("upload", ContentType.DEFAULT_TEXT))
				.build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			System.out.println(response);
			return JSON.parseObject(response).getJSONObject("image").getJSONObject("image").getString("url");
		} catch (Exception e) {
			// ignore
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
}
