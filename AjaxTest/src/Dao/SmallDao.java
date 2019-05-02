package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lab.util.JDBCUtil;

import model.Small;

public class SmallDao {
	public static List<Small> query(String sql,Object...obj){
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Small> list = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i+1, obj[i]);
			}
			rs = pst.executeQuery();
			while(rs.next()) {
				Small small = new Small();
				small.setSmallId(rs.getInt(1));
				small.setSmallName(rs.getString(2));
				small.setBigId(rs.getInt(3));
				list.add(small);
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
