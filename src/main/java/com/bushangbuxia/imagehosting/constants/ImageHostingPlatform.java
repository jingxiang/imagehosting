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
	/**
	 * 今日头条
	 */
	public final static int BYTEDANCE = 4;
	/**
	 * 搜狐
	 */
	public final static int SOUHU = 5;
	/**
	 * 网易
	 */
	public final static int NETEASE = 6;
	/**
	 * CC
	 */
	public final static int CC = 7;
	/**
	 * 掘金
	 */
	public final static int JUEJIN = 8;

	public final static List<Integer> ALL_HOSTING_PLATFORMS = Arrays.asList(JUEJIN, CC, NETEASE, SUNING, ALIEXPRESS, JD,
			BYTEDANCE, SOUHU);
}
