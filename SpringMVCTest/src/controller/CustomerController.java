package controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Customer;
import model.CustomerPage;
import service.CustomerPageService;
import service.CustomerService;
import service.impl.CustomerPageServiceImpl;
import service.impl.CustomerServiceImpl;

@Controller
@RequestMapping("Customer")
public class CustomerController {
	
	CustomerService cs1 = new CustomerServiceImpl();
	CustomerPageService cs2 = new CustomerPageServiceImpl();	
	
	@RequestMapping("Search")
	public String search() {
		// TODO ��ѯ����
		return null;
	}
	
	@RequestMapping("goAdd")
	public String goAdd() {
		// TODO ��ת����ӻ���
		return "customer_add";
	}
	
	@RequestMapping("doAdd")
	public String doAdd(Customer customer,Map<Object,Object> map) {
		// TODO ִ�����һ�����ݹ���
		boolean result = cs1.addCustomer(customer);
		if(result) {
			return "redirect:loginByPage?currentPage=1";
		}else {
			map.put("msg", "���ʧ��");
			return "customer_add";
		}
	}
	
	@RequestMapping("goUpdate")
	public String goUpdate(int no,Map<Object,Object> map) {
		// TODO ��ת������ҳ��
		//���÷������ҵ���Ӧ����
		Customer customer = cs1.findByNo(no);
		map.put("cs", customer);
		return "customer_update";
	}
	
	@RequestMapping("doUpdata")
	public String doUpdata(Customer customer,Map<Object,Object> map) {
		// TODO ִ�и������ݹ���
		//���÷���
		boolean result = cs1.updateCustomer(customer);
		if(result) {
			return "redirect:loginByPage?currentPage=1";
		}else {
			map.put("msg", "����ʧ�ܣ�");
			return "customer_updata";
		}
	}
	
	@RequestMapping("goDelete")
	public String goDelete(int no,Map<Object,Object> map) {
		// TODO ��ת����ӻ���
		map.put("no", no);
		return "customer_delete";
	}
	
	@RequestMapping("doDelete")
	public String doDelete(int no,Map<Object,Object> map) {
		// TODO ִ��ɾ�����ݹ���
		//���÷���
		boolean result = cs1.deleteCustomer(no);
		if(result) {
			return "redirect:loginByPage?currentPage=1";
		}else {
			map.put("msg", "ɾ��ʧ�ܣ�");
			return "customer_delete";
		}
	}
	
	@RequestMapping("loginByPage")
	public String loginByPage(int currentPage,Map<Object,Object> map) {
		// TODO ���ߵ�ǰҳ����ת����ҳ
		//��ѯ���ݿ�
		List<Customer> listAll = cs1.findAll();
		//��ҳ��ѯ����
		List<Customer> list = cs2.findByPage(currentPage);
		//����ҳ��
		int totalPage = 0;
		if(listAll.size()%CustomerPage.PAGE_COUNT == 0) {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT;
		}else {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT + 1;
		}
		//���ݴ��
		CustomerPage cp = new CustomerPage();
		cp.setList(list);
		cp.setCurrentPge(currentPage);
		cp.setTotalPage(totalPage);
		map.put("cp", cp);
		return "customer_index";
	}
	
}
