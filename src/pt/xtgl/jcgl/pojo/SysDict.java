package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

@SuppressWarnings("serial")
public class SysDict extends BaseObject{
	
	private String dictId;//系统字典Id
	private String dictCode;//系统字典编号
	private String dictName;//系统字典名称
	private String parentId;//父节点
	private String dictMemo;//系统字典备注
	private String dictPath;//系统字典路径
	private String statusFlag;//系统字典是否使用
	private String delFlag;//系统字典是否删除 0未删除; 1已删除;
	
	public SysDict() {
		super();
	}
	
	public SysDict(String dictType, String dictName, String dictId) {
		super();
		this.dictName = dictName;
		this.dictId = dictId;
	}
	
	public SysDict(SysDict dict) {
		this.dictId = dict.getDictId();
		this.dictCode = dict.getDictCode();
		this.dictName = dict.getDictName();
		this.parentId = dict.getParentId();
		this.dictMemo = dict.getDictMemo();
		this.dictPath = dict.getDictPath();
		this.statusFlag = dict.getStatusFlag();
		this.delFlag = dict.getDelFlag();
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
	public String getDictPath() {
		return dictPath;
	}
	public void setDictPath(String dictPath) {
		this.dictPath = dictPath;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SysDict) {
			SysDict dict = (SysDict) obj;
			return this.dictId.equals(dict.dictId);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.dictName;
	}
}
