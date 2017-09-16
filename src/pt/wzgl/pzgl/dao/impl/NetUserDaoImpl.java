package pt.wzgl.pzgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.wzgl.pzgl.dao.INetUserDao;
import pt.wzgl.pzgl.pojo.NetUser;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

/**
 * 网上申报用户 dao类
 * 
 * @author : yaosw
 * @date : 2014-09-23
 */
public class NetUserDaoImpl extends BaseDaoImpl<NetUser> implements INetUserDao {

	@Override
	public NetUser getNetUserById(String id) throws Exception {
		return super.onGet(id);
	}

	@Override
	public void portraitSetting(String url,NetUser netUser) throws Exception {
		super.executeUpdateSql("update net_user set user_head_image = '" + url + "' where ID='" + netUser.getId() + "'");
	}

	@Override
	public NetUser getNetUserByEmail(String userEmail) throws Exception {
		String hql = "from NetUser u where u.userEmail = ? and (u.userDelStatus is null or u.userDelStatus = ? ) ";
		Object[] params = new Object[] {userEmail, BaseParameter.NO};
		List<NetUser> users = super.queryByHql(hql, params);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}else {
			return null;
		}
	}

	@Override
	public NetUser findUserByCodeAndPass(String code) throws Exception {
		ArrayList<Object> parmaList = new ArrayList<Object>();
		String hql = " from NetUser u where u.userLogin = ? and (u.userDelStatus is null or u.userDelStatus = ?) ";
		parmaList.add(code);
		parmaList.add(BaseParameter.NO);
		return super.getFirstObject(hql, parmaList.toArray());
	}

	@Override
	public ResultPage findNetUsers(int page, int pageRows,NetUser user) throws Exception {
		StringBuffer sb = new StringBuffer(" from NetUser u where 1=1");
		ArrayList<Object> lisParams = new ArrayList<Object>();
		if( user != null ){
			if( user.getUserLogin() != null && !"".equals(user.getUserLogin())){
				sb.append(" and u.userLogin like ?");
				lisParams.add("%"+user.getUserLogin()+"%");
			}
			if( user.getUserName() != null && !"".equals(user.getUserName())){
				sb.append(" and u.userName like ?");
				lisParams.add("%"+user.getUserName()+"%");
			}
			if( user.getUserAddress() != null && !"".equals(user.getUserAddress())){
				sb.append(" and u.userAddress = ?");
				lisParams.add(user.getUserAddress());
			}
		}
		sb.append(" and ( u.userDelStatus = ? or u.userDelStatus is null ) ");
		lisParams.add( BaseParameter.NO );
		return super.getResultPage(sb.toString(), lisParams, page, pageRows);
	}
	
	public void saveOrUpdate(NetUser netUser)throws Exception{
		super.saveOrUpdate(netUser);
	}

	@Override
	public NetUser findUserByUserParams(NetUser netUser) throws Exception {
		StringBuffer sb = new StringBuffer("from NetUser a where (a.userDelStatus is null or a.userDelStatus = ?) ");
		ArrayList<Object> parmaList = new ArrayList<Object>();
		parmaList.add( BaseParameter.NO );
		if( netUser != null ){
			if( netUser.getUserCardno() != null && netUser.getUserCardno().trim().length() > 0 ){
				sb.append(" and a.userCardno = ? ");
				parmaList.add( netUser.getUserCardno() );
			}
			if( netUser.getUserEmail() != null && netUser.getUserEmail().trim().length() > 0 ){
				sb.append(" and a.userEmail = ? ");
				parmaList.add( netUser.getUserEmail() );
			}
			if( netUser.getUserPhone() != null && netUser.getUserPhone().trim().length() > 0 ){
				sb.append(" and a.userPhone = ? ");
				parmaList.add( netUser.getUserPhone() );
			}
			if( netUser.getUserLogin() != null && netUser.getUserLogin().trim().length() > 0 ){
				sb.append(" and a.userLogin = ? ");
				parmaList.add( netUser.getUserLogin() );
			}
			if( !Common.isNullOrSpace(netUser.getUserSecrQuesAnswA()) ){
				sb.append(" and a.userSecrQuesAnswA = ? ");
				parmaList.add(netUser.getUserSecrQuesAnswA());
			}
			if( !Common.isNullOrSpace(netUser.getUserSecrQuesAnswB()) ){
				sb.append(" and a.userSecrQuesAnswB = ? ");
				parmaList.add(netUser.getUserSecrQuesAnswB());
			}
			if( !Common.isNullOrSpace(netUser.getUserType()) ){
				sb.append(" and a.userType = ? ");
				parmaList.add(netUser.getUserType());
			}
		}
		return super.getFirstObject(sb.toString(), parmaList.toArray());
	}
}
