package util;

import java.util.List;

import util.base.BaseObject;

@SuppressWarnings({"unchecked"})
public class ResultPage {
	private List resultList;
	private int allRows;
	private int maxPage;
	private int currentPage;
	private int pageRows;

	private ResultPage() {
	}

	public ResultPage(List result) {
		setResultList(result);
	}

	public ResultPage(List result, int ar, int cp, int pr) {
		setResultList(result);
		setAllRows(ar);
		setMaxPage((ar - 1) / pr + 1);
		if (cp > maxPage)
			setCurrentPage(maxPage);
		else
			setCurrentPage(cp);
		setPageRows(pr);
	}

	public int getAllRows() {
		return allRows;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getPageRows() {
		return pageRows;
	}

	public List getResultList() {
		return resultList;
	}

	public void setAllRows(int i) {
		allRows = i;
	}

	public void setCurrentPage(int i) {
		currentPage = i;
	}

	public void setMaxPage(int i) {
		maxPage = i;
	}

	public void setPageRows(int i) {
		pageRows = i;
	}

	public void setResultList(List list) {
		resultList = list;
	}

	public void addResultList(BaseObject obj) {
		resultList.add(obj);
	}

	public void removeResultList(BaseObject obj) {
		resultList.remove(obj);
	}

	public void clear() {
		resultList.clear();
	}

}