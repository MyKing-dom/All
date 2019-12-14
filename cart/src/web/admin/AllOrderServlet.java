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

import dao.OrderDao;
import entity.Order;

public class AllOrderServlet extends HttpServlet {

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
		doPost(request,response);
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
		response.setCharacterEncoding("utf-8");
		int draw = Integer.parseInt(request.getParameter("draw"));// draw,start,length
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		OrderDao orderDao = new OrderDao();
		int recordsTotal = orderDao.selectAllCount();
		ArrayList<Order> orders = orderDao.selectAllPaged(start,length);
		HashMap<String,Object> value = new HashMap<String,Object>();
		value.put("draw", draw);
		value.put("recordsTotal", recordsTotal);
		value.put("recordsFiltered", recordsTotal);
		value.put("data", orders);
		
		Gson gson = new Gson();
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

}
