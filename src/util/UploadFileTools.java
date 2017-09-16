package util;

import java.io.File;

import org.apache.struts2.ServletActionContext;

public class UploadFileTools {

	/**
	 * 
	 * 功能：创建文件夹
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	public static String uploadFile(String path, String upLoadFileName,
			File uploadFile) {
		String dir = ServletActionContext.getServletContext().getRealPath(
				"/" + path);
		// 上传网上证书附件
		UploadFileTools.createDir(dir);
		String exdName = upLoadFileName.substring(upLoadFileName
				.lastIndexOf("."));
		String fileName = String.valueOf(System.currentTimeMillis())
				+ String.valueOf(Math.round(Math.random() * 1000D));
		File newFile = new File(dir, fileName + exdName);
		uploadFile.renameTo(newFile);
		return path + fileName + exdName;
	}
}
