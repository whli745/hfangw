package util.base.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import util.Common;

public class UploadAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private File file;
	private String file_path;
	
	public void upload(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
		Random r=new Random();
		String path=ServletActionContext.getServletContext().getRealPath("/");
		String imgName=sdf.format(new Date())+r.nextInt(100)+".gif";
		try {
			if(!Common.isNullOrSpace(file_path)){
				FileUtils.copyFile(file,new File(path + file_path + imgName));
			}else{
				FileUtils.copyFile(file,new File(path+"images\\firstmenu\\"+imgName));
			}
			ServletActionContext.getResponse().getWriter().print(imgName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
}
