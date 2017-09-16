package pt.wzgl.pzgl.action;

import java.util.List;

import net.sf.json.JSONObject;
import pt.wzgl.pzgl.pojo.GgfwType;
import pt.wzgl.pzgl.service.IGgfwTypeService;
import util.BaseParameter;
import util.ResultPage;
import util.base.action.BaseAction;
import util.json.JsonUtil;

/**
 * 公共服务分类
 */
public class GgfwTypeAction extends BaseAction {
	private static final long serialVersionUID = 2942379659916308119L;
	private IGgfwTypeService ggfwTypeService;
	private GgfwType ggfwType;
	private List<GgfwType> ggfwTypeList;
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
	 * 获取公共服务分类信息
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	public String getGgfwTypeInfo() {
		try {
			if (typeId != null) {
				ggfwType = ggfwTypeService.queryGgfwType(typeId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询公共服务分类信息列表
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryGgfwTypeList() {
		try {
			ResultPage rp = ggfwTypeService.queryGgfwTypeList(ggfwType, getPage(),
					BaseParameter.getPageRowsMiddle());
			setPageParam(rp);
			ggfwTypeList = rp.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * ajax查询公共服务分类目录并转换成JSON对象传输到前台
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	public String getJSONGgfwType() {
		try {
			ggfwType = ggfwTypeService.queryGgfwType(typeId);
			// 将javaBean转换成JSON对象
			JSONObject jGgfwType = JsonUtil.parse(ggfwType);
			super.returnAjaxInfo(jGgfwType.toString());
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * ajax添加修改公共服务分类目录
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 * @return
	 */
	public void editGgfwTypeAjax() {
		try {
			if (ggfwType != null) {
				ggfwTypeService.saveOrUpdateGgfwType(ggfwType);
				super.returnAjaxInfo(ggfwType.getTypeId());
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
	public void chkGgfwTypeCodeUniqueAjax() {
		try {
			boolean chkRes= ggfwTypeService.chkTypeCodeUnique(typeId,typeCode);
			super.returnAjaxInfo(chkRes+"");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * ajax 删除公共服务分类目录信息
	 * 
	 * @author yaosw
	 * @date 2016-02-14
	 */
	public void delGgfwTypeAjax() {
		try {
			ggfwType = ggfwTypeService.queryGgfwType(typeId);
			ggfwTypeService.deleteGgfwType(ggfwType);
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
	public String getGgfwTypeTreeAjax() {
		try {
			String JSONStr = ggfwTypeService.getGgfwTypeTreeByPid(parentId);
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
	public String getGgfwTypeTreeStrAjax() {
		try {
			String JSONStr = ggfwTypeService.getGgfwTypeTreeByParentCode(typeCode);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}
	public IGgfwTypeService getGgfwTypeService() {
		return ggfwTypeService;
	}
	public void setGgfwTypeService(IGgfwTypeService ggfwTypeService) {
		this.ggfwTypeService = ggfwTypeService;
	}
	public GgfwType getGgfwType() {
		return ggfwType;
	}
	public void setGgfwType(GgfwType ggfwType) {
		this.ggfwType = ggfwType;
	}
	public List<GgfwType> getGgfwTypeList() {
		return ggfwTypeList;
	}
	public void setGgfwTypeList(List<GgfwType> ggfwTypeList) {
		this.ggfwTypeList = ggfwTypeList;
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
