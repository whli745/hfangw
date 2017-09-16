package dwr;

import pt.xtgl.jcgl.service.ISysLocalService;
import util.Common;

/**
 * 主要营业地 DWR
 * @author yaozhiyuan
 * @date 2015-11-19
 */
public class SysLocalManageDwr {
	private ISysLocalService sysLocalService;
	public String getLocalListByTopId(String id){
		try {
			if(!Common.isNullOrSpace(id)){
				return sysLocalService.getSysLocalListToJsonToCity(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	public ISysLocalService getSysLocalService() {
		return sysLocalService;
	}

	public void setSysLocalService(ISysLocalService sysLocalService) {
		this.sysLocalService = sysLocalService;
	}
	
}
