package com.bushangbuxia.imagehosting.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 图床站点
 * 
 * @author kalman03
 * @since 2019-08-17
 */
public class ImageHostingPlatform {
	/**
	 * 苏宁
	 */
	public final static int SUNING = 1;
	/**
	 * aliexpress ，在微信里面可能无法直接打开
	 */
	public final static int ALIEXPRESS = 2;
	/**
	 * 京东，不支持尺寸太小的图片
	 */
	public final static int JD = 3;

	public final static List<Integer> ALL_HOSTING_PLATFORMS = Arrays.asList(SUNING, ALIEXPRESS, JD);
}
