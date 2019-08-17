package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class BytedanceImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://mp.toutiao.com/upload_photo/?type=json";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("photo", imageFile).build();
	}

	@Override
	String anaysisResult(String response) {
		JSONObject jsonObject = JSON.parseObject(response);
		return jsonObject.getString("web_url");
	}

	@Override
	Map<String, String> getHeaderMap() {
		return new HashMap<String, String>();
	}
}
