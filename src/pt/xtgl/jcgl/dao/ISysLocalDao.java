package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysLocal;
import util.base.dao.IBaseDao;

/**
 * 地区 dao接口
 * 
 * @author : dusd
 * @date : 2015-11-13
 */
public interface ISysLocalDao extends IBaseDao<SysLocal> {
	/**
	 * 获取地区且只到市
	 * @author yaozy
	 * @date 2015-11-16
	 * @return list
	 * @throws Exception
	 */
	public List<SysLocal> getSysLocalListToJsonToCity(String topId) throws Exception;
	
	/**
	 * 获取地区且只到省
	 * @author yaozy
	 * @date 2015-11-16
	 * @return json
	 * @throws Exception
	 */
	public List<SysLocal> getSysLocalListToProvince() throws Exception;
}
