package util;

import pt.xtgl.jcgl.service.ISysDictService;

public class SysDictUtil {
	public static String getDictNameByDictCode(String dictCode) throws Exception{
		ISysDictService s = (ISysDictService)Common.getService("sysDictService");
		if(s!=null&&dictCode!=null){
			return s.getSysDictByDictCode(dictCode).getDictName();
		}
		return null;
	}
}
