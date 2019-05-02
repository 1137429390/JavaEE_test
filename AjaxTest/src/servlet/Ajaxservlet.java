package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.loginService;
import service.impl.loginServiceImpl;

/**
 * Servlet implementation class Ajaxservlet
 */
@WebServlet("/Ajaxservlet")
public class Ajaxservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajaxservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if("calculator".equals(flag)) {
			calculator(request, response);
		}else if("confirmName".equals(flag)) {
			confirmName(request, response);
		}
	}

	private void confirmName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//接收画面传参
		String name = request.getParameter("name");
		//调用方法查询
		loginService ls = new loginServiceImpl();
		List<User> list = ls.findByName(name);
		//判断
		response.setContentType("test/html; chartset=utf-8"); 
		if(list.size() != 0) {
			response.getWriter().print(1);
		}else {
			response.getWriter().print(0);
		}
	}

	private void calculator(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String pt = request.getParameter("pt");
		double rs = 0;
		if(pt.equals("+")) {
			rs = Double.parseDouble(num1) + Double.parseDouble(num2);
		}else if(pt.equals("-")) {
			rs = Double.parseDouble(num1) - Double.parseDouble(num2);
		}else if(pt.equals("*")) {
			rs = Double.parseDouble(num1) * Double.parseDouble(num2);
		}else if(pt.equals("/")) {
			rs = Double.parseDouble(num1) / Double.parseDouble(num2);
		}
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(rs);
	}

}
