package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Big;
import model.GameUser;
import model.Small;
import net.sf.json.JSONArray;
import service.ConnectServicce;
import service.impl.ConnectServiceImpl;

/**
 * Servlet implementation class ConnetServlet
 */
@WebServlet("/ConnetServlet")
public class ConnetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectServicce c1 = new ConnectServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnetServlet() {
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
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if("findBig".equals(flag)) {
			findBig(request,response);
		}else if("findSmall".equals(flag)) {
			findSmall(request,response);
		}else if("findUser".equals(flag)) {
			findUser(request,response);
		}
	}

	private void findUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String smallId = request.getParameter("smallId");
		List<GameUser> list = c1.findGameUser(Integer.parseInt(smallId));
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	private void findSmall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String bigId = request.getParameter("bigId");
		List<Small> list = c1.findSmall(Integer.parseInt(bigId));
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	private void findBig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Big> list = c1.findBig();
		response.setContentType("text/html; charset=utf-8"); 
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().print(jsonArray);
	}

}
