package util.base.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.BaseParameter;
import util.ResultPage;

import com.zhuofan.module.DAOException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	protected Class clazz;

	public BaseDaoImpl() {
		// 获得泛型化超类(BaseDaoImpl<User> )
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 获得class<User>
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	/**
	 * 打开Session
	 * 
	 * @return
	 */
	public Session openSession() {
		return getSessionFactory().openSession();
	}

	/**
	 * 打开Session
	 * 
	 * @return
	 */
	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	public void merge(T t) {
		getSessionFactory().getCurrentSession().merge(t);
	}

	/**
	 * 关闭Session
	 * 
	 * @param session
	 */
	public void closeSession(Session session) {
		if (null != session && session.isOpen()) {
			session.close();
		}
	}

	/**
	 * 查询所有记录
	 * 
	 * @param hql
	 *            HQL语句
	 * @return
	 */
	public List<T> queryByHql(String hql) throws Exception {
		return this.getCurrentSession().createQuery(hql).list();
	}

	/**
	 * 查询所有记录
	 * 
	 * @param hql
	 *            HQL语句
	 * @return
	 */
	public List queryListByHql(String hql) throws Exception {
		return this.getCurrentSession().createQuery(hql).list();
	}

	/**
	 * 查询唯一对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @return
	 */
	public T getObjByHql(String hql) throws Exception {
		T t = null;
		Query query = this.getCurrentSession().createQuery(hql);
		if (null != query) {
			t = (T) query.uniqueResult();
		}
		return t;
	}

	/**
	 * 查询所有记录（参数）
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public List<T> queryByHql(String hql, List params) throws Exception {
		List<T> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		list = query.list();
		return list;
	}

	/**
	 * 查询所有记录（参数）
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public List<Object> queryListByHql(String hql, List params)
			throws Exception {
		List<Object> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		list = query.list();
		return list;
	}

	/**
	 * 查询所有记录（参数）
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public List<T> queryByHql(String hql, Object[] params) throws Exception {
		List<T> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		list = query.list();
		return list;
	}

	/**
	 * 根据指定的HQL语句以及参数数组取得所有结果列表的第一条记录
	 * 
	 * @param HQL语句
	 * @param objs 参数数组，为NULL表示没有参数
	 * @return Object
	 * @throws DAOException
	 */
	public T getFirstObject(String hql, Object[] params) throws Exception {
		T ret = null;
		List<T> tmp = queryByHql(hql, params);
		if (tmp != null && tmp.size() > 0)
			ret = tmp.get(0);
		return ret;
	}

	/**
	 * 根据指定的HQL预计以及参数数字取得所有结果列表的第一条记录
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数组，为null表示没有参数
	 * @return
	 * @throws Exception
	 */
	public Object getFirstObj(String hql, Object[] params) throws Exception {
		List<Object> objList = queryListByHql(hql, params);
		if (objList != null && objList.size() > 0)
			return objList.get(0);
		return null;
	}

	/**
	 * 查询所有记录（参数）
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public List<Object> queryListByHql(String hql, Object[] params)
			throws Exception {
		List<Object> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		list = query.list();
		return list;
	}

	/**
	 * 查询所有记录（参数）
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public T getObjByHql(String hql, List params) throws Exception {
		T t = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		if (null != query) {
			t = (T) query.uniqueResult();
		}
		return t;
	}

	/**
	 * 查询所有记录（参数）
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public T getObjByHql(String hql, Object[] params) throws Exception {
		T t = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (null != query) {
			t = (T) query.uniqueResult();
		}
		return t;
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 * @param params
	 *            参数列表
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页显示条数
	 * @return
	 * @throws Exception
	 */
	public ResultPage getResultPage(String hql, List params, int page,
			int pageRows) throws Exception {
		if (pageRows == 0)
			pageRows = 1;
		List<T> ls = this.queryByHql(hql, params);
		int rows = ls == null ? 0 : ls.size();
		int p1 = page;
		if (((page - 1) * pageRows) >= rows)
			p1 = rows / pageRows;
		if (p1 == 0) {
			return new ResultPage(new ArrayList(), rows, p1, pageRows);
		} else {
			return new ResultPage(queryPageByHql(hql, params, p1, pageRows),
					rows, p1, pageRows);
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 * @param params
	 *            参数列表
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页显示条数
	 * @return
	 * @throws Exception
	 */
	public ResultPage getResultPage(String hql, Object[] params, int page,
			int pageRows) throws Exception {
		if (pageRows == 0)
			pageRows = 1;
		List<T> ls = this.queryByHql(hql, params);
		int rows = ls == null ? 0 : ls.size();
		int p1 = page;
		if (((page - 1) * pageRows) >= rows)
			p1 = rows / pageRows;
		if (p1 == 0) {
			return new ResultPage(new ArrayList(), rows, p1, pageRows);
		} else {
			return new ResultPage(queryPageByHql(hql, params, p1, pageRows),
					rows, p1, pageRows);
		}
	}

	/**
	 * 查询所有记录分页使用
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页显示条数
	 * @return
	 */
	private List<T> queryPageByHql(String hql, List params, int page,
			int pageRows) throws Exception {
		List<T> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		query.setMaxResults(pageRows);
		query.setFirstResult(((page - 1) * pageRows));
		list = query.list();
		return list;
	}
	
	/**
	 * 支持top 查询 hql
	 * @author dusd
	 * @date 2013-11-26
	 * @param hql
	 * @param params
	 * @param topNum
	 * @return
	 */
	public List<T> queryListByHqlTopNum(String hql, List params, int topNum) {
		List<T> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		query.setMaxResults(topNum);
		query.setFirstResult(0);
		list = query.list();
		return list;
	}

	/**
	 * 查询所有记录分页使用
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @param page
	 *            当前页
	 * @param pageRows
	 *            每页显示条数
	 * @return
	 */
	private List<T> queryPageByHql(String hql, Object[] params, int page,
			int pageRows) throws Exception {
		List<T> list = null;
		Query query = getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setMaxResults(pageRows);
		query.setFirstResult(((page - 1) * pageRows));
		list = query.list();
		return list;
	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param object
	 * @return
	 */
	public T onLoad(String id) throws Exception {
		if (null == id || "".equals(id)) {
			return null;
		} else {
			return (T) getCurrentSession().load(this.clazz, id);
		}
	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param object
	 * @return
	 */
	public T onLoad(Integer id) throws Exception {
		if (null == id || "".equals(id)) {
			return null;
		} else {
			return (T) getCurrentSession().load(this.clazz, id);
		}
	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param object
	 * @return
	 */
	public T onGet(String id) throws Exception {
		if (null == id || "".equals(id)) {
			return null;
		} else {
			return (T) getCurrentSession().get(this.clazz, id);
		}
	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param object
	 * @return
	 */
	public T onGet(Integer id) throws Exception {
		if (null == id || "".equals(id)) {
			return null;
		} else {
			return (T) getCurrentSession().get(this.clazz, id);
		}
	}

	/**
	 * 清除缓存对象
	 * 
	 * @return
	 */
	public void evict(String id) throws Exception {
		// getSessionFactory().evict(clazz, id);// 清除一级缓存
		Session session = this.getCurrentSession();
		session.evict(session.get(this.clazz, id));// 清除二级缓存
	}

	/**
	 * 保存
	 * 
	 * @param object
	 * @return
	 */
	public void save(T t) throws Exception {
		getCurrentSession().save(t);
	}

	/**
	 * 更新
	 * 
	 * @param object
	 * @return
	 */
	public void update(T t) throws Exception {
		getCurrentSession().update(t);
	}

	/**
	 * 保存或更新
	 * 
	 * @param object
	 * @return
	 */
	public void saveOrUpdate(T t) throws Exception {
		getCurrentSession().saveOrUpdate(t);
	}

	/**
	 * 删除
	 * 
	 * @param object
	 * @return
	 */
	public void delete(T t) throws Exception {
		getCurrentSession().delete(t);
	}

	/**
	 * 删除
	 * 
	 * @param object
	 */
	public void delete(String oid) throws Exception {
		Session session = this.getCurrentSession();
		T t = (T) session.get(this.clazz, oid);
		session.delete(t);
	}

	/**
	 * 根据hql更新删除
	 * 
	 * @param hql
	 * @param objects
	 */
	public void batchEntityByHQL(String hql, Object[] paramList) {
		Query q = getCurrentSession().createQuery(hql);
		for (int i = 0; i < paramList.length; i++) {
			q.setParameter(i, paramList[i]);
		}
		q.executeUpdate();
	}
	/**
	 * 根据hql更新删除
	 * 
	 * @param hql
	 * @param objects
	 */
	public void batchEntityByHQL(String hql, List<Object> paramList) {
		Query q = getCurrentSession().createQuery(hql);
		for (int i = 0; i < paramList.size(); i++) {
			q.setParameter(i, paramList.get(i));
		}
		q.executeUpdate();
	}

	/**
	 * 获取唯一值
	 * 
	 * @param hql
	 * @param objects
	 */
	public Object uniqueResult(String hql, Object... objects) {
		Query q = getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
	}

	/**
	 * 执行一个存储过程 为了信息共享，不返回任何值
	 * 
	 * @date 2013-05
	 */
	public void execProc(String procName, Object objs[]) throws Exception {
		Connection conn = SessionFactoryUtils
				.getDataSource(getSessionFactory()).getConnection();
		boolean tmp = conn.getAutoCommit();
		conn.setAutoCommit(false);

		CallableStatement proc = null;
		int n = 0;
		// Oracle与sqlserver调用方式不同，参数位置也不一样。
		if (BaseParameter.getDbType().equals("oracle")) {
			proc = conn.prepareCall("{call ? := " + procName + "}");
			n = 2;
		} else {
			proc = conn.prepareCall("{call " + procName + "}");
			n = 1;
		}
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {
				if (objs[i] instanceof Integer) {
					proc.setInt(i + n, ((Integer) objs[i]).intValue());
				} else if (objs[i] instanceof Long) {
					proc.setLong(i + n, ((Long) objs[i]).longValue());
				} else if (objs[i] instanceof Date) {
					proc.setDate(i + n,
							new java.sql.Date(((Date) objs[i]).getTime()));
				} else if (objs[i] instanceof Double) {
					proc.setDouble(i + n, ((Double) objs[i]).doubleValue());

				} else {
					if (objs[i] != null) {
						proc.setString(i + n, objs[i].toString());
					} else {
						proc.setString(i + n, "");
					}
				}
			}
		}

		proc.executeUpdate();
		if (conn != null) {
			conn.commit();
			conn.setAutoCommit(tmp);
		}
		proc.close();
		conn.close();
	}

	/**
	 * 执行一个存储过程
	 * 
	 * @param procName存储过程名称
	 * @param objs参数集合
	 * @modify meidj 2012-05-20（直接返回list<map>）
	 * @throws Exception
	 * 
	 */
	public List<Hashtable<String, Object>> execProcRetList(String procName,
			Object objs[]) throws Exception {
		List<Hashtable<String, Object>> ls = null;
		Connection conn = SessionFactoryUtils
				.getDataSource(getSessionFactory()).getConnection();
		boolean tmp = conn.getAutoCommit();
		conn.setAutoCommit(false);

		CallableStatement proc = null;
		ResultSet rs = null;
		int n = 0;
		// Oracle与sqlserver调用方式不同，参数位置也不一样。
		if (BaseParameter.getDbType().equals("oracle")) {
			proc = conn.prepareCall("{call ? := " + procName + "}");
			n = 2;
		} else {
			proc = conn.prepareCall("{call " + procName + "}");
			n = 1;
		}
		if (objs != null)
			for (int i = 0; i < objs.length; i++) {
				if (objs[i] instanceof Integer) {
					proc.setInt(i + n, ((Integer) objs[i]).intValue());
				} else if (objs[i] instanceof Long) {
					proc.setLong(i + n, ((Long) objs[i]).longValue());
				} else if (objs[i] instanceof Date) {
					proc.setDate(i + n,
							new java.sql.Date(((Date) objs[i]).getTime()));
				} else if (objs[i] instanceof Double) {
					proc.setDouble(i + n, ((Double) objs[i]).doubleValue());

				} else {
					if (objs[i] != null)
						proc.setString(i + n, objs[i].toString());
					else
						proc.setString(i + n, "");
				}

			}

		if (BaseParameter.getDbType().equals("oracle")) {
			proc.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			proc.execute();
			rs = (ResultSet) proc.getObject(1);
		} else {
			rs = proc.executeQuery();
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		if (null != rsmd) {
			ls = new ArrayList<Hashtable<String, Object>>();
			Map<Integer, String> map = new Hashtable<Integer, String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				map.put(i, rsmd.getColumnName(i));
			}
			while (rs.next()) {
				Hashtable<String, Object> ht = new Hashtable<String, Object>();
				Set<Integer> keySet = map.keySet();
				for (Integer key : keySet) {
					ht.put(map.get(key),
							rs.getObject(map.get(key)) == null ? "" : rs
									.getObject(map.get(key)));
				}
				ls.add(ht);
			}
		}

		if (conn != null) {
			conn.commit();
			conn.setAutoCommit(tmp);
		}
		if (rs != null) {
			rs.close();
		}
		proc.close();
		conn.close();
		return ls;
	}

	/**
	 * 执行一个存储过程 是为了存储过程的分页
	 * 
	 * @date 2013-05
	 */
	public ResultPage execProcPage(String procName, Object objs[],
			Object outobjs[], int page, int pageRows) throws Exception {
		List<Hashtable<String, Object>> ls = null;
		ResultPage rp = null;
		Connection conn = SessionFactoryUtils
				.getDataSource(getSessionFactory()).getConnection();
		boolean tmp = conn.getAutoCommit();
		conn.setAutoCommit(false);

		CallableStatement proc = null;
		ResultSet rs = null;
		int n = 0;
		// Oracle与sqlserver调用方式不同，参数位置也不一样。
		if (BaseParameter.getDbType().equals("oracle")) {
			proc = conn.prepareCall("{call ? := " + procName + "}");
			n = 2;
		} else {
			proc = conn.prepareCall("{call " + procName + "}");
			n = 1;
		}
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {
				if (objs[i] instanceof Integer) {
					proc.setInt(i + n, ((Integer) objs[i]).intValue());
				} else if (objs[i] instanceof Long) {
					proc.setLong(i + n, ((Long) objs[i]).longValue());
				} else if (objs[i] instanceof Date) {
					proc.setDate(i + n,
							new java.sql.Date(((Date) objs[i]).getTime()));
				} else if (objs[i] instanceof Double) {
					proc.setDouble(i + n, ((Double) objs[i]).doubleValue());

				} else {
					if (objs[i] != null)
						proc.setString(i + n, objs[i].toString());
					else
						proc.setString(i + n, "");
				}

			}
			n = n + objs.length;
		}

		if (BaseParameter.getDbType().equals("oracle")) {
			proc.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			if (outobjs != null) {
				for (int i = 0; i < outobjs.length; i++) {
					if (outobjs[i] instanceof Integer)
						proc.registerOutParameter(n + i,
								oracle.jdbc.OracleTypes.INTEGER);
					else if (outobjs[i] instanceof Long)
						proc.registerOutParameter(n + i,
								oracle.jdbc.OracleTypes.NUMBER);
					else if (outobjs[i] instanceof Double)
						proc.registerOutParameter(n + i,
								oracle.jdbc.OracleTypes.NUMBER);
					else
						proc.registerOutParameter(n + i,
								oracle.jdbc.OracleTypes.VARCHAR);
				}
			}
			proc.execute();
			rs = (ResultSet) proc.getObject(1);
			if (outobjs != null) {
				for (int i = 0; i < outobjs.length; i++) {
					if (outobjs[i] instanceof Integer)
						outobjs[i] = proc.getInt(i + n);
					else if (outobjs[i] instanceof Long)
						outobjs[i] = proc.getLong(i + n);
					else if (outobjs[i] instanceof Double)
						outobjs[i] = proc.getDouble(i + n);
					else if (outobjs[i] instanceof String)
						outobjs[i] = proc.getString(i + n);
				}
			}
		} else {
			if (outobjs != null) {
				for (int i = 0; i < outobjs.length; i++) {
					proc.registerOutParameter(n + i - 1, java.sql.Types.INTEGER);
					proc.setObject(i + n - 1, outobjs[i]);
				}
			}
			rs = proc.executeQuery();
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		if (null != rsmd) {
			ls = new ArrayList<Hashtable<String, Object>>();
			Map<Integer, String> map = new Hashtable<Integer, String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				map.put(i, rsmd.getColumnName(i));
			}
			while (rs.next()) {
				Hashtable<String, Object> ht = new Hashtable<String, Object>();
				Set<Integer> keySet = map.keySet();
				for (Integer key : keySet) {
					ht.put(map.get(key),
							rs.getObject(map.get(key)) == null ? "" : rs
									.getObject(map.get(key)));
				}
				ls.add(ht);
			}
		}

		if (outobjs != null) {
			for (int i = 0; i < outobjs.length; i++) {
				outobjs[i] = proc.getObject(n + i - 1);
			}
		}
		if (conn != null) {
			conn.commit();
			conn.setAutoCommit(tmp);
		}
		if (rs != null) {
			rs.close();
		}
		proc.close();
		conn.close();
		rp = new ResultPage(ls, (Integer) outobjs[0], page, pageRows);
		return rp;
	}

	/**
	 * 运行原生sql语句
	 * 
	 * @date 2013-05
	 */
	public void executeUpdateSql(String sqls) throws Exception {
		String[] sql = sqls.split(";");
		Connection conn = SessionFactoryUtils
				.getDataSource(getSessionFactory()).getConnection();
		boolean tmp = conn.getAutoCommit();
		conn.setAutoCommit(false);
		Statement pStmt = conn.createStatement();
		for (int i = 0; i < sql.length; i++) {
			if (sql[i].length() > 0) {
				pStmt.executeUpdate(sql[i]);
			}
		}
		if (conn != null) {
			conn.commit();
			conn.setAutoCommit(tmp);
		}
		pStmt.close();
		conn.close();
	}

	/**
	 * 运行原生sql语句
	 */
	public List executeQuerySql(String sql, Object[] values) throws Exception {
		Connection conn = SessionFactoryUtils
				.getDataSource(getSessionFactory()).getConnection();
		conn.getAutoCommit();
		conn.setAutoCommit(false);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			pStmt.setObject(i + 1, values[i]);
		}
		ResultSet rs = pStmt.executeQuery();
		List res = convertResultSet(rs);
		pStmt.close();
		conn.close();
		return res;
	}
	
	private List convertResultSet(ResultSet rs) throws Exception {
		List list = new ArrayList();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		while (rs.next()) {
			Hashtable tmp = new Hashtable();
			for (int i = 1; i <= colCount; i++) {
				tmp.put(rsmd.getColumnName(i), rs.getObject(i) == null ? ""
						: rs.getObject(i));
			}
			list.add(tmp);
		}
		return list;
	}
	
	/**
	 * 对list进行分页
	 * @author dusd
	 * @date 2013-10-23
	 * @param tmpList
	 * @return
	 */
	public List<Object> resultList(List<Object> totalList,int page, int pageRows) {
		List<Object> l2 = new ArrayList<Object>();
		if(totalList == null || totalList.size() <= 0) return l2;
		int lastNum = pageRows * (page - 1)+ pageRows;
		if (totalList.size() > pageRows) {
			l2 = totalList.subList(pageRows * (page - 1), totalList.size() < lastNum ? totalList.size() : lastNum);
		} else {
			l2 = totalList;
		}
		return l2;
	}

	/**
	 * 根据 hql语句 进行增删改操作
	 * @author zhangCM
	 * @date 2013-12-9
	 */
	public void execUpdateByHql(String hql, Object[] params) {
		Query q = getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		q.executeUpdate();
	}
	
	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
}
