package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtils;
import entity.*;

public class CollectDao {
		public Collect selectByUserIdAndProduct(String userId,String product){
			
			Collect c = null;
			Connection con = DBUtils.getConnection();
			String sql = "select rid,user_id,product from tb_collect "
					+ "where user_id=? and product=?";
			PreparedStatement sta = null;
			ResultSet res = null;
			try {
				sta = con.prepareStatement(sql);
				sta.setString(1, userId );
				sta.setString(2, product);
				res = sta.executeQuery();
				if(res.next()){
					c = new Collect();
					c.setRid(res.getInt("rid"));
					c.setUserId(res.getString("user_id"));
					c.setProduct(res.getString("product"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(con, sta, res);
			}
			return c;
		}

		public int deleteById(Integer rid) {
			// TODO Auto-generated method stub
			Connection conn=DBUtils.getConnection();
			String sql="delete from tb_collect where rid=?";
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

		public int insert(String userId, String product) {
			// TODO Auto-generated method stub
			Connection conn=DBUtils.getConnection();
			String sql="insert into tb_collect(user_id,product) values(?,?)";
			PreparedStatement p=null;
			int rows=0;
			try {
				p=conn.prepareStatement(sql);
				p.setObject(1, userId);
				p.setObject(2, product);
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
