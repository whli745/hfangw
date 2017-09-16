package pt.wzgl.nrgl.service;

import java.io.File;

import pt.wzgl.nrgl.pojo.FileAtta;


public interface IFileAttaService {
	
	/**
	 * 文件上传
	 * @author shimh
	 * @date 2013-9-24
	 * @param atta
	 * @param attaName
	 * @param attaPath
	 * @return
	 * @throws Exception
	 */
	public FileAtta uploadFile(File atta, String attaName, String attaPath) throws Exception;
	
	/**
	 * 获取单个 附件 对象
	 * @author zhangCM
	 * @date 2013-10-17
	 * @param attaId
	 * @return
	 * @throws Exception
	 */
	public FileAtta getFileAtta(String attaId) throws Exception;
	
	/**
	 * 删除 文件附件
	 * @author zhangCM
	 * @date 2013-10-21
	 * @param attaId
	 * @throws Exception
	 */
	public void deleteFileAtta(String attaId) throws Exception;
	
	/**
	 * 删除 文件附件
	 * @author zhangCM
	 * @date 2013-10-21
	 * @param fileAtta
	 * @throws Exception
	 */
	public void deleteFileAtta(FileAtta fileAtta) throws Exception;
}
