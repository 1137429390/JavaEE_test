package service;

import java.util.List;

import model.Customer;

public interface CustomerService {
	//������ӷ�����������Ҫ���ϣ���Ҫʵ���ࣩ
	boolean addCustomer(Customer cs);
	//��ѯ��������
	List<Customer> findAll();
	//����IDɾ������
	boolean deleteCustomer(int no);
	
	Customer findByNo(int no);
	
	boolean updateCustomer(Customer cs);
	
	List<Customer> search(String no,String tel);
}
