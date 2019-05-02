package service;

import java.util.List;

import model.User;

public interface loginService {
	List<User>  findByName(String name);
}
