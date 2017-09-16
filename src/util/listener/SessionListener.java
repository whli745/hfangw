package util.listener;

//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	//session的创建方法
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		//得到session
		//HttpSession session = httpSessionEvent.getSession();
	}

	//session的销毁方法
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		//得到session
		//HttpSession session = httpSessionEvent.getSession();
	}

}
