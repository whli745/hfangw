package pt.xtgl.jcgl.service;

import java.util.List;

import pt.xtgl.jcgl.pojo.LogActionDesc;
import util.ResultPage;
import util.base.service.IBaseService;

/**
 * 系统功能描述service
 * @author dusd
 * @date 2015-12-9
 */
public interface ILogActionDescService extends IBaseService<LogActionDesc> {

	/**
	 * 保存系统功能描述
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @param logActionDesc系统功能描述对象
	 * @throws Exception
	 */
	public void saveLogActionDesc(LogActionDesc logActionDesc) throws Exception;

	/**
	 * 删除系统功能描述
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @param logActionDesc系统功能描述对象
	 * @throws Exception
	 */
	public void deleteLogActionDesc(LogActionDesc logActionDesc)
			throws Exception;

	/**
	 * 通过Oid查询系统功能描述
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @param oid系统功能描述oid
	 * @return
	 * @throws Exception
	 */
	public LogActionDesc queryLogActionDesc(String oid) throws Exception;

	/**
	 * 查询系统功能描述列表
	 * 
	 * @author wanghw
	 * @date 2013-5-9
	 * @param sqlObj查询条件对象
	 * @param page列表页数
	 * @param pageRows列表行数
	 * @return
	 * @throws Exception
	 */
	public ResultPage queryLogActionDescList(LogActionDesc sqlObj, int page,
			int pageRows) throws Exception;

	/**
	 * 查询所有的系统功能描述
	 * @author dusd
	 * @date 2015-12-9
	 * @return
	 * @throws Exception
	 */
	List<LogActionDesc> queryLogActionDescList() throws Exception;
}
