package util.base.service;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

import util.base.dao.IBaseDao;

/**
 * baseService实现类
 * 
 * @author dusd
 * @date 2014-4-3
 * @param <T>
 */
public class BaseServiceImpl<T> implements IBaseService<T> {

	/**
	 * baseDao接口
	 */
	protected IBaseDao<T> baseDao;
	protected Class<T> clazz;

	@PostConstruct
	public void init() throws Exception {
		if (clazz != null) {
			String clazzName = clazz.getSimpleName();
			String clazzDaoName = clazzName.substring(0,1).toLowerCase()+ clazzName.substring(1) + "Dao";
//			System.out.println(this);//ZjVerifyInfoServiceImpl
			Field daoNameField = this.getClass().getDeclaredField(clazzDaoName);
			daoNameField.setAccessible(true); // 抑制Java对修饰符的检查
//			System.out.println(daoNameField);//zjVerifyInfoDao
			Object object = daoNameField.get(this);
			Field baseDaoNameField = this.getClass().getSuperclass().getDeclaredField("baseDao");
			baseDaoNameField.set(this, object);
//			System.out.println("***"+baseDao);
		}
	}

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public BaseServiceImpl(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 新增
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	public void save(T t) throws Exception {
		baseDao.save(t);
	}

	/**
	 * 修改
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	public void update(T t) throws Exception {
		baseDao.update(t);
	}

	/**
	 * 新增/修改
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	public void saveOrUpdate(T t) throws Exception {
		baseDao.saveOrUpdate(t);
	}

	/**
	 * 通过对象删除
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param t
	 * @throws Exception
	 */
	public void delete(T t) throws Exception {
		baseDao.delete(t);
	}

	/**
	 * 通过id删除
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param oid
	 * @throws Exception
	 */
	public void delete(String oid) throws Exception {
		baseDao.delete(oid);
	}

	/**
	 * 通过主键得到对象 onGet
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T onGet(String id) throws Exception {
		return baseDao.onGet(id);
	}

	/**
	 * 通过主键得到对象 onGet
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T onGet(Integer id) throws Exception {
		return baseDao.onGet(id);
	}

	/**
	 * 通过主键得到对象 onLoad
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T onLoad(String id) throws Exception {
		return baseDao.onLoad(id);
	}

	/**
	 * 通过主键得到对象 onLoad
	 * 
	 * @author dusd
	 * @date 2014-4-3
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T onLoad(Integer id) throws Exception {
		return baseDao.onLoad(id);
	}

	/**
	 * @return the baseDao接口
	 */
	public IBaseDao<T> getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao接口
	 *            the baseDao接口 to set
	 */
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

}
