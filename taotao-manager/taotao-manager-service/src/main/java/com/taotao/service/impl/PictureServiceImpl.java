package com.taotao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/**
 * @author shijun_li
 * @date 2018年3月27日 下午6:55:49
 * 
 */
@Service
public class PictureServiceImpl implements PictureService{
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USER}")
	private String FTP_USER;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	public Map uploadFile(MultipartFile uploadFile) {
		Map result = new HashMap<>();
		try {
			//需要生成新文件名
			String oldName = uploadFile.getOriginalFilename();
			//生成新的文件名
			String newName = IDUtils.genImageName();
			newName += oldName.substring(oldName.indexOf("."));
			//上传
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			boolean flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER, FTP_PASSWORD, 
					FTP_BASE_PATH, filePath, newName, uploadFile.getInputStream());
		    if(!flag) {
		    	result.put("error", 1);
		    	result.put("message", "上传失败");
		    	return result;
		    }
		    else {
		    	result.put("error", 0);
		    	result.put("url", IMAGE_BASE_URL+filePath+"/"+newName);
		    	return result;
		    }
		    	
		} catch (Exception e) {
			// TODO: handle exception
			result.put("error", 1);
	    	result.put("message", "上传出现异常");
			return result;
		}
	}
}
