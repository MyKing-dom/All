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

public class ChangeItemNumServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		CartItemDao cartItemDao = new CartItemDao();
		CartItem cartItem = new CartItem();
		cartItemDao.updateNumById(Integer.parseInt(request.getParameter("itemId")),Integer.parseInt(request.getParameter("num")));
	}

}
