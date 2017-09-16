package util.base.service;

/**
 * baseService接口
 * @author dusd
 * @date 2014-4-3
 * @param <T>
 */
public interface IBaseService<T> {
	
	/**
	 * 新增
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	void save(T t) throws Exception;
	
	/**
	 * 修改
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	void update(T t) throws Exception;
	
	/**
	 * 新增/修改
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	void saveOrUpdate(T t) throws Exception;
	
	/**
	 * 通过对象删除
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	void delete(T t) throws Exception;
	
	/**
	 * 通过id删除
	 * @author dusd
	 * @date 2014-4-3
	 * @param oid
	 * @throws Exception
	 */
	void delete(String oid) throws Exception;
	
	/**
	 * 通过主键得到对象 onGet
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T onGet(String id) throws Exception;
	
	/**
	 * 通过主键得到对象 onGet
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T onGet(Integer id) throws Exception;
	
	/**
	 * 通过主键得到对象 onLoad
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T onLoad(String id) throws Exception;
	
	/**
	 * 通过主键得到对象 onLoad
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T onLoad(Integer id) throws Exception;

}
