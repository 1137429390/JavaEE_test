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
		// TODO ����Ա��¼
				//1.����ҳ�洫�ݵ�����
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String code = request.getParameter("code");
				//2.�ǿ���֤
				String msg = null;
				if(" ".equals(name)) {
					msg = "�������û�����";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(" ".equals(password)) {
					msg = "���������룡";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(" ".equals(code)) {
					msg = "��������֤�룡";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//3.��ʽ��֤��������ʽ��
				//	�˺ţ�Ӣ�ģ�4~10������
				//	���룺6~8λ������Ӣ�Ķ���
				String regNo = "[a-zA-Z]{2,10}";
				String regPs = "[A-Za-z0-9]{5,8}";
				if(!name.matches(regNo)) {
					msg = "�˺Ÿ�ʽ����";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(!password.matches(regPs)) {
					msg = "�����ʽ����";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//4.��Ч����֤
				//4.1У����֤���Ƿ���ȷ
				//	��Session�л�ȡ��֤���ַ���
				HttpSession session =  request.getSession();
				String ranCode = (String)session.getAttribute(ValidateCode.RANDOMCODEKEY);
				if(!code.equals(ranCode)) {
					msg = "��֤�����";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//4.2�ж�������˺��Ƿ���ȷ
				result = c1.checkAdmin(name, password);
				if(result) {
					request.getRequestDispatcher("CustomerPageServlt?flag=loginByPage").forward(request, response);
				}
				else {
					request.getRequestDispatcher("login_fail.jsp").forward(request, response);
				}
	}

}
