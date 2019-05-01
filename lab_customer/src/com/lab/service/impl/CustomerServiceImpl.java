package com.lab.service.impl;

import java.util.List;

import com.lab.dao.CustomerDao;
import com.lab.model.Customer;
import com.lab.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	String sql;
	@Override
	public boolean addCustomer(Customer cs) {
		// TODO ���һ������
		//����ӹ���(��װ��ͬ�Ĵ���)
		sql = "insert into tbl_customer values(?,?,?,?,?)";
		boolean result = CustomerDao.update(sql, cs.getNo(),cs.getName(),cs.getScore(),cs.getTel(),cs.getSex());
		return result;
	}
	
	@Override
	public List<Customer> findAll(){
		// TODO ��Ѱ��������
		sql = "select customer_id,customer_name,customer_score,customer_tel,customer_sex from tbl_customer";
		List<Customer> list = CustomerDao.query(sql);
		return list;
	}

	@Override
	public boolean deleteCustomer(int no) {
		// TODO ����IDɾ������
		sql = "delete from tbl_customer where customer_id = ?";
		return CustomerDao.update(sql, no);
	}

	@Override
	public Customer findByNo(int no) {
		// TODO ���ݱ�Ż�ȡ����
		sql = "select customer_id,customer_name,customer_score,customer_tel,customer_sex from tbl_customer where customer_id = ?";
		List<Customer> list = CustomerDao.query(sql, no);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public boolean updateCustomer(Customer cs) {
		// TODO ����ģ�����ݸ�������
		sql = "update tbl_customer set customer_name = ?, customer_score = ?, customer_tel = ?, customer_sex = ? where customer_id = ?";
		return CustomerDao.update(sql, cs.getName(),cs.getScore(),cs.getTel(),cs.getSex(),cs.getNo());
	}

	@Override
	public List<Customer> search(String no, String tel) {
		// TODO �����ֻ��źͱ��ģ����ѯ
		List<Customer> list = null;
		if("".equals(no)&&"".equals(tel)) {
			list = findAll();
		}else if(!"".equals(no)&&"".equals(tel)) {
			//��ֹsqlע��concat()�����ַ���
			String sql = "select customer_id, customer_name, customer_score, customer_tel, customer_sex from tbl_customer where customer_id like concat('%', ?, '%')";
			list = CustomerDao.query(sql, no);
		}else if("".equals(no)&&!"".equals(tel)) {
			String sql = "select customer_id, customer_name, customer_score, customer_tel, customer_sex from tbl_customer where customer_tel like ?";
			list = CustomerDao.query(sql, "%" + tel + "%");
		}else if(!"".equals(no)&&!"".equals(tel)) {
			String sql = "select customer_id, customer_name, customer_score, customer_tel, customer_sex from tbl_customer where customer_id like ? and customer_tel like ?";
			list = CustomerDao.query(sql, "%" + no + "%", "%" + tel + "%");
		}
		return list;
	}
}
