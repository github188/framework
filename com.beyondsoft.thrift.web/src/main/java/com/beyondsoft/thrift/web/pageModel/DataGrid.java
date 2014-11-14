package com.beyondsoft.thrift.web.pageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataGrid {

	private Long total = 0L;
	private List rows = new ArrayList();
	private List<Map> footer = new ArrayList<Map>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List<Map> getFooter() {
		return footer;
	}

	public void setFooter(List<Map> footer) {
		this.footer = footer;
	}

}
