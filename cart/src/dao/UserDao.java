package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtils;
import entity.User;

public class UserDao {
	
	public int insert(User user){
		Connection con = DBUtils.getConnection();
		String sql = "insert into tb_user(phone,uname,upwd,email,role) "
				+ "values(?,?,?,?,?)";
		PreparedStatement sta = null;
		int rows = 0;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, user.getPhone());
			sta.setString(2, user.getUname());
			sta.setString(3, user.getUpwd());
			sta.setString(4, user.getEmail());
			sta.setInt(5, user.getRole());
			rows = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(con, sta, null);
		}
		return rows;
	}
	
	public User selectByNamePasswordAndRole(User user) {
		User u = null;
		Connection con = DBUtils.getConnection();
		String sql = "select email,phone,role,uname,upwd from tb_user "
				+ "where uname=? and upwd=? and role=?";
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, user.getUname());
			sta.setString(2, user.getUpwd());
			sta.setInt(3, user.getRole());
			res = sta.executeQuery();
			if(res.next()){
				u = new User();
				u.setEmail(res.getString("email"));
				u.setPhone(res.getString("phone"));
				u.setRole(res.getInt("role"));
				u.setUname(res.getString("uname"));
				u.setUpwd(res.getString("upwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con, sta, res);
		}
		return u;
	}

	public User selectByEmailAndRole(User user){
		User u = null;
		Connection con = DBUtils.getConnection();
		String sql = "select email,phone,role,uname,upwd from tb_user "
				+ "where email=? and role=?";
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, user.getEmail());
			sta.setInt(2, user.getRole());
			res = sta.executeQuery();
			if(res.next()){
				u = new User();
				u.setEmail(res.getString("email"));
				u.setPhone(res.getString("phone"));
				u.setRole(res.getInt("role"));
				u.setUname(res.getString("uname"));
				u.setUpwd(res.getString("upwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con, sta, res);
		}
		return u;
	}
	
	public User selectByPhoneAndRole(User user){
		User u = null;
		Connection con = DBUtils.getConnection();
		String sql = "select email,phone,role,uname,upwd from tb_user "
				+ "where phone=? and role=?";
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, user.getPhone());
			sta.setInt(2, user.getRole());
			res = sta.executeQuery();
			while(res.next()){
				u = new User();
				u.setEmail(res.getString("email"));
				u.setPhone(res.getString("phone"));
				u.setRole(res.getInt("role"));
				u.setUname(res.getString("uname"));
				u.setUpwd(res.getString("upwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con, sta, res);
		}
		return u;
	}
	
	public User selectByUnameAndRole(User user){
		User u = null;
		Connection con = DBUtils.getConnection();
		String sql = "select email,phone,role,uname,upwd from tb_user "
				+ "where uname=? and role=?";
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, user.getUname());
			sta.setInt(2, user.getRole());
			res = sta.executeQuery();
			if(res.next()){
				u = new User();
				u.setEmail(res.getString("email"));
				u.setPhone(res.getString("phone"));
				u.setRole(res.getInt("role"));
				u.setUname(res.getString("uname"));
				u.setUpwd(res.getString("upwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con, sta, res);
		}
		return u;
	}
}
