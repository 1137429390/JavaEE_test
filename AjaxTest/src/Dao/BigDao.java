package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lab.util.JDBCUtil;

import model.Big;

public class BigDao {
	public static List<Big> query(String sql,Object...obj){
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Big> list = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i+1, obj[i]);
			}
			rs = pst.executeQuery();
			while(rs.next()) {
				Big big = new Big();
				big.setBigId(rs.getInt(1));
				big.setBigName(rs.getString(2));
				list.add(big);
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
