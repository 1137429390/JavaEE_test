package service;

import java.util.List;

import model.Big;
import model.GameUser;
import model.Small;

public interface ConnectServicce {
	//��ѯ���д���
	List<Big> findBig();
	//��ѯ����С��
	List<Small> findSmall(int bigId);
	//��ѯ�����û�
	List<GameUser> findGameUser(int smallId);
}
