package util.json;

import java.util.Date;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {
	public static JSONObject parse(Object object) {
		// 对日期类做特别处理
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONObject jsonObj = JSONObject.fromObject(object, jsonConfig);
		return jsonObj;
	}
}
