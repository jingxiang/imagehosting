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
import com.bushangbuxia.imagehosting.domain.ImageHostingReqConfig;
import com.bushangbuxia.imagehosting.utils.RandomUtils;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class AliexpressImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://kfupload.alibaba.com/mupload";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		String suffix = imageFile.getName().substring(imageFile.getName().lastIndexOf("."));
		return MultipartEntityBuilder.create().addBinaryBody("file", imageFile)
				.addPart("scene", new StringBody("aeMessageCenterV2ImageRule", ContentType.DEFAULT_TEXT))
				.addPart("name", new StringBody(RandomUtils.randomString(7) + suffix, ContentType.DEFAULT_TEXT))
				.build();
	}

	@Override
	String anaysisResult(String response) {
		JSONObject jsonObject = JSON.parseObject(response);
		if (jsonObject.getIntValue("code") == 0) {
			return "https://ae01.alicdn.com/kf/"+jsonObject.getString("fs_url");
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("User-Agent", "iAliexpress/6.22.1 (iPhone; iOS 12.1.2; Scale/2.00)");
		return map;
	}
}
