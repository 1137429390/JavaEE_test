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
		// TODO 查询数据
		return null;
	}
	
	@RequestMapping("goAdd")
	public String goAdd() {
		// TODO 跳转到添加画面
		return "customer_add";
	}
	
	@RequestMapping("doAdd")
	public String doAdd(Customer customer,Map<Object,Object> map) {
		// TODO 执行添加一条数据功能
		boolean result = cs1.addCustomer(customer);
		if(result) {
			return "redirect:loginByPage?currentPage=1";
		}else {
			map.put("msg", "添加失败");
			return "customer_add";
		}
	}
	
	@RequestMapping("goUpdate")
	public String goUpdate(int no,Map<Object,Object> map) {
		// TODO 跳转到更新页面
		//调用方法查找到对应数据
		Customer customer = cs1.findByNo(no);
		map.put("cs", customer);
		return "customer_update";
	}
	
	@RequestMapping("doUpdata")
	public String doUpdata(Customer customer,Map<Object,Object> map) {
		// TODO 执行更新数据功能
		//调用方法
		boolean result = cs1.updateCustomer(customer);
		if(result) {
			return "redirect:loginByPage?currentPage=1";
		}else {
			map.put("msg", "更新失败！");
			return "customer_updata";
		}
	}
	
	@RequestMapping("goDelete")
	public String goDelete(int no,Map<Object,Object> map) {
		// TODO 跳转到添加画面
		map.put("no", no);
		return "customer_delete";
	}
	
	@RequestMapping("doDelete")
	public String doDelete(int no,Map<Object,Object> map) {
		// TODO 执行删除数据功能
		//调用方法
		boolean result = cs1.deleteCustomer(no);
		if(result) {
			return "redirect:loginByPage?currentPage=1";
		}else {
			map.put("msg", "删除失败！");
			return "customer_delete";
		}
	}
	
	@RequestMapping("loginByPage")
	public String loginByPage(int currentPage,Map<Object,Object> map) {
		// TODO 更具当前页面跳转到首页
		//查询数据库
		List<Customer> listAll = cs1.findAll();
		//分页查询方法
		List<Customer> list = cs2.findByPage(currentPage);
		//计算页数
		int totalPage = 0;
		if(listAll.size()%CustomerPage.PAGE_COUNT == 0) {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT;
		}else {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT + 1;
		}
		//数据打包
		CustomerPage cp = new CustomerPage();
		cp.setList(list);
		cp.setCurrentPge(currentPage);
		cp.setTotalPage(totalPage);
		map.put("cp", cp);
		return "customer_index";
	}
	
}
