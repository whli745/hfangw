package util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import util.BaseParameter;
import util.SysAppParamUtil;

import com.ykmaiz.mail.MyAuth;

/**
 * 发送邮件实体类 需要配置以下实体类的属性才能够使用
 * 
 * @author zhangh
 * @since 2014-9-29
 */
public class MailUtil {
	// 发送人的邮件服务器地址
	private String host;
	// 是否启动调式模式 默认是false
	private boolean debug = false;
	// 发送人的用户名
	private String username;
	// 发送人的密码
	private String password;
	// 发送人的邮件地址
	private String frommail;

	public MailUtil(String host, String username, String password, boolean debug) {
		this.host = host;
		this.debug = debug;
		this.username = username;
		this.password = password;
	}

	public MailUtil() {

	}
	
	public void send(String tomail, String msgTitle, String msgText) throws Exception {
		
		String host = SysAppParamUtil.getParamValByParamId(BaseParameter.MAIL_SEND_YJFWQDXZ);
		String username = SysAppParamUtil.getParamValByParamId(BaseParameter.MAIL_SEND_FSRYHM);
		String password = SysAppParamUtil.getParamValByParamId(BaseParameter.MAIL_SEND_FSRMM);
		String frommail = SysAppParamUtil.getParamValByParamId(BaseParameter.MAIL_SEND_YJDZ);
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", Boolean.valueOf(true));
		if (this.debug) {
			props.put("mail.debug", Boolean.valueOf(this.debug));
		}
		
		MyAuth auth = new MyAuth(username,password);

		Session session = Session.getInstance(props, auth);

		session.setDebug(this.debug);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(frommail));
		InternetAddress[] address = { new InternetAddress(tomail) };
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(msgTitle);
		msg.setSentDate(new Date());
		// 这是发送带格式的文本
		msg.setContent(msgText, "text/html;charset=gbk");
		// msg.setText(msgText);//这个是纯文本

		Transport.send(msg);
		// System.out.println(" 邮件推送成功 >>> " + tomail);
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFrommail() {
		return frommail;
	}
	public void setFrommail(String frommail) {
		this.frommail = frommail;
	}

}
