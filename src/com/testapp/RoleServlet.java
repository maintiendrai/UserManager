package com.testapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RoleServlet() {
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
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("gbk");

		String resultPage = "";

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		if (action.equals("add")) {
			int roleid= Integer.parseInt(request.getParameter("roleid"));
			String rolename = request.getParameter("rolename");
			String roledesc = request.getParameter("roledesc");

			RoleTO role=new RoleTO();
			role.setRoleid(roleid);
			role.setRolename(rolename);
			role.setRoledesc(roledesc);

			if(new RoleManager().addRole(role))
			resultPage = "/role.jsp";
		} else if (action.equals("viewmod")) {
			RoleTO role= new RoleManager().getRole(Integer.parseInt(request
					.getParameter("roleid")));
			request.setAttribute("role", role);
			resultPage = "/roleMod.jsp";
		} else if (action.equals("mod")) {
			int roleid= Integer.parseInt(request.getParameter("roleid"));
			String rolename = request.getParameter("rolename");
			String roledesc = request.getParameter("roledesc");

			RoleTO role=new RoleTO();
			role.setRoleid(roleid);
			role.setRolename(rolename);
			role.setRoledesc(roledesc);

			 if(new RoleManager().modRole(role))
			resultPage = "/role.jsp";
		}else if (action.equals("viewadd")) {
			RoleTO role=new RoleTO();
			role.setNew(true);
			request.setAttribute("role", role);
			
			resultPage = "/roleMod.jsp";
		}
		else if (action.equals("del")) {
			new RoleManager().delRole(Integer.parseInt(request.getParameter("roleid")));
			resultPage = "/role.jsp";
		}

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(resultPage);

		dispatcher.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
