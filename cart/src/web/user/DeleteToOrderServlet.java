package web.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import entity.Order;
import entity.User;

public class DeleteToOrderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收数据
	
		int rid=Integer.parseInt(request.getParameter("itemId"));

		OrderDao orderDao = new OrderDao();
		orderDao.deleteById(rid);
		
		response.sendRedirect("../web/ToCartServlet");
		

	}

}
