package service;

import java.util.List;

import model.Customer;

public interface CustomerPageService {
	//�������ݣ���ʾÿҳ������
	public List<Customer> findByPage(int currentPage);
}
