package com.lab.service;

import java.util.List;

import com.lab.model.Customer;

public interface CustomerPageService {
	//根据数据，显示每页的内容
	public List<Customer> findByPage(int currentPage);
}
