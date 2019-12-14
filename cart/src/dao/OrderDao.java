package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import util.DBUtils;
import entity.Order;

public class OrderDao {
	public void insert(Order order) {
		Connection con = DBUtils.getConnection();
		String sql = "insert into tb_order(user_id,order_id,sta) "
				+ "values(?,?,?)";
		PreparedStatement sta = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, order.getUserId());
			sta.setString(2, order.getOrderId());
			sta.setString(3, order.getSta());
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(con,sta,null);
		}
	}
	
	public void updateOrderById(String orderId, int address, double payment, String status) {
		Connection con = DBUtils.getConnection();
		String sql = "update tb_order set address_id=?,payment=?,sta=?,placed=? "
				+ "where order_id=?";
		PreparedStatement sta = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setInt(1, address);
			sta.setDouble(2, payment);
			sta.setString(3, status);
			sta.setTimestamp(4, new Timestamp(new Date().getTime()));
			sta.setString(5, orderId);
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(con,sta,null);
		}
	}
	
	public Order selectById(String orderId) {
		Order order = null;
		Connection con = DBUtils.getConnection();
		String sql = "select * from tb_order "
				+ "where order_id=?";
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, orderId);
			res = sta.executeQuery();
			if(res.next()){
				order = new Order();
				order.setOrderId(res.getString("order_id"));
				order.setAddressId(res.getString("address_id"));
				order.setRid(res.getInt("rid"));
				order.setSta(res.getString("sta"));
				order.setUserId(res.getString("user_id"));
				order.setPayment(res.getDouble("payment"));
				order.setPlaced(res.getTimestamp("placed"));
				order.setReceipt(res.getTimestamp("receipt"));
				order.setDeliver(res.getTimestamp("deliver"));
				order.setHandover(res.getTimestamp("handover"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con,sta,res);
		}
		return order;
	}
	
	public int selectAllCount() {
		int totalCount = 0;
		Connection con = DBUtils.getConnection();
		String sql = "select count(rid) "
				+ "from tb_order";
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			res = sta.executeQuery();
			if(res.next()){
				totalCount = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con,sta,res);
		}
		return totalCount;
	}

	public ArrayList<Order> selectAllPaged(int start, int length) {
		ArrayList<Order> list = new ArrayList<Order>();
		Order order = null;
		Connection con = DBUtils.getConnection();
		String sql = "select * from tb_order "
				+ "order by placed desc "
				+ "limit ?,?";//每页10条，第一页0,10第二页10,10
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setInt(1, start);
			sta.setInt(2, length);
			res = sta.executeQuery();
			while(res.next()){
				order = new Order();
				order.setOrderId(res.getString("order_id"));
				order.setAddressId(res.getString("address_id"));
				order.setRid(res.getInt("rid"));
				order.setSta(res.getString("sta"));
				order.setUserId(res.getString("user_id"));
				order.setPayment(res.getDouble("payment"));
				order.setPlaced(res.getTimestamp("placed"));
				order.setReceipt(res.getTimestamp("receipt"));
				order.setDeliver(res.getTimestamp("deliver"));
				order.setHandover(res.getTimestamp("handover"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con,sta,res);
		}
		return list;
	}
	
	public void updateStaById(String orderId, String status) {
		Connection con = DBUtils.getConnection();
		String sql = "update tb_order set sta=?,handover=? "
				+ "where order_id=? and sta<>?";
		PreparedStatement sta = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, status);
			sta.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			sta.setString(3, orderId);
			sta.setString(4, status);
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(con,sta,null);
		}
	}
	
	
	public int deleteById(Integer rid) {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getConnection();
		String sql="delete from tb_cart_item where rid=?";
		PreparedStatement p=null;
		int rows=0;
		try {
			p=conn.prepareStatement(sql);
			p.setObject(1, rid);
			rows=p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(conn, p, null);
		}
		return rows;
	}

}
