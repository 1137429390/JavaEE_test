package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;
import util.JDBCUtil;

public class AdminDao{
	//只有查询（自己写）
		public static Admin query(String sql){
			Connection conn = JDBCUtil.getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			Admin admin = new Admin();
			try {
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()) {
					admin.setId(rs.getInt(1));
					admin.setName(rs.getString(2));
					admin.setPassword(rs.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn, pstm, rs);
			}
			return admin;
		}
}
