package util;

import java.lang.reflect.Method;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;

import util.base.BaseObject;

public class CompareObject {
	/**
	 * 复制对象属性
	 * zhujj
	 * 2017-07-17
	 * @param newObj 复制后的对象
	 * @param oldObj 被复制的对象
	 * @param ignoObjects 无需复制的属性
	 */
	@SuppressWarnings("unchecked")
	public static Object compare(Object newObj, Object oldObj,Object[] ignoObjects)
			throws Exception {
		// 如果其中任意对象为null，直接返回null
		if (oldObj == null || newObj == null) {
			return null;
		}
		List<Object> ignoObjList = Arrays.asList(ignoObjects);
		Method[] oldMethods = oldObj.getClass().getMethods();
		Method[] newMethods = newObj.getClass().getMethods();
		for (Method oldMethod : oldMethods) {
			String oldMethodString = oldMethod.getName();
			if (oldMethodString.toLowerCase().indexOf("get") == 0) {
				String oldFiled = oldMethodString.substring(3,
						oldMethodString.length());
				if(ignoObjList.contains(oldFiled)){
					continue;
				}
				for (Method newMethod : newMethods) {
					String newMethodString = newMethod.getName();
					if (newMethodString.toLowerCase().indexOf("set") == 0) {
						String newFiled = newMethodString.substring(3,
								newMethodString.length());
						if (newFiled.equals(oldFiled)) {
							Object oldValue = oldMethod.invoke(oldObj, null);
							newMethod.invoke(newObj, oldValue);
						}
					}
				}
			}
		}
		return newObj;
	}
	
	public static BaseObject compare(BaseObject newObj, BaseObject oldObj)
			throws Exception {
		// 如果其中任意对象为null，直接返回null
		if (oldObj == null || newObj == null) {
			return null;
		}
		Method[] oldMethods = oldObj.getClass().getMethods();
		Method[] newMethods = newObj.getClass().getMethods();
		for (Method oldMethod : oldMethods) {
			String oldMethodString = oldMethod.getName();
			if (oldMethodString.toLowerCase().indexOf("get") == 0) {
				String oldFiled = oldMethodString.substring(3,
						oldMethodString.length());
				for (Method newMethod : newMethods) {
					String newMethodString = newMethod.getName();
					if (newMethodString.toLowerCase().indexOf("set") == 0) {
						String newFiled = newMethodString.substring(3,
								newMethodString.length());
						if (newFiled.equals(oldFiled)) {
							Object oldValue = oldMethod.invoke(oldObj, null);
							newMethod.invoke(newObj, oldValue);
						}
					}
				}
			}
		}
		return newObj;
	}
}
