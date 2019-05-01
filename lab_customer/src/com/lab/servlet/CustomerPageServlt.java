package com.lab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab.model.Customer;
import com.lab.model.CustomerPage;
import com.lab.service.CustomerPageService;
import com.lab.service.CustomerService;
import com.lab.service.impl.CustomerPageServiceImpl;
import com.lab.service.impl.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerPageServlt
 */
@WebServlet("/CustomerPageServlt")
public class CustomerPageServlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPageServlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    static int currentPage = 1;
	static int totalPage = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String PageString = request.getParameter("PageString");
		String PageInt = request.getParameter("PageInt");
		//1.1����ҳ�����ݼ��㵱ǰҳ
		if("Add".equals(PageString)) {
			if((currentPage+1)<=totalPage) {
				currentPage += 1;
			}
		}
		if("Reduce".equals(PageString)) {
			if((currentPage-1)>0) {
				currentPage -= 1;
			}
		}
		if(PageInt != null) {
			currentPage = Integer.parseInt(PageInt);
		}
		loginByPage(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if("loginByPage".equals(flag)) {
			loginByPage(request,response);
		}else if("showBysearch".equals(flag)) {
			showBysearch(request,response);
		}
	}

	private void showBysearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.����������
		List<Customer> listAll = (List<Customer>)request.getAttribute("cus_list");
		List<Customer> list = null;
		//2.��������ҳ��
		if(listAll.size()%CustomerPage.PAGE_COUNT == 0) {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT;
		}else {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT+1;
		}
		//3.�����ݴ��
		CustomerPage cp = new CustomerPage();
		cp.setList(listAll);
		cp.setTotalPage(totalPage);
		cp.setCurrentPge(currentPage);
		//4.������ҳ��
		request.setAttribute("cp", cp);
		request.getRequestDispatcher("customer_index.jsp").forward(request, response);
	}

	private void loginByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ��ҳ����
		CustomerPageService c1 = new CustomerPageServiceImpl();
		//1.���÷�ҳ��ѯ����
		List<Customer> list = c1.findByPage(currentPage);
		//2.�����ܹ�����ҳ
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
		cp.setCurrentPge(currentPage);
		//4.������ҳ��
		request.setAttribute("cp", cp);
		request.getRequestDispatcher("customer_index.jsp").forward(request, response);
	}

}
