package com.lab.service;

import java.util.List;

import com.lab.model.Customer;

public interface CustomerService {
	//定义添加方法（参数需要整合，需要实体类）
	boolean addCustomer(Customer cs);
	//查询所有数据
	List<Customer> findAll();
	//根据ID删除数据
	boolean deleteCustomer(int no);
	
	Customer findByNo(int no);
	
	boolean updateCustomer(Customer cs);
	
	List<Customer> search(String no,String tel);
}
