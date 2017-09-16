package util;

import pt.xtgl.jcgl.pojo.SysAppParam;
import pt.xtgl.jcgl.service.ISysAppParamService;

public class SysAppParamUtil {
	
	public static ISysAppParamService getISysAppParamService(){
		return (ISysAppParamService)Common.getService("sysAppParamService");
	}
	
	/**
	 * 根据参数编码获取系统参数
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	public static SysAppParam getSysAppParamByParamCode(String paramCode) throws Exception{
		if(!Common.isNullOrSpace(paramCode)){
			return getISysAppParamService().getSysAppParamByParamCode(paramCode);
		}
		return null;
	}
	
	/**
	 * 根据参数编码获取系统参数值
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	public static String getParamValByParamCode(String paramCode) throws Exception{
		SysAppParam sysAppParam= getSysAppParamByParamCode(paramCode);
		if(sysAppParam!=null){
			return sysAppParam.getParamVal();
		}
		return "";
	}
	
	/**
	 * 根据参数Id获取系统参数
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	public static SysAppParam getSysAppParamByParamId(String paramId) throws Exception{
		if(!Common.isNullOrSpace(paramId)){
			return getISysAppParamService().getSysAppParamByParamCode(paramId);
		}
		return null;
	}
	
	/**
	 * 根据参数Id获取系统参数值
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	public static String getParamValByParamId(String paramId) throws Exception{
		SysAppParam sysAppParam= getSysAppParamByParamId(paramId);
		if(sysAppParam!=null){
			return sysAppParam.getParamVal();
		}
		return "";
	}
}
