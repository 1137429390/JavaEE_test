package com.lab.service;

import java.util.List;

import com.lab.model.Customer;

public interface CustomerPageService {
	//�������ݣ���ʾÿҳ������
	public List<Customer> findByPage(int currentPage);
}
