package service;

import java.util.List;

import model.Big;
import model.GameUser;
import model.Small;

public interface ConnectServicce {
	//查询所有大区
	List<Big> findBig();
	//查询所有小区
	List<Small> findSmall(int bigId);
	//查询所有用户
	List<GameUser> findGameUser(int smallId);
}
