package pt.xtgl.jcgl.service;

import pt.xtgl.jcgl.pojo.SysLocal;
import util.base.service.IBaseService;

/**
 * 地区 service接口
 * 
 * @author : dusd
 * @date : 2015-11-13
 */
public interface ISysLocalService extends IBaseService<SysLocal> {
	/**
	 * 获取地区且只到市
	 * @author yaozy
	 * @date 2015-11-16
	 * @return json
	 * @throws Exception
	 */
	public String getSysLocalListToJsonToCity(String topId) throws Exception;
	
	/**
	 * 获取地区且只到省
	 * @author yaozy
	 * @date 2015-11-16
	 * @return json
	 * @throws Exception
	 */
	public String getSysLocalListToProvince() throws Exception;
}
