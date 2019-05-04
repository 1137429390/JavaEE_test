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
		//���ܻ�������
		String name = admin.getName();
		String password = admin.getPassword();
		//�ж�������Ч��
		String msg = null;
		//���÷�����ѯ���ݿ�
		AdminService as = new AdminServiceImpl();
		boolean result = as.checkAdmin(name, password);
		if(result) {
			return "redirect:/Customer/loginByPage?currentPage=1";
		}else {
			msg = "�û������������";
			map.put("msg", msg);
			return "login";
		}
		
	}
	
	@RequestMapping("goLogin")
	public String goLogin() {
		return "login";
	}
}
