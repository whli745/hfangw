package util.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.service.ISysModuleService;
import util.BaseParameter;

/**
 * 自动加载信息类,处理系统菜单
 * 
 */
public class AutoLoadListener implements ServletContextListener {
	private static ISysModuleService sysModuleService;

	// private static H

	public void contextInitialized(ServletContextEvent event) {
		try {
			List<SysModule> actionRequest = sysModuleService
					.queryAllModuleByTypeList();// action请求菜单

			// 设置所有action请求菜单到系统静态变量中
			Map<String, String> actionMap = new HashMap<String, String>();
			for (SysModule module : actionRequest) {
				if (null != module.getModuleUrl()
						&& !"".equals(module.getModuleUrl())
						&& module.getModuleUrl().indexOf(".action") > 0) {
					if (module.getModuleUrl().indexOf("?") > 0) {
						actionMap.put(
								module.getModuleUrl().substring(0,
										module.getModuleUrl().indexOf("?")),
								module.getModuleId());
					} else {
						actionMap.put(module.getModuleUrl(),
								module.getModuleId());
					}
				}
			}
			BaseParameter.initActionRequest = actionMap;

			/*
			 * 读取 config.properties 配置文件中的信息，存放到 configMap中
			 */

			// String path = AutoLoadListener.class.getResource("/").getPath();
			String path = event.getServletContext().getRealPath(
					"/WEB-INF/classes");
			InputStream is = new FileInputStream(new File(path
					+ "/config.properties"));
			Reader isReader = new InputStreamReader(is, "GBK");
			Properties pro = new Properties();
			pro.load(isReader);
			Set<Object> keys = pro.keySet();
			for (Object key : keys) {
				BaseParameter.configMap.put(key.toString(),
						pro.getProperty((String) key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		BaseParameter.initActionRequest.clear();
	}

	public static ISysModuleService getSysModuleService() {
		return sysModuleService;
	}

	public static boolean setSysModuleService(ISysModuleService sysModuleService) {
		AutoLoadListener.sysModuleService = sysModuleService;
		return true;
	}

	public static void main(String[] args) {
		Map<String, String> configMap = new HashMap<String, String>();
		try {
			String path = AutoLoadListener.class.getResource("/").getPath();
			InputStream is = new FileInputStream(new File(path
					+ "config.properties"));
			Reader isReader = new InputStreamReader(is, "GBK");
			Properties pro = new Properties();
			pro.load(isReader);
			Set<Object> keys = pro.keySet();
			for (Object key : keys) {
				configMap.put(key.toString(), pro.getProperty((String) key));
			}

			for (String str : configMap.keySet()) {
				System.out.println(str + ", " + configMap.get(str));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
