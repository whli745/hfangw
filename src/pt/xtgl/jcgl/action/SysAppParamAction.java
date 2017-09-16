package pt.xtgl.jcgl.action;

import java.util.List;

import net.sf.json.JSONObject;
import pt.xtgl.jcgl.pojo.SysAppParam;
import pt.xtgl.jcgl.service.ISysAppParamService;
import util.BaseParameter;
import util.ResultPage;
import util.base.action.BaseAction;
import util.json.JsonUtil;

/**
 * 系统参数
 */
public class SysAppParamAction extends BaseAction {
	private static final long serialVersionUID = 2942379659916308119L;
	private ISysAppParamService sysAppParamService;
	private SysAppParam sysAppParam;
	private List<SysAppParam> sysAppParamList;
	private String paramCode;
	private String paramId;
	private String parentId;// 父节点
	/**
	 * 获取系统参数信息
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @return
	 */
	public String getSysAppParamInfo() {
		try {
			if (paramId != null) {
				sysAppParam = sysAppParamService.querySysAppParam(paramId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询系统参数信息列表
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySysAppParamList() {
		try {
			ResultPage rp = sysAppParamService.querySysAppParamList(sysAppParam, getPage(),
					BaseParameter.getPageRowsMiddle());
			setPageParam(rp);
			sysAppParamList = rp.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * ajax查询系统参数目录并转换成JSON对象传输到前台
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @return
	 */
	public String getJSONSysAppParam() {
		try {
			sysAppParam = sysAppParamService.querySysAppParam(paramId);
			// 将javaBean转换成JSON对象
			JSONObject jSysAppParam = JsonUtil.parse(sysAppParam);
			super.returnAjaxInfo(jSysAppParam.toString());
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * ajax添加修改系统参数目录
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 * @return
	 */
	public void editSysAppParamAjax() {
		try {
			if (sysAppParam != null) {
				sysAppParamService.saveOrUpdateSysAppParam(sysAppParam);
				super.returnAjaxInfo(sysAppParam.getParamId());
			}
		} catch (Exception e) {
			error(null, e);
		}
	}
	
	/**
	 * 参数编码唯一性验证
	 * @date 2016-02-19
	 * @return
	 */
	public void chkSysAppParamCodeUniqueAjax() {
		try {
			boolean chkRes= sysAppParamService.chkParamCodeUnique(paramId,paramCode);
			super.returnAjaxInfo(chkRes+"");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * ajax 删除系统参数目录信息
	 * @author yaosw
	 * @date 2016-02-19
	 */
	public void delSysAppParamAjax() {
		try {
			sysAppParam = sysAppParamService.querySysAppParam(paramId);
			sysAppParamService.deleteSysAppParam(sysAppParam);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * 异步加载树
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 */
	public String getSysAppParamTreeAjax() {
		try {
			String JSONStr = sysAppParamService.getSysAppParamTreeByPid(parentId);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * 异步加载树--根据参数编码查询
	 * 
	 * @author yaosw
	 * @date 2016-02-19
	 */
	public String getSysAppParamTreeStrAjax() {
		try {
			String JSONStr = sysAppParamService.getSysAppParamTreeByParentCode(paramCode);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}
	public ISysAppParamService getSysAppParamService() {
		return sysAppParamService;
	}
	public void setSysAppParamService(ISysAppParamService sysAppParamService) {
		this.sysAppParamService = sysAppParamService;
	}
	public SysAppParam getSysAppParam() {
		return sysAppParam;
	}
	public void setSysAppParam(SysAppParam sysAppParam) {
		this.sysAppParam = sysAppParam;
	}
	public List<SysAppParam> getSysAppParamList() {
		return sysAppParamList;
	}
	public void setSysAppParamList(List<SysAppParam> sysAppParamList) {
		this.sysAppParamList = sysAppParamList;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
