package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class VimCNImageService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://img.vim-cn.com";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("file", imageFile)
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
