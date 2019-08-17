package com.bushangbuxia.imagehosting.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.bushangbuxia.imagehosting.domain.ImageHostingReqConfig;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public abstract class AbstractInnerImageUploadService implements InnerImageUploadService {

	abstract String getUploadUrl();

	abstract HttpEntity getHttpEntity(File imageFile);

	abstract String anaysisResult(String response);
	
	abstract Map<String, String> getHeaderMap();

	public String upload(File imageFile,ImageHostingReqConfig reqConfig) throws IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = getUploadUrl();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(reqConfig.getConnetionTimeOut())
				.setSocketTimeout(reqConfig.getSocketTimeout()).build();
		httpPost.setConfig(requestConfig);
		httpPost.setEntity(getHttpEntity(imageFile));
		Map<String, String> headerMap = getHeaderMap();
		if(headerMap != null && !headerMap.isEmpty()) {
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
			}
		}
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				String responseEntity = EntityUtils.toString(response.getEntity());
				result = anaysisResult(responseEntity);
				if(result == null || result.isEmpty()) {
					printError(url, responseEntity);
				}
			}
			
//			EntityUtils.consume(resEntity);
			return result;
		} finally {
			try {
				response.close();
			} catch (Exception e) {
			}
		}
	}

	private void printError(String url,String responseEntity) {
		System.out.println("上传图片失败，返回结果------>：\n==============="+responseEntity+"===============\n请求站点："+url);
	}
}
