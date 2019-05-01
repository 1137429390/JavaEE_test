package com.lab.model;

import java.util.List;

public class CustomerPage {
	//将分页查询后的发送到页面的数据打包
	private List<Customer> list; //页面所展示数据
	
	private int totalPage; //共多少页
	
	private int currentPge; //当前页码

	public static final int PAGE_COUNT = 5; //每页显示数据条数
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPge() {
		return currentPge;
	}

	public void setCurrentPge(int currentPge) {
		this.currentPge = currentPge;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}
}
