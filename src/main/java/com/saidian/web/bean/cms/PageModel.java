package com.saidian.web.bean.cms;

import java.util.List;

public class PageModel {

	private int page = 0;
	
	private int rows = 15;
	
	private int totalPage = 1;
	
	private List<? extends Object> list ;
	
	public List<? extends Object> getList() {
		return list;
	}

	public void setList(List<? extends Object> list) {
		this.list = list;
	}

	public int getPage() {
		if(page > 0){
			page = (page - 1) * rows;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		if(totalPage > 0){
			this.totalPage = (totalPage + rows - 1)/rows;
		}else
			this.totalPage = totalPage;
	}
	
}
