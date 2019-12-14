package web.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.BookDao;
import dao.ProductDao;
import entity.Book;
import entity.Product;
import entity.User;



public class AllProductServlet extends HttpServlet {

	
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		int draw = Integer.parseInt(request.getParameter("draw"));// draw,start,length
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		//User user=new User();
		BookDao bookDao = new BookDao();
		int recordsTotal = bookDao.selectTotalCount();
		ArrayList<Book> books = bookDao.selectPage(start,length);
		HashMap<String,Object> value = new HashMap<String,Object>();
		value.put("draw", draw);
		value.put("recordsTotal", recordsTotal);
		value.put("recordsFiltered", recordsTotal);
		value.put("data", books);
		
		//Gson gson = new Gson();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonstr = gson.toJson(value);
		PrintWriter out = null; 
		try{
			out = response.getWriter();
			out.write(jsonstr);
		}
		finally{
			out.close();
		}
			
	
	}

	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
