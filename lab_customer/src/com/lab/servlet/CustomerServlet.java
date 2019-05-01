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
		// TODO doget方法
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
		// TODO 根据编号修改数据
		//1. 获取编号
		String no = request.getParameter("no");
		//2.根据编号查询数据，发送到下一个页面
		Customer cs = c1.findByNo(Integer.parseInt(no));
		request.setAttribute("cs", cs);
		//3.跳转到下一各画面
		request.getRequestDispatcher("customer_update.jsp").forward(request, response);
	}

	private void goDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 跳转到delete画面
		//1接收画面传递的编号
		String no = request.getParameter("no");
		//2.将编号发送到下一个画面
		request.setAttribute("no", no);
		//3.跳转到下一个画面
		request.getRequestDispatcher("customer_delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO dopost方法
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
		// TODO 根据编号或者手机号模糊查询
		//1.接收画面传递的数据(手机号和编号)
		String no = request.getParameter("no");
		String tel = request.getParameter("tel");
		//2.调用模糊查询方法
		List<Customer> list = c1.search(no, tel);
		//3.设置返回值
		request.setAttribute("cus_list", list);
		//4.跳转到页面
		request.getRequestDispatcher("CustomerPageServlt?flag=showBysearch").forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 用户登录
		
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 执行更新数据的功能
		//1.接收页面传递的数据
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
		//2.执行更新功能（链接数据库）
		result = c1.updateCustomer(cs);
		if(result) {
			request.getRequestDispatcher("customer_update_ok.jsp").forward(request, response);
		}else {
			System.out.println("错误");
		}
	}

	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 根据编号删除数据
		// 接收删除的编号
		String no = request.getParameter("no");
		// 调用删除方法
		result = c1.deleteCustomer(Integer.parseInt(no));
		if(result) {
			request.getRequestDispatcher("customer_delete_success.jsp").forward(request, response);
		}else {
			System.out.println("错误");
		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 添加一条数据
		//接收画面传递的数据
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String score = request.getParameter("score");
		String tel = request.getParameter("tel");
		String sex = request.getParameter("sex");
		//执行添加功能（链接数据库）
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
			System.out.println("错误");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 跳转到首页
		//查询数据库中所有的数据（调用方法）
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null) {
			currentPage = "1";
		}
		CustomerPageService c1 = new CustomerPageServiceImpl();
		//1.调用分页查询方法
		List<Customer> list = c1.findByPage(Integer.parseInt(currentPage));
		//2.计算总共多少页
		int totalPage = 0;
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
		cp.setCurrentPge(Integer.parseInt(currentPage));
		//4.发送至页面
		request.setAttribute("cp", cp);
		request.getRequestDispatcher("customer_index.jsp").forward(request, response);
	}

}
