package com.lab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lab.model.Customer;
import com.lab.model.CustomerPage;
import com.lab.service.CustomerPageService;
import com.lab.service.CustomerService;
import com.lab.service.impl.CustomerPageServiceImpl;
import com.lab.service.impl.CustomerServiceImpl;
import com.lab.util.ValidateCode;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	CustomerService c1 = new CustomerServiceImpl();
	boolean result = false;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO doget����
		String flag = request.getParameter("flag");
		if("goAdd".equals(flag)) {
			request.getRequestDispatcher("customer_add.jsp").forward(request, response);		
		}else if("goDelete".equals(flag)) {
			goDelete(request,response);
		}else if("goIndex".equals(flag)) {
			login(request,response);
		}else if("goUpdate".equals(flag)) {
			goUpdate(request,response);
		}
	}

	private void goUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ���ݱ���޸�����
		//1. ��ȡ���
		String no = request.getParameter("no");
		//2.���ݱ�Ų�ѯ���ݣ����͵���һ��ҳ��
		Customer cs = c1.findByNo(Integer.parseInt(no));
		request.setAttribute("cs", cs);
		//3.��ת����һ������
		request.getRequestDispatcher("customer_update.jsp").forward(request, response);
	}

	private void goDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ��ת��delete����
		//1���ջ��洫�ݵı��
		String no = request.getParameter("no");
		//2.����ŷ��͵���һ������
		request.setAttribute("no", no);
		//3.��ת����һ������
		request.getRequestDispatcher("customer_delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO dopost����
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if("doLogin".equals(flag)) {
			doLogin(request,response);
		}else if("doAdd".equals(flag)) {
			doAdd(request,response);
		}else if("doDel".equals(flag)) {
			doDel(request,response);
		}else if("doUpdate".equals(flag)) {
			doUpdate(request,response);
		}else if("doSearch".equals(flag)) {
			doSearch(request,response);
		}
	}

	private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ���ݱ�Ż����ֻ���ģ����ѯ
		//1.���ջ��洫�ݵ�����(�ֻ��źͱ��)
		String no = request.getParameter("no");
		String tel = request.getParameter("tel");
		//2.����ģ����ѯ����
		List<Customer> list = c1.search(no, tel);
		//3.���÷���ֵ
		request.setAttribute("cus_list", list);
		//4.��ת��ҳ��
		request.getRequestDispatcher("CustomerPageServlt?flag=showBysearch").forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO �û���¼
		
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ִ�и������ݵĹ���
		//1.����ҳ�洫�ݵ�����
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String score = request.getParameter("score");
		String tel = request.getParameter("tel");
		String sex = request.getParameter("sex");
		Customer cs = new Customer();
		cs.setName(name);
		cs.setNo(Integer.parseInt(no));
		cs.setTel(tel);
		cs.setScore(Double.parseDouble(score));
		cs.setSex(sex);
		//2.ִ�и��¹��ܣ��������ݿ⣩
		result = c1.updateCustomer(cs);
		if(result) {
			request.getRequestDispatcher("customer_update_ok.jsp").forward(request, response);
		}else {
			System.out.println("����");
		}
	}

	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ���ݱ��ɾ������
		// ����ɾ���ı��
		String no = request.getParameter("no");
		// ����ɾ������
		result = c1.deleteCustomer(Integer.parseInt(no));
		if(result) {
			request.getRequestDispatcher("customer_delete_success.jsp").forward(request, response);
		}else {
			System.out.println("����");
		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ���һ������
		//���ջ��洫�ݵ�����
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String score = request.getParameter("score");
		String tel = request.getParameter("tel");
		String sex = request.getParameter("sex");
		//ִ����ӹ��ܣ��������ݿ⣩
		Customer cs = new Customer();
		cs.setName(name);
		cs.setNo(Integer.parseInt(no));
		cs.setTel(tel);
		cs.setScore(Double.parseDouble(score));
		cs.setSex(sex);
		result = c1.addCustomer(cs);
		if(result) {
			response.sendRedirect("CustomerServlet?flag=goIndex");
		}else {
			System.out.println("����");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ��ת����ҳ
		//��ѯ���ݿ������е����ݣ����÷�����
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null) {
			currentPage = "1";
		}
		CustomerPageService c1 = new CustomerPageServiceImpl();
		//1.���÷�ҳ��ѯ����
		List<Customer> list = c1.findByPage(Integer.parseInt(currentPage));
		//2.�����ܹ�����ҳ
		int totalPage = 0;
		//2.1�鿴���ж���������
		CustomerService c2 = new CustomerServiceImpl();
		List<Customer> listAll = c2.findAll();
		//2.2����ÿҳ��ʾ�����ݺ�������������ʾ��ҳ��
		if(listAll.size()%CustomerPage.PAGE_COUNT == 0) {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT;
		}else {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT+1;
		}
		//3.���������ݴ����������
		CustomerPage cp = new CustomerPage();
		cp.setList(list);
		cp.setTotalPage(totalPage);
		cp.setCurrentPge(Integer.parseInt(currentPage));
		//4.������ҳ��
		request.setAttribute("cp", cp);
		request.getRequestDispatcher("customer_index.jsp").forward(request, response);
	}

}
