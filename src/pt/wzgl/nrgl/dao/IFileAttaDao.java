package pt.wzgl.nrgl.dao;

import pt.wzgl.nrgl.pojo.FileAtta;


public interface IFileAttaDao {
	
	/**
	 * 保存文件上传
	 * @author shimh
	 * @date 2013-9-24
	 * @param fileAtta
	 * @throws Exception
	 */
	public void saveFileAtta(FileAtta fileAtta) throws Exception;
	
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
