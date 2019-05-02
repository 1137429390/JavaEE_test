package service.impl;

import java.util.List;

import Dao.BigDao;
import Dao.GameUserDao;
import Dao.SmallDao;
import model.Big;
import model.GameUser;
import model.Small;
import service.ConnectServicce;

public class ConnectServiceImpl implements ConnectServicce{

	@Override
	public List<Big> findBig() {
		// TODO Auto-generated method stub
		String sql = "select big_id,big_name from big";
		return BigDao.query(sql);
	}

	@Override
	public List<Small> findSmall(int bigId) {
		// TODO Auto-generated method stub
		String sql = "select small_id,small_name,big_id from small where big_id = ?";
		return SmallDao.query(sql, bigId);
	}

	@Override
	public List<GameUser> findGameUser(int smallId) {
		// TODO Auto-generated method stub
		String sql = "select user_id,user_name,small_id from user where small_id = ?";
		return GameUserDao.query(sql, smallId);
	}

}
