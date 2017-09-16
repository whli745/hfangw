package pt.wzgl.nrgl.dao.impl;

import pt.wzgl.nrgl.dao.IFileAttaDao;
import pt.wzgl.nrgl.pojo.FileAtta;
import util.base.dao.BaseDaoImpl;

public class FileAttaDaoImpl extends BaseDaoImpl<FileAtta> implements IFileAttaDao{

	@Override
	public void saveFileAtta(FileAtta fileAttas) throws Exception {
		super.save(fileAttas);
	}

	@Override
	public FileAtta getFileAtta(String attaId) throws Exception {
		return super.onLoad(attaId);
	}
	
	@Override
	public void deleteFileAtta(String attaId) throws Exception {
		super.delete(super.onLoad(attaId));
	}
	
	@Override
	public void deleteFileAtta(FileAtta fileAtta) throws Exception {
		super.delete(fileAtta);
	}
}
