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
public class BilibiliUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://api.vc.bilibili.com/api/v1/drawImage/upload";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("file_up", imageFile)
				.addPart("category", new StringBody("daily", ContentType.DEFAULT_TEXT))
				.addPart("biz", new StringBody("draw", ContentType.DEFAULT_TEXT)).build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			System.out.println(response);
			//{"code":0,"message":"success","data":{"image_url":"http:\/\/i0.hdslb.com\/bfs\/album\/8eac2cf7ced7f3b5db455e22dc9c930f5c09967b.png","image_width":2442,"image_height":873}}
			return JSON.parseObject(response).getJSONObject("data").getString("image_url");
		} catch (Exception e) {
			// ignore
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		Map<String, String> map = new HashMap<>();
		map.put("Cookie", "SESSDATA=b1a03641%2C1642516858%2C8fcba%2A71");
		return map;
	}
}
