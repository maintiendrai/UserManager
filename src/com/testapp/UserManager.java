package com.testapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class UserManager {
	private int pageNum = 1;

	private int pageLength = 10;

	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public UserManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser(String userid) {
		User user = null;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			ResultSet r = s.executeQuery("SELECT *  FROM users where userid='"
					+ userid + "'");
			if (r.next()) {
				user = new User();
				user.setRoleid(r.getInt("roleid"));
				// user.setUserid(new
				// String(r.getString("userid").getBytes("iso-8859-1"),"GBK")
				// );
				// user.setUsername(new
				// String(r.getString("username").getBytes("iso-8859-1"),"GBK")
				// );
				user.setUserid(r.getString("userid"));
				user.setUsername(r.getString("username"));
				System.out.println("here: " + user.getUsername());
				user.setStatus(r.getInt("status"));
				user.setPassword(r.getString("password"));
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean login(String userid, String password) {
		boolean flag = false;
		try {
			Connection c = ConnectionFactory.getInstance().getConnection();
			System.out.println(11111);
			Statement s = c.createStatement();
			String strsql = "select * from users where userid='" + userid + "'";
			System.out.println(strsql);
			ResultSet rs = s.executeQuery(strsql);
			if (rs.next()) {
				String password_1 = rs.getString("password");
				// System.out.println(password_1);
				// System.out.println(password);

				if (password_1.equals(password)) {
					flag = true;
				}
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String getInfo() {
		return "get info...........";
	}

	public boolean addUser(User user) {
		int count = 0;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();

			Statement s = c.createStatement();
			String strsql = "insert into users(userid,username,password,roleid,status) values('"
					+ user.getUserid()
					+ "','"
					+ user.getUsername()
					+ "','"
					+ user.getPassword()
					+ "',"
					+ user.getRoleid()
					+ ","
					+ user.getStatus() + ")";
			count = s.executeUpdate(strsql);
			System.out.print(strsql);
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean modUser(User user) {
		int count = 0;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();

			Statement s = c.createStatement();
			String strsql = "update users set username='" +user.getUsername()
					+ "',roleid=" + user.getRoleid() + ",status="
					+ user.getStatus() + " where userid='" + user.getUserid()
					+ "'";
			count = s.executeUpdate(strsql);
			System.out.print(strsql);
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delUser(String userid) {
		int count = 0;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();

			Statement s = c.createStatement();
			String strsql = "delete from users where userid='" + userid + "'";
			count = s.executeUpdate(strsql);
			System.out.print(strsql);
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public List getAllUser() {
		ArrayList list = new ArrayList();

		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			ResultSet r = s
					.executeQuery("SELECT *  FROM users ORDER BY userid");
			while (r.next()) {
				// Capitalization doesn't matter:
				User user = new User();
				user.setRoleid(r.getInt("roleid"));
				// user.setUserid(new
				// String(r.getString("userid").getBytes("iso-8859-1"),"GBK")
				// );
				// user.setUsername(new
				// String(r.getString("username").getBytes("iso-8859-1"),"GBK")
				// );
				user.setUserid(r.getString("userid"));
				user.setUsername(r.getString("username"));
				user.setStatus(r.getInt("status"));
				user.setPassword(r.getString("password"));

				list.add(user);
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Page getAllUserByPage(int pageNum, int pageLength) {
		ArrayList list = new ArrayList();
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageLength(pageLength);

		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			int t = (pageNum - 1) * pageLength;

			// String sql = "select top " + pageLength
			// + " * from users where userid not in(select top " + t
			// + " userid from users)";

			String sql = "select  * from users   LIMIT " + t + ","
					+ pageLength;

			ResultSet r = s.executeQuery(sql);

			while (r.next()) {
				// Capitalization doesn't matter:
				User user = new User();
				user.setRoleid(r.getInt("roleid"));
				// user.setUserid(new
				// String(r.getString("userid").getBytes("iso-8859-1"),"GBK")
				// );
				// user.setUsername(new
				// String(r.getString("username").getBytes("iso-8859-1"),"GBK")
				// );
				user.setUserid(r.getString("userid"));
				user.setUsername(r.getString("username"));
				user.setStatus(r.getInt("status"));
				user.setPassword(r.getString("password"));

				list.add(user);
			}
			page.setData(list);

			r = s.executeQuery("SELECT count(*)  FROM users");
			if (r.next()) {
				page.setRecordNum(r.getInt(1));
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	public Page getAllUserByPage() {
		ArrayList list = new ArrayList();
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageLength(pageLength);

		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			int t = (pageNum - 1) * pageLength;

			// String sql = "select top " + pageLength
			// + " * from users where userid not in(select top " + t
			// + " userid from users)";

			String sql = "select  * from users  LIMIT " + t + "," + pageLength;

			System.out.println(sql);

			ResultSet r = s.executeQuery(sql);

			while (r.next()) {
				// Capitalization doesn't matter:
				User user = new User();
				user.setRoleid(r.getInt("roleid"));
				// user.setUserid(new
				// String(r.getString("userid").getBytes("iso-8859-1"),"GBK")
				// );
				// user.setUsername(new
				// String(r.getString("username").getBytes("iso-8859-1"),"GBK")
				// );
				user.setUserid(r.getString("userid"));
				user.setUsername(r.getString("username"));
				user.setStatus(r.getInt("status"));
				user.setPassword(r.getString("password"));

				list.add(user);
			}
			page.setData(list);

			r = s.executeQuery("SELECT count(*)  FROM users");
			if (r.next()) {
				page.setRecordNum(r.getInt(1));
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	public boolean register(User user) {
		user.setStatus(User.STATUS_INVALID);
		return this.addUser(user);
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getRoleName(int roleid) {
		String rolename="";
		try {
			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			String sql = "select rolename from roles  where roleid=" + roleid;
			System.out.println(sql);
			ResultSet r = s.executeQuery(sql);
			if (r.next()) {
			 rolename = r.getString("rolename");
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rolename;
	}
	
	public boolean changePassword(String userid,String oldpw,String newpw ){
		String p="";
		try {
			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			ResultSet r=s.executeQuery("select password from users where userid='"+userid+"'");
			if(r.next()){
				p=r.getString("password");
			}
			if(p.equals(oldpw)){
				String sql = "update users set password='"+newpw+"'"
				+"where userid='"+userid+"'";
				System.out.println(sql);
				if(1==s.executeUpdate(sql))
					return true;	
			}	
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}