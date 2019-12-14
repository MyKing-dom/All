package web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

public class LoginServlet extends HttpServlet {

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
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		
		User user = new User();
		user.setRole(0);
		user.setUname(uname);
		user.setUpwd(upwd);
		
		UserDao userDao = new UserDao();
		User u = userDao.selectByNamePasswordAndRole(user);
		String data = "no";
		if(null!=u){
			//使用session进行会话管理
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", u);
			data = "yes";
		}
		PrintWriter out = response.getWriter();
		out.write(data);
		out.close();

	}

}
