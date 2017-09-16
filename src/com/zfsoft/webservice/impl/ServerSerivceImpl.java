package com.zfsoft.webservice.impl;

import javax.jws.WebService;
import com.zfsoft.webservice.ServerService;

/**
 * webservice测试实现类
 */
@WebService(endpointInterface="com.zfsoft.webservice.ServerService")
public class ServerSerivceImpl implements ServerService{
	
	/**
     * webservice 测试
     * @param paramName 名称
     * @return
     */
	@Override
	public String getGasData(String paramName) {
		System.out.println("INFO:Server已经启动:"+paramName);
		return "Service";
	}

}
