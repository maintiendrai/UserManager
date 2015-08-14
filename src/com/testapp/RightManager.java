package com.testapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RightManager {

	 private List rights=new ArrayList();
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
		
		public List getRights(){
			Connection c=null;
			try {
				c = ConnectionFactory.getInstance().getConnection();
				Statement s = c.createStatement();
				ResultSet r=s.executeQuery("select perid,rolename,funcname from permission,roles,functions where permission.roleid=roles.id and permission.funcid=functions.id");
				while(r.next()){
					RightTO right=new RightTO();
					int perid=r.getInt("perid");
					String rolename=r.getString("rolename");
					String funcname=r.getString("funcname");
					right.setPerid(perid);
					right.setRolename(rolename);
					right.setFuncname(funcname);
					rights.add(right);
				}
				s.close();
				c.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return rights;
		}
		
		public Page getAllRightByPage(int pageNum, int pageLength){
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
				
				String sql = "select perid,rolename,funcname from permission,roles,functions where permission.roleid=roles.roleid and permission.funcid=functions.funcid  LIMIT "+t+","+pageLength;

				ResultSet r = s.executeQuery(sql);

				while (r.next()) {
					// Capitalization doesn't matter:
					RightTO right=new RightTO();
					int perid=r.getInt("perid");
					String rolename=r.getString("rolename");
					String funcname=r.getString("funcname");
					right.setPerid(perid);
					right.setRolename(rolename);
					right.setFuncname(funcname);
					list.add(right);
				}
				page.setData(list);

				r = s.executeQuery("SELECT count(*)  FROM permission");
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

		public Page getAllRightByPage() {
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
				
				String sql = "select perid,rolename,funcname from permission,roles,functions where permission.roleid=roles.roleid and permission.funcid=functions.funcid   LIMIT "+t+","+pageLength;
				
				System.out.println(sql);

				ResultSet r = s.executeQuery(sql);

				while (r.next()) {
					// Capitalization doesn't matter:
					RightTO right=new RightTO();
					int perid=r.getInt("perid");
					String rolename=r.getString("rolename");
					String funcname=r.getString("funcname");
					right.setPerid(perid);
					right.setRolename(rolename);
					right.setFuncname(funcname);
					list.add(right);
				}
				page.setData(list);

				r = s.executeQuery("SELECT count(*)  FROM permission");
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
		
		public boolean addRight(RightTO right) {
			int count = 0;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();

				Statement s = c.createStatement();
				String strsql = "insert into permission(roleid,funcid) values("
						+ right.getRoleid()
						+ ","
						+ right.getFuncid()+ ")";
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
		
		public boolean modRight(RightTO right) {
			int count = 0;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();

				Statement s = c.createStatement();
				String strsql = "update permission set roleid=" + right.getRoleid()
					  + ",funcid=" + right.getFuncid()
						+ " where perid=" + right.getPerid();
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


		public boolean delRight(int perid ) {
			int count = 0;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();

				Statement s = c.createStatement();
				String strsql = "delete from permission where perid='" + perid + "'";
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
		
		public RightTO getRight(int perid) {
			RightTO right = null;
			try {

				Connection c = ConnectionFactory.getInstance().getConnection();
				Statement s = c.createStatement();
				// SQL code:
				ResultSet r = s.executeQuery("select permission.roleid,permission.funcid,rolename,funcname from permission,roles,functions where permission.roleid=roles.roleid and permission.funcid=functions.funcid and  perid='"
						+ perid + "'");
				if (r.next()) {
					right=new RightTO();
					int roleid=r.getInt("permission.roleid");
					int funcid=r.getInt("permission.funcid");
					String rolename=r.getString("rolename");
					String funcname=r.getString("funcname");
					right.setPerid(perid);
					right.setRoleid(roleid);
					right.setFuncid(funcid);
					right.setRolename(rolename);
					right.setFuncname(funcname);
				}
				s.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return right;
		}
}
