package br.com.soaresdeandrade.advocacia.controller;

import java.util.ArrayList;
import java.util.List;

public abstract class DataTableVO<T> {
	private String draw = "1";
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<T> data = new ArrayList<T>();

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
