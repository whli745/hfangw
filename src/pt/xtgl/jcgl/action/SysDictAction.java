package pt.xtgl.jcgl.action;

import java.util.List;

import net.sf.json.JSONObject;
import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.service.ISysDictService;
import util.BaseParameter;
import util.ResultPage;
import util.base.action.BaseAction;
import util.json.JsonUtil;

/**
 * 数据字典
 */
public class SysDictAction extends BaseAction {
	private static final long serialVersionUID = 5691561636630505893L;
	private ISysDictService sysDictService;
	private SysDict sysDict;
	private List<SysDict> sysDictList;
	private String dictId;// 系统字典Id
	private String dictCode;// 系统字典编号
	private String parentId;// 父节点
	private String dictName;// 系统字典名称
	private String dictMemo;// 系统字典备注
	private String dictType;// 系统字典类型
	private String dictPath;// 系统字典路径
	private String statusFlag;// 系统字典是否使用
	private String delFlag;// 系统字典是否删除 0未删除; 1已删除;

	/**
	 * 获取数据字典信息
	 * 
	 * @author wanghw
	 * @date 2013-4-11
	 * @return
	 */
	public String getSysDictInfo() {
		try {
			if (dictId != null) {
				sysDict = sysDictService.querySysDict(dictId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询数据字典信息列表
	 * 
	 * @author wanghw
	 * @date 2013-4-15
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySysDictList() {
		try {
			ResultPage rp = sysDictService.querySysDictList(dictName, dictType,
					BaseParameter.DELETE_NO, getPage(),
					BaseParameter.getPageRowsMiddle());
			setPageParam(rp);
			sysDictList = rp.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * ajax查询数据字典目录并转换成JSON对象传输到前台
	 * 
	 * @author wanghw
	 * @date 2013-4-16
	 * @return
	 */
	public String getJSONSysDict() {
		try {
			SysDict sysDict = sysDictService.querySysDict(dictId);
			// 将javaBean转换成JSON对象
			JSONObject jSysDict = JsonUtil.parse(sysDict);
			super.returnAjaxInfo(jSysDict.toString());
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * ajax添加修改数据字典目录
	 * 
	 * @author wanghw
	 * @date 2013-4-16
	 * @return
	 */
	public void editSysDictAjax() {
		try {
			if (sysDict != null) {
				sysDictService.saveOrUpdateSysDict(sysDict);
				super.returnAjaxInfo(sysDict.getDictId());
			}
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * ajax 删除数据字典目录信息
	 * 
	 * @author wanghw
	 * @date 2013-4-16
	 */
	public void delSysDictAjax() {
		try {
			sysDict = sysDictService.querySysDict(dictId);
			sysDictService.deleteSysDict(sysDict);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * 异步加载树
	 * 
	 * @author wanghw
	 * @date 2013-04-16
	 */
	public String getSysDictTreeAjax() {
		try {
			String JSONStr = sysDictService.getSysDictTreeByPid(parentId);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * 异步加载树--根据字典编码查询
	 * 
	 * @author zhujj
	 * @date 2013-05
	 */
	public String getSysDictTreeStrAjax() {
		try {
			String JSONStr = sysDictService.getSysDictTreeByCode(dictCode);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	public List<SysDict> getSysDictList() {
		return sysDictList;
	}

	public void setSysDictList(List<SysDict> sysDictList) {
		this.sysDictList = sysDictList;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictMemo() {
		return dictMemo;
	}

	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictPath() {
		return dictPath;
	}

	public void setDictPath(String dictPath) {
		this.dictPath = dictPath;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
