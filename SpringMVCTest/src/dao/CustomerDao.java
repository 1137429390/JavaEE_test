package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import util.JDBCUtil;

public class CustomerDao extends BaseDao{
	//既有增删改（继承），也有查询（自己写）
	public static List<Customer> query(String sql,Object...obj){
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Customer> list = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstm.setObject(i+1, obj[i]);
			}
			rs = pstm.executeQuery();
			while(rs.next()) {
				Customer cs = new Customer();
				cs.setNo(rs.getInt(1));
				cs.setName(rs.getString(2));
				cs.setScore(rs.getDouble(3));
				cs.setTel(rs.getString(4));
				cs.setSex(rs.getString(5));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstm, rs);
		}
		return list;
	}
}
