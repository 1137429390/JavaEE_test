package service.impl;

import java.util.List;

import Dao.UserDao;
import model.User;
import service.loginService;

public class loginServiceImpl implements loginService {

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select user_id,user_name,user_password from test_user where user_name = ?";
		List<User> list = UserDao.query(sql, name);
		return list;
	}

}
