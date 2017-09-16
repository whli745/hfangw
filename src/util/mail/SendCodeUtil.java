package util.mail;

import java.util.UUID;

import util.BaseParameter;
import util.Common;
import util.SysAppParamUtil;

/**
 * 发送邮件工具类
 * @author zhangh
 * @since 2014-10-8 
 */
public class SendCodeUtil {
	/**  
	 * 生成32位编码  
     * @return string  
     */    
    public  String getEmailCode(){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
        return uuid;    
    } 
    
    /**
     * 此处是激活安全邮箱的发送内容   以下的链接内容需要根据项目的修改而修改
     * @author zhangh
     * @since 2014-10-8
     * @param id 当前登录用户的主键
     * @param vaidCode 发送邮件产生的随机码
     * @return 字符串
     * @throws Exception 
     */
    public  String getMsgText(String id , String vaidCode,String basePath) throws Exception{
    	String text ;
    	String xmfwdz = SysAppParamUtil.getParamValByParamId(BaseParameter.MAIL_SEND_XMFWDZ);
    	if(!Common.isNullOrSpace(xmfwdz)){
            text = new StringBuffer("<p>请点击以下链接，完成绑定！</p> <p> <a href='").append(xmfwdz).append("/saftCenter.action?validationid=").append(id).append("&vaidCode=").append(vaidCode).append("' target='_blank' mce_href='").append(xmfwdz).append("/saftCenter.action?validationid=").append(id).append("&amp;vaidCode=").append(vaidCode).append("'>").append(xmfwdz).append("/saftCenter.action?validationid=").append(id).append("&amp;vaidCode=").append(vaidCode).append("</a></p><p> 如果通过点击以上链接无法访问，请将该网址复制并粘贴至浏览器地址栏进行访问。</p><p></p> ").toString();    

    	}else{
            text = new StringBuffer("<p>请点击以下链接，完成绑定！</p> <p> <a href='" ).append( basePath ).append( "/saftCenter.action?validationid=").append(id).append("&vaidCode=").append(vaidCode).append("' target='_blank' mce_href='").append(basePath).append("/saftCenter.action?validationid=").append(id).append("&amp;vaidCode=").append(vaidCode).append("'>").append(basePath).append("/saftCenter.action?validationid=").append(id).append("&amp;vaidCode=").append(vaidCode).append("</a></p><p> 如果通过点击以上链接无法访问，请将该网址复制并粘贴至浏览器地址栏进行访问。</p><p></p> ").toString();    
    	}
        return text;    
    } 
    
    /**
     * 此处是找回密码时的发送内容   以下的链接内容需要根据项目的修改而修改
     * @author zhangh
     * @since 2014-10-8
     * @param id 当前登录用户的主键
     * @param vaidCode 发送邮件产生的随机码
     * @return 字符串
     * @throws Exception 
     */
    public  String getResetPassMsgText(String id , String vaidCode,String basePath) throws Exception{
    	String text ;
    	String xmfwdz = SysAppParamUtil.getParamValByParamId(BaseParameter.MAIL_SEND_XMFWDZ);
    	if(!Common.isNullOrSpace(xmfwdz)){
            text = "<p>请点击以下链接，完成密码重置！</p> <p> <a href='"+xmfwdz+"/saftCenter.action?resetFlag=1&amp;validationid="+id+"&vaidCode="+vaidCode+"' target='_blank' mce_href='"+xmfwdz+"/saftCenter.action?resetFlag=1&amp;validationid="+id+"&amp;vaidCode="+vaidCode+"'>"+xmfwdz+"/saftCenter.action?resetFlag=1&amp;validationid="+id+"&amp;vaidCode="+vaidCode+"</a></p><p> 如果通过点击以上链接无法访问，请将该网址复制并粘贴至浏览器地址栏进行访问。</p><p></p> ";    

    	}else{
            text = "<p>请点击以下链接，完成密码重置！</p> <p> <a href='"+basePath+"/saftCenter.action?resetFlag=1&amp;validationid="+id+"&vaidCode="+vaidCode+"' target='_blank' mce_href='"+basePath+"/saftCenter.action?resetFlag=1&amp;validationid="+id+"&amp;vaidCode="+vaidCode+"'>"+basePath+"/saftCenter.action?resetFlag=1&amp;validationid="+id+"&amp;vaidCode="+vaidCode+"</a></p><p> 如果通过点击以上链接无法访问，请将该网址复制并粘贴至浏览器地址栏进行访问。</p><p></p> ";    
    	}
        return text;    
    } 
    
   
}
