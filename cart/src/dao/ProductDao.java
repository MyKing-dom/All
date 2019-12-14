package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtils;
import entity.Product;

public class ProductDao {

	public int selectAllProductCount() {
		int totalCount = 0;
		Connection con = DBUtils.getConnection();
		String sql = "select count(rid) from tb_order_item";
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
	

	public ArrayList<Product> selectAllProductPaged(int start, int length) {
		ArrayList<Product> list = new ArrayList<Product>();
		Product product = null;
		Connection con = DBUtils.getConnection();
		String sql = "select * from tb_order_item "
				+ "order by product desc "
				+ "limit ?,?";//每页10条，第一页0,10第二页10,10
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = con.prepareStatement(sql);
			sta.setInt(1, start);
			sta.setInt(2, length);
			res = sta.executeQuery();
			while(res.next()){
				product = new Product();
				product.setRid(res.getInt("rid"));
				product.setProduct(res.getString("product"));
				product.setPrice(res.getDouble("price"));
				product.setCount(res.getInt("count"));
				product.setOrder_id(res.getString("order_id"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(con,sta,res);
		}
		return list;
	}

}
