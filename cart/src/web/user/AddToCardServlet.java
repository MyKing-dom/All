package web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartItemDao;
import entity.CartItem;
import entity.Collect;
import entity.User;

public class AddToCardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收数据
		// 存什么数据 商品 数量 用户
		// 存在哪?session 数据库
		String count = request.getParameter("count");
		String product = request.getParameter("product");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		CartItemDao cartItemDao = new CartItemDao();

		CartItem cartItem = new CartItem();
		cartItem.setCount(Integer.parseInt(count));
		cartItem.setProduct(product);
		cartItem.setUserId(user.getPhone());
		int rows = 0;
		// 判断 购物车是否有商品
		CartItem ci = cartItemDao.selectByUserIdAndProduct(cartItem);
		if (ci != null) {// 已有商品
			cartItem.setCount(ci.getCount() + cartItem.getCount());// 原数量加本次数量
			rows = cartItemDao.update(cartItem);
		} else {// 未有商品
			rows = cartItemDao.insert(cartItem);
		}
		String data = "no";
		if (rows > 0) {
			data = "yes";
		}
		PrintWriter out = response.getWriter();
		out.write(data);
		out.close();

	}

}
