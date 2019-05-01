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
		//1.1根据页面数据计算当前页
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
		//1.接收总数据
		List<Customer> listAll = (List<Customer>)request.getAttribute("cus_list");
		List<Customer> list = null;
		//2.计算中总页数
		if(listAll.size()%CustomerPage.PAGE_COUNT == 0) {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT;
		}else {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT+1;
		}
		//3.将数据打包
		CustomerPage cp = new CustomerPage();
		cp.setList(listAll);
		cp.setTotalPage(totalPage);
		cp.setCurrentPge(currentPage);
		//4.发送至页面
		request.setAttribute("cp", cp);
		request.getRequestDispatcher("customer_index.jsp").forward(request, response);
	}

	private void loginByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 分页处理
		CustomerPageService c1 = new CustomerPageServiceImpl();
		//1.调用分页查询方法
		List<Customer> list = c1.findByPage(currentPage);
		//2.计算总共多少页
		//2.1查看共有多少条数据
		CustomerService c2 = new CustomerServiceImpl();
		List<Customer> listAll = c2.findAll();
		//2.2根据每页显示的数据和总条数计算显示的页数
		if(listAll.size()%CustomerPage.PAGE_COUNT == 0) {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT;
		}else {
			totalPage = listAll.size()/CustomerPage.PAGE_COUNT+1;
		}
		//3.将三个数据打包到对象中
		CustomerPage cp = new CustomerPage();
		cp.setList(list);
		cp.setTotalPage(totalPage);
		cp.setCurrentPge(currentPage);
		//4.发送至页面
		request.setAttribute("cp", cp);
		request.getRequestDispatcher("customer_index.jsp").forward(request, response);
	}

}
