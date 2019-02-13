package com.sky.knowledge.module.common.server.util;

import java.io.File;

import com.esafenet.dll.FileDlpUtil;
/**
 * dlp解密工具类
 * @description
 * @create xq
 * @date 2014-10-18
 */
public class DlpUtil {

	/**
	 * dlp解密
	 * @description
	 * @create xq
	 * @date 2014-10-18
	 */
	public static File deCodeDlpFile(File file) throws Exception{
		if (file == null || !file.isFile()) {
			return null;
		}
		String filePath = file.getAbsolutePath();//文件全路径
		String dlpFilePath = FileDlpUtil.getDecryptFile(filePath);	//DLP解密
		file.delete();	//删除原始文件
		File dlpFile = new File(dlpFilePath);
		File newFile = new File(filePath);
		if (!dlpFile.renameTo(newFile)) {	//修改DLP文件名为原始文件名
			return null;
		}
		return newFile;
	}
}
