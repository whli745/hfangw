package pt.wzgl.pzgl.dao;

import pt.wzgl.pzgl.pojo.NetUser;
import util.ResultPage;


/**
 * 网上申报用户 dao接口
 * 
 * @author : yaosw
 * @date : 2014-09-23
 */
public interface INetUserDao{
	/**
	 * 根据主键获取对象
	 * @author zhangh
	 * @since 2014-9-26
	 * @param id 主键
	 * @return 对象
	 * @throws Exception 异常抛出
	 */
	NetUser getNetUserById(String id) throws Exception;
	
	/**
	 * 根据用户邮箱地址获取用户
	 * @author liulx
	 * @since 2014年10月8日15:10:50
	 * @param userEmail 主键
	 * @return 对象
	 * @throws Exception 异常抛出
	 */
	NetUser getNetUserByEmail(String userEmail) throws Exception;
	
	/**
	 * 修改头像
	 * @param url	头像地址
	 * @param netUser	用户对象
	 * @throws Exception
	 * @createDate 2014-9-30
	 * @author Liangmh
	 */
	void portraitSetting(String url,NetUser netUser) throws Exception;
	
	/**
	 * 根据用户输入的登录名code判断数据库中是否存在该用户
	 * @param code
	 * 				登录名
	 * @return
	 * @throws Exception
	 * @createDate 2014-10-9
	 * @author Liangmh
	 */
	NetUser findUserByCodeAndPass( String code ) throws Exception;
	
	/**
	 * 查询所有的注册用户
	 * @return
	 * @throws Exception
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	ResultPage findNetUsers(int page, int pageRows,NetUser user) throws Exception;
	
	/**
	 * 添加或修改
	 * @param netUser
	 * @createDate 2014-11-13
	 * @author Liangmh
	 */
	void saveOrUpdate( NetUser netUser ) throws Exception;
	
	/**
	 * 根据用户参数查询(不限定查询条件)
	 * @param netUser
	 * @return
	 * @throws Exception
	 * @createDate 2014-11-16
	 * @author Liangmh
	 */
	NetUser findUserByUserParams(NetUser netUser) throws Exception;
}
