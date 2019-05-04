package service.impl;

import java.util.List;

import dao.CustomerDao;
import model.Customer;
import model.CustomerPage;
import service.CustomerPageService;

public class CustomerPageServiceImpl implements CustomerPageService {

	@Override
	public List<Customer> findByPage(int currentPage) {
		// TODO 根据页码显示数据
		String sql = "select customer_id,customer_name,customer_score,customer_tel,customer_sex from tbl_customer limit ?,?";
		List<Customer> list = CustomerDao.query(sql, (currentPage-1)*CustomerPage.PAGE_COUNT,CustomerPage.PAGE_COUNT);
		return list;
	}

}
