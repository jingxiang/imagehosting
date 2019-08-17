package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class JdImageUploadService extends AbstractInnerImageUploadService {

	@Override
	String getUploadUrl() {
		return "https://search.jd.com/image?op=upload";
	}

	@Override
	HttpEntity getHttpEntity(File imageFile) {
		return MultipartEntityBuilder.create().addBinaryBody("file", imageFile).build();
	}

	@Override
	String anaysisResult(String response) {
		if (response.contains("callback")) {
			Pattern p = Pattern.compile("(?m)\\(\"(.*)\"\\)");
			Matcher m = p.matcher(response);
			String temp = "";
			while (m.find()) {
				temp = m.group();
			}
			temp = temp.substring(2, temp.length() - 2);
			return "https://img1.360buyimg.com/img/" + temp;
		}
		return null;
	}

	@Override
	Map<String, String> getHeaderMap() {
		return new HashMap<String, String>();
	}
}
