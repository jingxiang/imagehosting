package com.bushangbuxia.imagehosting.constants;
/**
 *	@author kalman03
 *	@since	2019-08-17
 */
public enum ImageSuffix {

	PNG(".png"),JPG(".jpg");
	
	private ImageSuffix(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}
}
