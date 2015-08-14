package com.testapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RoleManager {
	 private List roles=new ArrayList();
	    private int pageNum = 1;
		private int pageLength = 10;

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
		
		public List getRoles(){
			Connection c=null;
			try {
				c = ConnectionFactory.getInstance().getConnection();
				Statement s = c.createStatement();
				ResultSet r=s.executeQuery("select * from roles");
				while(r.next()){
					RoleTO role=new RoleTO();
					int roleid=r.getInt("roleid");
					String rolename=r.getString("rolename");
					String roledesc=r.getString("roledesc");
					role.setRoleid(roleid);
					role.setRolename(rolename);
					role.setRoledesc(roledesc);
					roles.add(role);
				}
				s.close();
				c.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return roles;
		}
		
		public Page getAllRoleByPage(int pageNum, int pageLength){
			ArrayList list = new ArrayList();
			Page page = new Page();
			page.setPageNum(pageNum);
			page.setPageLength(pageLength);

			try {

				Connection c = ConnectionFactory.getInstance().getConnection();
				Statement s = c.createStatement();
				// SQL code:
				int t = (pageNum - 1) * pageLength;

//				String sql = "select top " + pageLength
//						+ " * from users where userid not in(select top " + t
//						+ " userid from users)";
				
				String sql = "select  * from roles   LIMIT "+t+","+pageLength;

				ResultSet r = s.executeQuery(sql);

				while (r.next()) {
					// Capitalization doesn't matter:
					RoleTO role=new RoleTO();
					int roleid=r.getInt("roleid");
					String rolename=r.getString("rolename");
					String roledesc=r.getString("roledesc");
					role.setRoleid(roleid);
					role.setRolename(rolename);
					role.setRoledesc(roledesc);
					list.add(role);
				}
				page.setData(list);

				r = s.executeQuery("SELECT count(*)  FROM roles");
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

		public Page getAllRoleByPage() {
			ArrayList list = new ArrayList();
			Page page = new Page();
			page.setPageNum(pageNum);
			page.setPageLength(pageLength);

			try {

				Connection c = ConnectionFactory.getInstance().getConnection();
				Statement s = c.createStatement();
				// SQL code:
				int t = (pageNum - 1) * pageLength;

//				String sql = "select top " + pageLength
//						+ " * from users where userid not in(select top " + t
//						+ " userid from users)";
				
				String sql = "select  * from roles  LIMIT "+t+","+pageLength;
				
				System.out.println(sql);

				ResultSet r = s.executeQuery(sql);

				while (r.next()) {
					// Capitalization doesn't matter:
					RoleTO role=new RoleTO();
					int roleid=r.getInt("roleid");
					String rolename=r.getString("rolename");
					String roledesc=r.getString("roledesc");
					role.setRoleid(roleid);
					role.setRolename(rolename);
					role.setRoledesc(roledesc);
					list.add(role);
				}
				page.setData(list);

				r = s.executeQuery("SELECT count(*)  FROM roles");
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
		
		public boolean addRole(RoleTO role) {
			int count = 0;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();

				Statement s = c.createStatement();
				String strsql = "insert into roles(roleid,rolename,roledesc) values('"
						+role.getRoleid()
						+ "','"
						+ role.getRolename()
						+ "','"
						+ role.getRoledesc()+ "')";
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
		
		public boolean modRole(RoleTO role) {
			int count = 0;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();

				Statement s = c.createStatement();
				String strsql = "update roles set rolename='" +role.getRolename()
					  + "',roledesc='" + role.getRoledesc()
						+ "' where roleid='" + role.getRoleid() + "'";
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


		public boolean delRole(int roleid ) {
			int count = 0;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();

				Statement s = c.createStatement();
				String strsql = "delete from roles where roleid='" + roleid + "'";
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
		
		public RoleTO getRole(int roleid) {
			RoleTO role = null;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();
				Statement s = c.createStatement();
				// SQL code:
				ResultSet r = s.executeQuery("SELECT *  FROM roles where roleid='"
						+ roleid + "'");
				if (r.next()) {
					role=new RoleTO();
					String rolename=r.getString("rolename");
					String roledesc=r.getString("roledesc");
					role.setRoleid(roleid);
					role.setRolename(rolename);
					role.setRoledesc(roledesc);
				}
				s.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return role;
		}
		
		public boolean isExist(int roleid){
			Connection c=null;
			try {
				Statement s = c.createStatement();
				c = ConnectionFactory.getInstance().getConnection();
				ResultSet r = s.executeQuery("SELECT *  FROM roles where roleid="
						+ roleid);
				if(r.next())return true;
				s.close();
				c.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return false;
		}
}
