package web.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;

public class ToIndexServlet extends HttpServlet {



	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		ArrayList<Book> books = bookDao.selectAll();
		request.setAttribute("books", books);
		request.setAttribute("size", books.size());
		request.getRequestDispatcher("../page/index.jsp").forward(request, response);
	}

}
