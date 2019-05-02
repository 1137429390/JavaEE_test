package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lab.util.JDBCUtil;

import model.GameUser;

public class GameUserDao extends BaseDao {
	public static List<GameUser> query(String sql,Object...obj){
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<GameUser> list = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i+1, obj[i]);
			}
			rs = pst.executeQuery();
			while(rs.next()) {
				GameUser user = new GameUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setSmallId(rs.getInt(3));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pst, rs);
		}
		return list;
	}
}
