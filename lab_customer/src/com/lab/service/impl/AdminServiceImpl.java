package com.lab.service.impl;

import com.lab.dao.AdminDao;
import com.lab.model.Admin;
import com.lab.service.AdminService;

public class AdminServiceImpl implements AdminService {

	@Override
	public boolean checkAdmin(String name, String password) {
		// TODO 验证管理员身份
		//1.从数据库中查询管理员
		String sql = "select admin_id,admin_name,admin_pass from tbl_admin";
		Admin admin = AdminDao.query(sql);
		//2.验证管理员
		if(name.equals(admin.getName())&&password.equals(admin.getPassword())) {
			return true;
		}else {
			return false;
		}
	}
	
}
