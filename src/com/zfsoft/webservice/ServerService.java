package com.zfsoft.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

/**
 * webservice 测试接口
 */
@WebService
public interface ServerService{
    
	/**
     * @Description webservice 测试
     * @param paramName 名称
     * @return
     */
	@WebMethod
	@WSDLDocumentation("方法注释")
    String getGasData(@WebParam(name = "paramName")String paramName);
}
