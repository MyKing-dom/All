package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private String contextPath;

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//获取请求路径
		String path = request.getServletPath();
		//判断是否应该放行
		boolean boo = false;
		HttpSession session = request.getSession();
		Object u = session.getAttribute("currentUser");
		if(null!=u){
			boo = true;
		}else if(
			   path.endsWith("login.html") || 
			   path.endsWith("regist.html") || 
			   path.endsWith("index.html") ||
			   path.endsWith(".css") || 
			   path.endsWith(".js") || 
			   path.endsWith(".json") || 
			   path.endsWith(".jpg") || 
			   path.endsWith(".png") || 
			   path.endsWith(".eot") || 
			   path.endsWith(".svg") || 
			   path.endsWith(".ttf") || 
			   path.endsWith(".woff") || 
			   path.endsWith(".woff2") || 
			   path.endsWith(".otf") || 
			   path.endsWith("LoginServlet") || 
			   path.endsWith("RegistServlet") || 
			   path.endsWith("CheckEmailServlet") || 
			   path.endsWith("CheckPhoneServlet") || 
			   path.endsWith("CheckUnameServlet")){
			boo = true;
		}
		if(boo){
			fc.doFilter(req, resp);//放行
		}else{
			response.sendRedirect(contextPath+"/"+(path.split("/")[1])+"/page/login.html");
		}
	}

	public void init(FilterConfig fc) throws ServletException {
		contextPath = fc.getServletContext().getContextPath();
	}

	public void destroy() {
		
	}

}
