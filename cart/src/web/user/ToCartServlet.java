package web.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.CartItemDao;
import entity.Book;
import entity.User;

public class ToCartServlet extends HttpServlet {



	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("currentUser");
		CartItemDao cartItemDao=new CartItemDao();
		ArrayList<HashMap<String,Object>> items=cartItemDao.selectCartItemAssociatedBook(user.getPhone());
		
		request.setAttribute("items", items);
		request.getRequestDispatcher("../page/cart.jsp").forward(request, response);
	}

}
