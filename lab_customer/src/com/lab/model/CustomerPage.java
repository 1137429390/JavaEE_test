package com.lab.model;

import java.util.List;

public class CustomerPage {
	//����ҳ��ѯ��ķ��͵�ҳ������ݴ��
	private List<Customer> list; //ҳ����չʾ����
	
	private int totalPage; //������ҳ
	
	private int currentPge; //��ǰҳ��

	public static final int PAGE_COUNT = 5; //ÿҳ��ʾ��������
	
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
