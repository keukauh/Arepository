package cn.smartmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.smartmvc.entity.User;
import cn.smartmvc.util.DBUtils;

/**
 * DAO类: 用于封装数据访问逻辑。
 */
public class UserDAO {

	/**
	 * 依据用户名查询该用户的所有信息， 如果找不到，返回null。
	 */
	public User find(String username) throws SQLException {
		User user = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rst = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("SELECT * FROM t_user " + "WHERE username=?");
			stat.setString(1, username);
			rst = stat.executeQuery();
			if (rst.next()) {
				user = new User();
				user.setId(rst.getInt("id"));
				user.setUname(username);
				user.setPwd(rst.getString("password"));
				user.setEmail(rst.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(null, stat, conn);
		}
		return user;
	}

	/**
	 * 删除指定id的用户
	 */
	public void delete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("DELETE FROM t_user WHERE id=?");
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(null, stat, conn);
		}

	}

	/**
	 * 将用户信息添加到数据库中(t_user表)
	 */
	public void save(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("INSERT INTO t_user " + "VALUES(null,?,?,?)");
			stat.setString(1, user.getUname());
			stat.setString(2, user.getPwd());
			stat.setString(3, user.getEmail());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(null, stat, conn);
		}
	}

	/**
	 * 将用户表(t_user)中所有用户信息查询出来。
	 */
	public List<User> findAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rst = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("SELECT * FROM t_user");
			rst = stat.executeQuery();
			while (rst.next()) {
				int id = rst.getInt("id");
				String uname = rst.getString("username");
				String pwd = rst.getString("password");
				String email = rst.getString("email");

				User user = new User();
				user.setId(id);
				user.setUname(uname);
				user.setPwd(pwd);
				user.setEmail(email);

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(rst, stat, conn);
		}
		return users;
	}
}
