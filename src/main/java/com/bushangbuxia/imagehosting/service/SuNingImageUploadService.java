package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class SuNingImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "http://review.suning.com/imageload/uploadImg.do";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("Filedata", imageFile)
				.addPart("omsOrderItemId", new StringBody("1", ContentType.DEFAULT_TEXT))
				.addPart("custNum", new StringBody("1", ContentType.DEFAULT_TEXT))
				.addPart("deviceType", new StringBody("1", ContentType.DEFAULT_TEXT)).build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			JSONObject jsonObject = JSON.parseObject(response);
			String src = jsonObject.getString("src");
			if (src == null || src.isEmpty()) {
				return null;
			}
			return "https:" + src + ".jpg";
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		return new HashMap<String, String>();
	}
}
