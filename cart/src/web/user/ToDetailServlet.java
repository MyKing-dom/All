package web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.CollectDao;
import entity.Book;
import entity.Collect;
import entity.User;

public class ToDetailServlet extends HttpServlet {



	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		Book book = new Book();
		book.setIsbn(isbn);
		BookDao bookDao = new BookDao();
		Book b = bookDao.selectByIsbn(book);
		request.setAttribute("book", b);
		//查询当前用户是否被收藏
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("currentUser");
		//user.getPhone();
		CollectDao collectDao=new CollectDao();
		Collect collect =collectDao.selectByUserIdAndProduct(user.getPhone(), isbn);
		request.setAttribute("collect", collect);
		request.getRequestDispatcher("../page/detail.jsp").forward(request, response);
		
	}

}
