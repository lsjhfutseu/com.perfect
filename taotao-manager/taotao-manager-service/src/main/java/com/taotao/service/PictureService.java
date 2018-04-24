package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author shijun_li
 * @date 2018年3月27日 下午6:57:26
 * 
 */
public interface PictureService {
	public Map uploadFile(MultipartFile uploadFile);
}
