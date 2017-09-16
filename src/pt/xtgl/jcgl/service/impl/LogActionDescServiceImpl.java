package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ILogActionDescDao;
import pt.xtgl.jcgl.pojo.LogActionDesc;
import pt.xtgl.jcgl.service.ILogActionDescService;
import util.Common;
import util.ResultPage;
import util.base.service.BaseServiceImpl;
import util.interceptor.LoginfoInterceptor;

public class LogActionDescServiceImpl extends BaseServiceImpl<LogActionDesc> implements ILogActionDescService{

	public LogActionDescServiceImpl(ILogActionDescDao logActionDescDao) {
		super(logActionDescDao);
		this.logActionDescDao = logActionDescDao;
	}
	
	private ILogActionDescDao logActionDescDao;

	@Override
	public void deleteLogActionDesc(LogActionDesc logActionDesc)
			throws Exception {
		logActionDescDao.deleteLogActionDesc(logActionDesc);
	}

	@Override
	public LogActionDesc queryLogActionDesc(String oid) throws Exception {
		return logActionDescDao.queryLogActionDesc(oid);
	}

	@Override
	public ResultPage queryLogActionDescList(LogActionDesc sqlObj, int page,
			int pageRows) throws Exception {
		return logActionDescDao.queryLogActionDescList(sqlObj, page, pageRows);
	}

	@Override
	public void saveLogActionDesc(LogActionDesc logActionDesc) throws Exception {
		if(Common.isNullOrSpace(logActionDesc.getOid())){
			logActionDesc.setOid(null);
		}
		logActionDescDao.saveLogActionDesc(logActionDesc);
		//更新map
		LoginfoInterceptor.logMap.put(logActionDesc.getActionUrl(), logActionDesc);
	}

	@Override
	public List<LogActionDesc> queryLogActionDescList() throws Exception {
		return logActionDescDao.queryLogActionDescList();
	}
	
}
