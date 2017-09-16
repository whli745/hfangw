package pt.wzgl.pzgl.service;

import javax.servlet.http.HttpServletRequest;

import pt.wzgl.pzgl.pojo.NetUser;
import util.ResultPage;


/**
 * 网上申报用户 service接口
 * 
 * @author : yaosw
 * @date : 2014-09-23
 */
public interface INetUserService{
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
	 * 修改对象
	 * @author zhangh
	 * @since 2014-9-26
	 * @param netUser 网上申报用户对象
	 * @throws Exception 异常抛出
	 */
	void updateNetUser(NetUser netUser) throws Exception;
	
	/**
	 * 修改头像
	 * @param photoUrl	头像URL地址
	 * @param netUser	用户对象
	 * @throws Exception
	 * @createDate 2014-9-30
	 * @author Liangmh
	 */
	void portraitSetting(String photoUrl,NetUser netUser) throws Exception;
	
	/**
	 * 根据用户输入的登录名code判断数据库中是否存在该用户
	 * @param code
	 * @return
	 * @throws Exception
	 * @createDate 2014-10-9
	 * @author Liangmh
	 */
	NetUser findUserByCodeAndPass( String code ) throws Exception;
	
	/**
	 * 查询所有的注册用户
	 * @param page
	 * @param pageRows
	 * @return
	 * @throws Exception
	 * @createDate 2014-10-10
	 * @author Liangmh
	 */
	ResultPage findNetUsers(int page,int pageRows,NetUser user) throws Exception;
	
	/**
	 * 保存新用户
	 * @author zhangh
	 * @since 2014-11-10
	 * @param netUser 网上申报用户对象
	 * @throws Exception 异常抛出
	 */
	void saveNetUser(NetUser netUser) throws Exception;
	
	/**
	 * 保存或修改
	 * @param netUser
	 * @throws Exception
	 * @createDate 2014-11-13
	 * @author Liangmh
	 */
	void saveOrUpdate( NetUser netUser ) throws Exception;
	
	/**
	 * 验证用户参数唯一性
	 * @param user
	 * @return
	 * @createDate 2014-11-25
	 * @author Liangmh
	 */
	NetUser checkUserForUnicode( NetUser user ) throws Exception;
	/**
	 * 网站登录
	 * @author chail
	 * @since 2016-1-6
	 * @param userName 用户名
	 * @param userPass 密码
	 * @param request
	 * @return
	 * @throws Exception 异常捕获.
	 */
	String webCheckLogin(String userName, String userPass, HttpServletRequest request) throws Exception;
	
}
