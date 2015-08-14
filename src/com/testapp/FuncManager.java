package com.testapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FuncManager {
    private List functions=new ArrayList();
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

	public List getFunctions(){
		Connection c=null;
		try {
			c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			ResultSet r=s.executeQuery("select * from functions");
			while(r.next()){
				FunctionTO func=new FunctionTO();
				int funcid=r.getInt("funcid");
				String funcname=r.getString("funcname");
				String url=r.getString("url");
				String funcdesc=r.getString("funcdesc");
				func.setFuncid(funcid);
				func.setFuncname(funcname);
				func.setUrl(url);
				func.setFuncdesc(funcdesc);
				functions.add(func);
			}
			s.close();
			c.close();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return functions;
	}
	
	public Page getAllFuncByPage(int pageNum, int pageLength){
		ArrayList list = new ArrayList();
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageLength(pageLength);

		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			int t = (pageNum - 1) * pageLength;

//			String sql = "select top " + pageLength
//					+ " * from users where userid not in(select top " + t
//					+ " userid from users)";
			
			String sql = "select  * from functions   LIMIT "+t+","+pageLength;

			ResultSet r = s.executeQuery(sql);

			while (r.next()) {
				// Capitalization doesn't matter:
				FunctionTO func=new FunctionTO();
				int funcid=r.getInt("funcid");
				String funcname=r.getString("funcname");
				String url=r.getString("url");
				String funcdesc=r.getString("funcdesc");
				func.setFuncid(funcid);
				func.setFuncname(funcname);
				func.setUrl(url);
				func.setFuncdesc(funcdesc);
				list.add(func);
			}
			page.setData(list);

			r = s.executeQuery("SELECT count(*)  FROM functions");
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

	public Page getAllFuncByPage() {
		ArrayList list = new ArrayList();
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageLength(pageLength);

		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			int t = (pageNum - 1) * pageLength;

//			String sql = "select top " + pageLength
//					+ " * from users where userid not in(select top " + t
//					+ " userid from users)";
			
			String sql = "select  * from functions  LIMIT "+t+","+pageLength;
			
			System.out.println(sql);

			ResultSet r = s.executeQuery(sql);

			while (r.next()) {
				// Capitalization doesn't matter:
				FunctionTO func=new FunctionTO();
				int funcid=r.getInt("funcid");
				String funcname=r.getString("funcname");
				String url=r.getString("url");
				String funcdesc=r.getString("funcdesc");
				func.setFuncid(funcid);
				func.setFuncname(funcname);
				func.setUrl(url);
				func.setFuncdesc(funcdesc);
				list.add(func);
			}
			page.setData(list);

			r = s.executeQuery("SELECT count(*)  FROM functions");
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
	
	public boolean addFunc(FunctionTO func) {
		int count = 0;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();

			Statement s = c.createStatement();
			String strsql = "insert into functions(funcname,url,funcdesc) values('"
					+ func.getFuncname()
					+ "','"
					+ func.getUrl()
					+ "','"
					+ func.getFuncdesc()+ "')";
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
	
	public boolean modFunc(FunctionTO func) {
		int count = 0;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();

			Statement s = c.createStatement();
			String strsql = "update functions set funcname='" + func.getFuncname()
				  + "',url='" + func.getUrl()
				  +"',funcdesc='"+func.getFuncdesc()
					+ "' where funcid=" + func.getFuncid();
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


	public boolean delFunc(int funcid ) {
		int count = 0;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();

			Statement s = c.createStatement();
			String strsql = "delete from functions where funcid='" + funcid + "'";
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
	
	public FunctionTO getFunc(int funcid) {
		FunctionTO func = null;
		try {

			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			// SQL code:
			ResultSet r = s.executeQuery("SELECT *  FROM functions where funcid='"
					+ funcid + "'");
			if (r.next()) {
				func=new FunctionTO();
				String funcname=r.getString("funcname");
				String url=r.getString("url");
				String funcdesc=r.getString("funcdesc");
				func.setFuncid(funcid);
				func.setFuncname(funcname);
				func.setUrl(url);
				func.setFuncdesc(funcdesc);
			}
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return func;
	}
	
	public boolean isExist(int funcid){
		Connection c=null;
		try {
			Statement s = c.createStatement();
			c = ConnectionFactory.getInstance().getConnection();
			ResultSet r = s.executeQuery("SELECT *  FROM functions where funcid='"
					+ funcid + "'");
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
