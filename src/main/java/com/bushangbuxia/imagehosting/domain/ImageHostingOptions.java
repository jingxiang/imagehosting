package com.bushangbuxia.imagehosting.domain;

import java.util.Arrays;
import java.util.List;

import com.bushangbuxia.imagehosting.constants.ImageHostingPlatform;
import com.bushangbuxia.imagehosting.constants.ImageSuffix;

import lombok.Data;

/**
 * @author kalman03
 * @since 2019-08-17
 */
@Data
public class ImageHostingOptions {
	/**
	 * 指定上传图片的后缀，请与上传图片类型对应。该参数仅对上传参数传byte[]时有效。若不指定，默认为png，部分图床站点遇到图片类型与后缀不一致，会报错。
	 */
	private ImageSuffix suffix;
	/**
	 * 指定要上传图片所在图床的站点，遇到一个站点上传成功则立即返回，否则继续下一个站点。建议list长度不要超过2个站点。 
	 * @see ImageHostingPlatform
	 */
	private List<Integer> hostingPlatforms = Arrays.asList(ImageHostingPlatform.SUNING, ImageHostingPlatform.ALIEXPRESS);
	/**
	 * 是否需要校验上传文件是否为图片，若前置业务已校验，可忽略
	 */
	private boolean checkImage = false;
}
