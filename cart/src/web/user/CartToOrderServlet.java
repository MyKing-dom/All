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

public class CartToOrderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收数据
		String[] itemIds = request.getParameterValues("itemIds");
		
		
		
		// 生成订单(tb_order, tb_order_item)
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		Date now=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmm");
		String nowstr=format.format(now);
		String orderId=user.getPhone().substring(8)+nowstr;
		
		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(user.getPhone());
		order.setSta("待处理");

		OrderDao orderDao = new OrderDao();

		orderDao.insert(order);
		request.getRequestDispatcher("../page/order-confirm.jsp").forward(request, response);
		

	}

}
