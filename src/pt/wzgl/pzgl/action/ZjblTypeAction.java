package pt.wzgl.pzgl.action;

import java.util.List;

import net.sf.json.JSONObject;
import pt.wzgl.pzgl.pojo.ZjblType;
import pt.wzgl.pzgl.service.IZjblTypeService;
import util.BaseParameter;
import util.ResultPage;
import util.base.action.BaseAction;
import util.json.JsonUtil;

/**
 * 证件办理分类
 */
public class ZjblTypeAction extends BaseAction {
	private static final long serialVersionUID = 2942379659916308119L;
	private IZjblTypeService zjblTypeService;
	private ZjblType zjblType;
	private List<ZjblType> zjblTypeList;
	private String typeCode;
	private String typeId;
	private String parentId;// 父节点
	private String typeName;// 分类名称
	private String typeMemo;// 分类备注
	private String typeType;// 分类类型
	private String typePath;// 分类路径
	private String usingFlag;// 分类是否使用
	private String delFlag;// 分类是否删除 0未删除; 1已删除;
	/**
	 * 获取证件办理分类信息
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	public String getZjblTypeInfo() {
		try {
			if (typeId != null) {
				zjblType = zjblTypeService.queryZjblType(typeId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询证件办理分类信息列表
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryZjblTypeList() {
		try {
			ResultPage rp = zjblTypeService.queryZjblTypeList(zjblType, getPage(),
					BaseParameter.getPageRowsMiddle());
			setPageParam(rp);
			zjblTypeList = rp.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * ajax查询证件办理分类目录并转换成JSON对象传输到前台
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	public String getJSONZjblType() {
		try {
			zjblType = zjblTypeService.queryZjblType(typeId);
			// 将javaBean转换成JSON对象
			JSONObject jZjblType = JsonUtil.parse(zjblType);
			super.returnAjaxInfo(jZjblType.toString());
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * ajax添加修改证件办理分类目录
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	public void editZjblTypeAjax() {
		try {
			if (zjblType != null) {
				zjblTypeService.saveOrUpdateZjblType(zjblType);
				super.returnAjaxInfo(zjblType.getTypeId());
			}
		} catch (Exception e) {
			error(null, e);
		}
	}
	
	/**
	 * 分类编码唯一性验证
	 * @date 2016-02-14
	 * @return
	 */
	public void chkZjblTypeCodeUniqueAjax() {
		try {
			boolean chkRes= zjblTypeService.chkTypeCodeUnique(typeId,typeCode);
			super.returnAjaxInfo(chkRes+"");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * ajax 删除证件办理分类目录信息
	 * @author yaosw
	 * @date 2016-02-14
	 */
	public void delZjblTypeAjax() {
		try {
			zjblType = zjblTypeService.queryZjblType(typeId);
			zjblTypeService.deleteZjblType(zjblType);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * 异步加载树
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 */
	public String getZjblTypeTreeAjax() {
		try {
			String JSONStr = zjblTypeService.getZjblTypeTreeByPid(parentId);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * 异步加载树--根据分类编码查询
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 */
	public String getZjblTypeTreeStrAjax() {
		try {
			String JSONStr = zjblTypeService.getZjblTypeTreeByParentCode(typeCode);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}
	public IZjblTypeService getZjblTypeService() {
		return zjblTypeService;
	}
	public void setZjblTypeService(IZjblTypeService zjblTypeService) {
		this.zjblTypeService = zjblTypeService;
	}
	public ZjblType getZjblType() {
		return zjblType;
	}
	public void setZjblType(ZjblType zjblType) {
		this.zjblType = zjblType;
	}
	public List<ZjblType> getZjblTypeList() {
		return zjblTypeList;
	}
	public void setZjblTypeList(List<ZjblType> zjblTypeList) {
		this.zjblTypeList = zjblTypeList;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeMemo() {
		return typeMemo;
	}
	public void setTypeMemo(String typeMemo) {
		this.typeMemo = typeMemo;
	}
	public String getTypeType() {
		return typeType;
	}
	public void setTypeType(String typeType) {
		this.typeType = typeType;
	}
	public String getTypePath() {
		return typePath;
	}
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}
	public String getUsingFlag() {
		return usingFlag;
	}
	public void setUsingFlag(String usingFlag) {
		this.usingFlag = usingFlag;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
