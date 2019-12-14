package web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CollectDao;
import dao.UserDao;
import entity.Collect;
import entity.User;

public class CollectServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收数据
		String userId = request.getParameter("userId");
		String product = request.getParameter("product");
		CollectDao collectDao=new CollectDao();
		//判断收藏还是取消收藏
		Collect collect=collectDao.selectByUserIdAndProduct(userId, product);
		int rows=0;
		if(collect!=null){
			//取消收藏
			rows=collectDao.deleteById(collect.getRid());
		}else{
			rows=collectDao.insert(userId,product);
		}
		
		String data = "no";
		if(rows>0){
			//使用session进行会话管理
//			HttpSession session = request.getSession();
//			session.setAttribute("currentUser",);
			data = "yes";
		}
		PrintWriter out = response.getWriter();
		out.write(data);
		out.close();

	}

}
