package service.impl;

import dao.AdminDao;
import model.Admin;
import service.AdminService;

public class AdminServiceImpl implements AdminService {

	@Override
	public boolean checkAdmin(String name, String password) {
		// TODO ��֤����Ա���
		//1.�����ݿ��в�ѯ����Ա
		String sql = "select admin_id,admin_name,admin_pass from tbl_admin";
		Admin admin = AdminDao.query(sql);
		//2.��֤����Ա
		if(name.equals(admin.getName())&&password.equals(admin.getPassword())) {
			return true;
		}else {
			return false;
		}
	}
	
}
