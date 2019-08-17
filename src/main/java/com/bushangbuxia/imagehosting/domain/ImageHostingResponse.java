package com.bushangbuxia.imagehosting.domain;

import lombok.Data;

@Data
public class ImageHostingResponse {
	
	private ImageDO imageDO;
	private boolean success;
	private String message;
	
	@Data
	public static class ImageDO{
		private String absoluteUrl;
		private int platform;
	}
}