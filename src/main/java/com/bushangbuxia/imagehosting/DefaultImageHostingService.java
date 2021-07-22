package com.bushangbuxia.imagehosting;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bushangbuxia.imagehosting.constants.ImageHostingPlatform;
import com.bushangbuxia.imagehosting.domain.ImageHostingOptions;
import com.bushangbuxia.imagehosting.domain.ImageHostingResponse;
import com.bushangbuxia.imagehosting.domain.ImageHostingResponse.ImageDO;
import com.bushangbuxia.imagehosting.service.AliexpressImageUploadService;
import com.bushangbuxia.imagehosting.service.BilibiliUploadService;
import com.bushangbuxia.imagehosting.service.BytedanceImageUploadService;
import com.bushangbuxia.imagehosting.service.CatBoxMoreService;
import com.bushangbuxia.imagehosting.service.CcImageUploadService;
import com.bushangbuxia.imagehosting.service.FreeImageHostImageService;
import com.bushangbuxia.imagehosting.service.InnerImageUploadService;
import com.bushangbuxia.imagehosting.service.JdImageUploadService;
import com.bushangbuxia.imagehosting.service.JueJinImageUploadService;
import com.bushangbuxia.imagehosting.service.NeteaseImageUploadService;
import com.bushangbuxia.imagehosting.service.SouhuImageUploadService;
import com.bushangbuxia.imagehosting.service.SuNingImageUploadService;
import com.bushangbuxia.imagehosting.service.VimCNImageService;
import com.bushangbuxia.imagehosting.utils.RandomUtils;

/**
 * @author kalman03
 * @since 2019-08-17
 */
public class DefaultImageHostingService implements ImageHostingService {

	public ImageHostingResponse upload(byte[] imageBytes, ImageHostingOptions options) throws IOException {
		String prefix = RandomUtils.randomString(7);
		File imageFile = File.createTempFile(prefix, options.getSuffix().getValue());
		byte2File(imageBytes, imageFile);
		return upload(imageFile, options);
	}

	public ImageHostingResponse upload(File imageFile, ImageHostingOptions options) throws IOException {
		if (imageFile == null) {
			return error("请指定上传图片的内容");
		}
		if (options.getHostingPlatforms() == null || options.getHostingPlatforms().isEmpty()) {
			return error("hostingPlatforms参数不能为空，至少指定一个图床站点");
		}
		for (int hostingPlatform : options.getHostingPlatforms()) {
			if (!ImageHostingPlatform.ALL_HOSTING_PLATFORMS.contains(hostingPlatform)) {
				return error("hostingPlatforms参数中存在不支持的图传站点");
			}
		}
		if (options.isCheckImage() && isImage(imageFile)) {
			return error("上传的图片损坏或非图片文件");
		}
		for (int hostingPlatform : options.getHostingPlatforms()) {
			try {
				InnerImageUploadService innerImageUploadService = adapterUploadService(hostingPlatform);
				String absoluteUrl = innerImageUploadService.upload(imageFile, options.getReqConfig());
				if (absoluteUrl != null && !absoluteUrl.isEmpty()) {
					return success(absoluteUrl, hostingPlatform);
				}
			} catch (Exception e) {
			}
		}
		return error("上传图片失败 ");
	}

	private InnerImageUploadService adapterUploadService(int hostingPlatform) {
		if (hostingPlatform == ImageHostingPlatform.SUNING) {
			return new SuNingImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.ALIEXPRESS) {
			return new AliexpressImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.JD) {
			return new JdImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.BYTEDANCE) {
			return new BytedanceImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.SOUHU) {
			return new SouhuImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.NETEASE) {
			return new NeteaseImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.CC) {
			return new CcImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.JUEJIN) {
			return new JueJinImageUploadService();
//			return new PrntscrImageUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.BILIBILI) {
			return new BilibiliUploadService();
		} else if (hostingPlatform == ImageHostingPlatform.CATBOX) {
			return new CatBoxMoreService();
		} else if (hostingPlatform == ImageHostingPlatform.VIM_CN) {
			return new VimCNImageService();
		} else if (hostingPlatform == ImageHostingPlatform.FREE_IMAGE_HOST) {
			return new FreeImageHostImageService();
		}
		return null;
	}

	private ImageHostingResponse error(String message) {
		ImageHostingResponse response = new ImageHostingResponse();
		response.setMessage(message);
		response.setSuccess(false);
		return response;
	}

	private ImageHostingResponse success(String absoluteUrl, int platform) {
		ImageHostingResponse response = new ImageHostingResponse();
		response.setMessage("上传成功");
		response.setSuccess(true);
		ImageDO imageDO = new ImageDO();
		imageDO.setAbsoluteUrl(absoluteUrl);
		imageDO.setPlatform(platform);
		response.setImageDO(imageDO);
		return response;
	}

	private boolean isImage(File imageFile) {
		try {
			Image image = ImageIO.read(imageFile);
			return image != null && image.getWidth(null) > 0 && image.getHeight(null) > 0;
		} catch (Exception e) {
			// ignore
		}
		return false;
	}

	private void byte2File(byte[] buf, File file) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		} catch (Exception e) {
			// ignore
		} finally {
			try {
				if (bos != null) {
					bos.close();
					fos.close();
				}
			} catch (IOException e) {
			}
		}
	}
}
