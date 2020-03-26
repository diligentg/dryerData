package com.sc.dryer.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;


public class UpUtils {
//工具类所有函数都是静态修饰的
	public static void upfile(MultipartFile file, HttpServletRequest request) {
		//获取到服务器的地址
		String realPath = request.getSession().getServletContext().getRealPath("\\WEB-INF\\img\\");
		System.out.println(realPath);
		String basePath="C:\\Users\\郭世聪\\IdeaProjects\\dryer\\src\\main\\webapp\\WEB-INF\\img\\";

		File file1 = new File(realPath);

		if (!file1.exists()) {
			file1.mkdirs();
		}
		File file2 = new File(basePath);
		if(!file2.exists()) {
             file2.mkdirs();
		}
		//获取文件的名字
		String orgName = file.getOriginalFilename();
		try {
			FileOutputStream fos = new FileOutputStream(realPath+orgName,true);
			FileOutputStream fos1 = new FileOutputStream(basePath+orgName,true);
			fos.write(file.getBytes());
			fos1.write(file.getBytes());
			fos1.flush();
			fos.flush();
			fos1.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
