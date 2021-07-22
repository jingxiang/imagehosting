package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class CatBoxMoreService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://catbox.moe/user/api.php";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("fileToUpload", imageFile)
				.addPart("reqtype", new StringBody("fileupload", ContentType.DEFAULT_TEXT))
				.build();
	}

	@Override
	String anaysisResult(String response) {
		try {
			System.out.println(response);
			return response.trim();
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
