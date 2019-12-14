package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBUtils {
	//根据配置创建连接池对象
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	//从连接池中获取可用连接
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	private static void closeResultSet(ResultSet res){
		if(null!=res){
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void closeStatement(Statement sta){
		if(null!=sta){
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void closeConnection(Connection con){
		if(null!=con){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 依次释放res,sta,con资源
	 * @param con
	 * @param sta
	 * @param res
	 */
	public static void closeAll(Connection con,Statement sta,ResultSet res){
		closeResultSet(res);
		closeStatement(sta);
		closeConnection(con);
	}
}
