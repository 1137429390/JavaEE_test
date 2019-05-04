package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Admin;
import service.AdminService;
import service.impl.AdminServiceImpl;

@Controller
@RequestMapping("/Admin")
public class AdminController {

	@RequestMapping("login")
	public String login(Admin admin,Map<Object,Object> map) {
		//接受画面数据
		String name = admin.getName();
		String password = admin.getPassword();
		//判断数据有效性
		String msg = null;
		//调用方法查询数据库
		AdminService as = new AdminServiceImpl();
		boolean result = as.checkAdmin(name, password);
		if(result) {
			return "redirect:/Customer/loginByPage?currentPage=1";
		}else {
			msg = "用户名或密码错误！";
			map.put("msg", msg);
			return "login";
		}
		
	}
	
	@RequestMapping("goLogin")
	public String goLogin() {
		return "login";
	}
}
