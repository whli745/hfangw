package util.servlet;

import java.util.Hashtable;
import java.util.Map;

public class InfoCache
{
  private static Map<String,InfoCache> setInfoMap = new Hashtable<String,InfoCache>();
  private Map sqlMap;
  private String moduleName;

  private InfoCache(String moduleName, Map sqlMap)
  {
    this.moduleName = moduleName;
    this.sqlMap = sqlMap;
  }

  public static synchronized InfoCache getInstance(String moduleName)
  {
    InfoCache sc = null;
    if (setInfoMap.containsKey(moduleName))
    {
      sc = (InfoCache)setInfoMap.get(moduleName);
    }
    return sc;
  }

  protected static synchronized InfoCache setInstance(String moduleName, Map errorMap)
  {
    InfoCache eic = null;
    eic = new InfoCache(moduleName, errorMap);
    setInfoMap.put(moduleName, eic);
    return eic;
  }

  public String getSetInfo(String setId)
  {
    return this.sqlMap.get(setId).toString();
  }

  public String getModuleName() {
    return this.moduleName;
  }
}
