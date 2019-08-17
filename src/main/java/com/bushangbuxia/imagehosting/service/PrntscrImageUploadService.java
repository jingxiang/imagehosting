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
public class PrntscrImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://prntscr.com/upload.php";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("image", imageFile).build();
	}

	@Override
	String anaysisResult(String response) {
		System.out.println(response);
		try {
			return JSON.parseObject(response).getString("data");
		} catch (Exception e) {
			// ignore
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:65.0) Gecko/20100101 Firefox/65.0");
		return map;
	}
}
