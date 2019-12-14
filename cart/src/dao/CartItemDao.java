package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entity.CartItem;

import util.DBUtils;

public class CartItemDao {
	public int insert(CartItem cartItem){
		Connection conn=DBUtils.getConnection();
		String sql="insert into tb_cart_item (user_id,product,count) values(?,?,?)";
        PreparedStatement p=null;
        int rows=0;
		try {
			p=conn.prepareStatement(sql);
			p.setObject(1, cartItem.getUserId());
			p.setObject(2, cartItem.getProduct());
			p.setObject(3, cartItem.getCount());
			
			rows=p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally{
			DBUtils.closeAll(conn, p, null);
		}
		return rows;
	}

	public CartItem selectByUserIdAndProduct(CartItem cartItem) {
		// TODO Auto-generated method stub
		CartItem c=null;
		Connection conn=DBUtils.getConnection();
		String sql="select rid,user_id,product,count from tb_cart_item where user_id=? and product=?";
        PreparedStatement p=null;
        ResultSet r=null;
        try {
			p=conn.prepareStatement(sql);
			p.setObject(1, cartItem.getUserId());
			p.setObject(2, cartItem.getProduct());
		
			r=p.executeQuery();
			if(r.next()){
				c=new CartItem();
				
				c.setRid(r.getInt("rid"));
				c.setUserId(r.getString("user_id"));
				c.setProduct(r.getString("product"));
				c.setCount(r.getInt("count"));	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally{
			DBUtils.closeAll(conn, p, null);
		}
		return c;
	}

	public int update(CartItem cartItem) {
		Connection conn=DBUtils.getConnection();
		String sql="update tb_cart_item set count=? where product=? and user_id=?";
        PreparedStatement p=null;
        int rows=0;
        try {
			p=conn.prepareStatement(sql);
			p.setObject(3, cartItem.getUserId());
			p.setObject(2, cartItem.getProduct());
			p.setObject(1, cartItem.getCount());
	
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
	
	
	public void updateNumById(int itemId , int num) {
		Connection conn=DBUtils.getConnection();
		String sql="update tb_cart_item set count=? where rid=?";
        PreparedStatement p=null;
        try {
			p=conn.prepareStatement(sql);
			p.setObject(1,num);
			p.setObject(2, itemId);
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally{
			DBUtils.closeAll(conn, p, null);
		}
		
	}

	public ArrayList<HashMap<String, Object>> selectCartItemAssociatedBook(
			String phone) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder(
				"select c.product,c.rid,c.count,b.price,b.title,b.author from tb_cart_item c inner join tb_book b on c.product=b.isbn where c.user_id=?");
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map=null;
		Connection conn=DBUtils.getConnection();
		PreparedStatement p=null;
		ResultSet res=null;
		try {
			p=conn.prepareStatement(sql.toString());
			p.setObject(1, phone);
			res=p.executeQuery();

			while(res.next()){
				map=new HashMap<String, Object>();
				map.put("rid", res.getInt("rid"));
				map.put("product", res.getString("product"));
				map.put("count", res.getInt("count"));
				map.put("price", res.getDouble("price"));
				map.put("title", res.getString("title"));
				map.put("author", res.getString("author"));
				list.add(map);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(conn, p, null);
		}
		return list;
	}
	
	
}
