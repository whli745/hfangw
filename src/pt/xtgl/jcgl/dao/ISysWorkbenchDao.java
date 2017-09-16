package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysWorkbench;
import util.ResultPage;

public interface ISysWorkbenchDao {

	/**
	 * 增加工作台
	 * 
	 * @author wanghw
	 * @date 2013-04-08
	 * @param sysWorkbench
	 *            工作台对象
	 * @throws Exception
	 */
	public void saveSysWorkbench(SysWorkbench sysWorkbench) throws Exception;

	/**
	 * 删除工作台
	 * 
	 * @author wanghw
	 * @date 2013-04-08
	 * @param sysWorkbench
	 *            工作台对象
	 * @throws Exception
	 */
	public void deleteSysWorkbench(SysWorkbench sysWorkbench) throws Exception;

	/**
	 * 修改工作台
	 * 
	 * @author wanghw
	 * @date 2013-04-08
	 * @param sysWorkbench
	 *            工作台对象
	 * @throws Exception
	 */
	public void updateSysWorkbench(SysWorkbench sysWorkbench) throws Exception;

	/**
	 * 查找工作台
	 * 
	 * @author wanghw
	 * @date 2013-04-08
	 * @param workbenchId
	 *            工作台对象Id
	 * @return
	 * @throws Exception
	 */
	public SysWorkbench querySysWorkbench(String workbenchId) throws Exception;

	/**
	 * 增加或者修改工作台信息
	 * 
	 * @author wanghw
	 * @date 2013-04-08
	 * @param sysWorkbench
	 *            工作台对象
	 * @throws Exception
	 */
	public void editeSysWorkbench(SysWorkbench sysWorkbench) throws Exception;

	/**
	 * 查询工作台列表
	 * 
	 * @param workbenchSystem
	 *            工作台所属对象
	 * @param workbenchName
	 *            工作台名称
	 * @param delFlag
	 *            是否删除标识
	 * @param page
	 *            列表页数
	 * @param pageRows
	 *            列表行数
	 * @return
	 * @throws Exception
	 */
	public ResultPage querySysWorkbenchList(SysWorkbench sysWorkbench,
			String delFlag, int page, int pageRows) throws Exception;
	
	/**
	 * 
	  * 
	  * 功能：根据当前的角色查询工作台列表
	  * @author:xuhw
	  * @Email: xuhaiwei@zhuofansoft.com
	  * @date:  2013-6-27
	  * @return
	  * @modify：
	    @param workbenchSystem
	    @return
	    @throws Exception
	 */
	public List queryWorkbenchList(String workbenchSystem,String modulePid) throws Exception;
	
	
	
}
