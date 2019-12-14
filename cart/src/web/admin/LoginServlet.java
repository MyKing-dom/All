package web.admin;

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
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收数据
		response.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		
		User user = new User();
		user.setRole(1);
		user.setUname(uname);
		user.setUpwd(upwd);
		
		UserDao userDao = new UserDao();
		User u = userDao.selectByNamePasswordAndRole(user);
		String path = "../page/login.html";
		if(null!=u){
			//使用session进行会话管理
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", u);
			path = "../page/index.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}


}
