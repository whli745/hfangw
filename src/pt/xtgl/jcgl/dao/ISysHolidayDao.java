package pt.xtgl.jcgl.dao;

import pt.xtgl.jcgl.pojo.SysHoliday;
import util.ResultPage;

public interface ISysHolidayDao {
	/**
	 * 保存节假日
	 * @param holiday
	 * @throws Exception
	 */
	void saveSysHoliday(SysHoliday holiday) throws Exception;

	/**
	 * 删除节假日
	 * @param oid
	 * @throws Exception
	 */
	void deleteSysHoliday(String oid) throws Exception;

	/**
	 * 查询节假日
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	ResultPage querySysHoliday(SysHoliday holiday,int page, int pageRows) throws Exception;
	
	public SysHoliday querySysHoliday(String oid) throws Exception;
}
