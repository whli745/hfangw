package util.base.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import util.BaseParameter;
import util.Common;


public class UploadifyAction {
	/**
	 * file附件
	 */
	private File file;
	/**
	 * 文件名
	 */
	private String fileFileName;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件存放位置
	 */
	private String fileSaveLocation;
	
	/**
	 * 附件上传方法
	 * @author dusd
	 * @modiy by zhujj
	 * @date 2015-6-9
	 */
	public void doUploadUploadify() {
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Random r = new Random();

			// 上传文件名中文乱码 待解决。。。 为了取得后缀名
			// jpg;*.gif;*.png;*.jpeg;*.txt;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.pdf;*.swf
			String tltfFileName = fileFileName;
			fileFileName = fileFileName.toLowerCase();
			for (int i = 0; i < BaseParameter.ARR_PHOTO_TYPE.length; i++) {
				String strFileType = BaseParameter.ARR_PHOTO_TYPE[i];
				if (fileFileName.endsWith(strFileType)) {
					fileType = "." + strFileType;
					break;
				}
			}
			if (Common.isNullOrSpace(fileType)) {
				for (int i = 0; i < BaseParameter.ARR_OFFICE_TYPE.length; i++) {
					String strFileType = BaseParameter.ARR_OFFICE_TYPE[i];
					if (fileFileName.endsWith(strFileType)) {
						fileType = "." + strFileType;
						break;
					}
				}
			}
			if (Common.isNullOrSpace(fileType)) {
				for (int i = 0; i < BaseParameter.ARR_COMPRESS_TYPE.length; i++) {
					String strFileType = BaseParameter.ARR_COMPRESS_TYPE[i];
					if (fileFileName.endsWith(strFileType)) {
						fileType = "." + strFileType;
						break;
					}
				}
			}
			
			if (Common.isNullOrSpace(fileType)) {
				if (tltfFileName.endsWith(".TLTF")) {
					fileType = ".TLTF";
				}
			}

			String fileSaveName = sdf.format(new Date()) + r.nextInt(100)
					+ fileType;
			String firstDir = BaseParameter.XMFJCFLJ + "/";
			String dir = fileSaveLocation 
					+ "/" + sdf1.format(new Date()) + "/";
			
			FileUtils.copyFile(file, new File(firstDir + dir + fileSaveName));
			ServletActionContext.getResponse().getWriter().print(dir + fileSaveName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 光盘导入TLTF
	 * @author liulx
	 * @date 2015年7月15日 19:58:27
	 */
	public void guangPanUploadify() {
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Random r = new Random();

			String tltfFileName = fileFileName;
			
			if (tltfFileName.endsWith(".TLTF")) {
				fileType = ".TLTF";
			} else {
				return;
			}

			String fileSaveName = sdf.format(new Date()) + r.nextInt(100) + fileType;
			String firstDir = BaseParameter.XMFJCFLJ + "/";
			String dir = fileSaveLocation 
					+ "/" + sdf1.format(new Date()) + "/";
			FileUtils.copyFile(file, new File(firstDir + dir + fileSaveName));
			
			ServletActionContext.getResponse().getWriter()
					.print(dir + fileSaveName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSaveLocation() {
		return fileSaveLocation;
	}

	public void setFileSaveLocation(String fileSaveLocation) {
		this.fileSaveLocation = fileSaveLocation;
	}
	
	
}
