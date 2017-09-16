package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ILogActionDescDao;
import pt.xtgl.jcgl.pojo.LogActionDesc;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class LogActionDescDaoImpl extends BaseDaoImpl<LogActionDesc> implements ILogActionDescDao{

	@Override
	public void deleteLogActionDesc(LogActionDesc logActionDesc)
			throws Exception {
		delete(logActionDesc);
	}

	@Override
	public LogActionDesc queryLogActionDesc(String oid) throws Exception {
		return super.onGet(oid);
	}

	@Override
	public ResultPage queryLogActionDescList(LogActionDesc sqlObj, int page,
			int pageRows) throws Exception {
		String hql=" from pt.xtgl.jcgl.pojo.LogActionDesc where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(sqlObj!=null&&!Common.isNullOrSpace(sqlObj.getActionDesc())){
			hql+=" and actionDesc like ?";
			params.add("%"+sqlObj.getActionDesc().trim()+"%");
		}
		if(sqlObj!=null&&!Common.isNullOrSpace(sqlObj.getActionUrl())){
			hql+=" and actionUrl like ?";
			params.add("%"+sqlObj.getActionUrl().trim()+"%");
		}
		if(sqlObj!=null&&!Common.isNullOrSpace(sqlObj.getUseFlag())){
			hql+=" and useFlag = ?";
			params.add(sqlObj.getUseFlag().trim());
		}
		hql += " order by oid desc";
		return super.getResultPage(hql, params, page, pageRows);
	}

	@Override
	public void saveLogActionDesc(LogActionDesc logActionDesc) throws Exception {
		saveOrUpdate(logActionDesc);
	}

	@Override
	public List<LogActionDesc> queryLogActionDescList() throws Exception {
		String hql = "from LogActionDesc";
		return super.queryByHql(hql);
	}

}
