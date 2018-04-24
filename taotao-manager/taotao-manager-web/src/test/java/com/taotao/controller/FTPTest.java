package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {
	@Test
	public void testFtpClient() throws Exception{
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.111.128");
		ftpClient.login("ftpuser", "ftpuser");
		FileInputStream inputStream = new FileInputStream(new File("D:zhengjian.jpg"));
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		ftpClient.storeFile("123.jpg", inputStream);
		inputStream.close();
		
		ftpClient.logout();

	}
	
	//运用工具类上传
	@Test
	public void testFtpUtils() throws Exception{
		FileInputStream inputStream = new FileInputStream(new File("D:zhengjian.jpg"));
		FtpUtil.uploadFile("192.168.111.128", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", 
				"/2018", "111.jpg", inputStream);
	}
}
