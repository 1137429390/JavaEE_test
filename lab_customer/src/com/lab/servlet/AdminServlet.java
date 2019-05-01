package com.lab.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lab.service.AdminService;
import com.lab.service.CustomerService;
import com.lab.service.impl.AdminServiceImpl;
import com.lab.service.impl.CustomerServiceImpl;
import com.lab.util.ValidateCode;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminService c1 = new AdminServiceImpl();
	boolean result = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		if("doLogin".equals(flag)) {
			doLogin(request,response);
		}
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO 管理员登录
				//1.接收页面传递的数据
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String code = request.getParameter("code");
				//2.非空验证
				String msg = null;
				if(" ".equals(name)) {
					msg = "请输入用户名！";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(" ".equals(password)) {
					msg = "请输入密码！";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(" ".equals(code)) {
					msg = "请输入验证码！";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//3.格式验证（正则表达式）
				//	账号：英文，4~10个长度
				//	密码：6~8位，数字英文都可
				String regNo = "[a-zA-Z]{2,10}";
				String regPs = "[A-Za-z0-9]{5,8}";
				if(!name.matches(regNo)) {
					msg = "账号格式不对";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(!password.matches(regPs)) {
					msg = "密码格式不对";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//4.有效性验证
				//4.1校验验证码是否正确
				//	从Session中获取验证码字符串
				HttpSession session =  request.getSession();
				String ranCode = (String)session.getAttribute(ValidateCode.RANDOMCODEKEY);
				if(!code.equals(ranCode)) {
					msg = "验证码错误！";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//4.2判断密码和账号是否正确
				result = c1.checkAdmin(name, password);
				if(result) {
					request.getRequestDispatcher("CustomerPageServlt?flag=loginByPage").forward(request, response);
				}
				else {
					request.getRequestDispatcher("login_fail.jsp").forward(request, response);
				}
	}

}
