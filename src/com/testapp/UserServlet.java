package com.testapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");

		String resultPage = "";

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		if (action.equals("add")) {
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String roleid = request.getParameter("roleid");
			String status = request.getParameter("status");

			User user = new User();
			user.setUserid(userid);
			user.setUsername(username);
			user.setPassword(password);
			user.setRoleid(Integer.parseInt(roleid));
			user.setStatus(Integer.parseInt(status));

			new UserManager().addUser(user);
			resultPage = "/UserList.jsp";
		} else if (action.equals("viewmod")) {
			User user = new UserManager().getUser(request
					.getParameter("userid"));
			request.setAttribute("user", user);
			resultPage = "/UserMod.jsp";
		} else if (action.equals("viewadd")) {
			User user = new User();
			user.setNew(true);
			request.setAttribute("user", user);
			resultPage = "/UserMod.jsp";
		} else if (action.equals("mod")) {
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String roleid = request.getParameter("roleid");
			String status = request.getParameter("status");

			User user = new User(); 
			user.setUserid(userid);
			user.setUsername(username);
			user.setPassword(password);
			user.setRoleid(Integer.parseInt(roleid));
			user.setStatus(Integer.parseInt(status));

			new UserManager().modUser(user);
			resultPage = "/UserList.jsp";
		} else if (action.equals("del")) {
			new UserManager().delUser(request.getParameter("userid"));
			resultPage = "/UserList.jsp";
		}else if(action.equals("changepw")){
			HttpSession session=request.getSession();
			String userid=(String)session.getAttribute("userid");
			String oldpw= request.getParameter("oldpw");
			String newpw= request.getParameter("newpw1");
			
			if(new UserManager().changePassword(userid, oldpw, newpw)){
				resultPage = "/sceuss.html";
			}
			else {
				resultPage = "/defeat.html";
			}
		}

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(resultPage);

		dispatcher.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
