package com.testapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MenuManager {

	List funcs=new ArrayList();
	
	public boolean isAdmin(User user){
		String rolename="";
		try {
			Connection c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT rolename  FROM roles where roleid="
					+ user.getRoleid());
			if(r.next())
				rolename=r.getString("rolename");
			s.close();
			c.close();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		System.out.println("asdfasdfasdfsadf"+rolename);
		if("Admin".equals(rolename.trim()))
			return true;
		else return false;
	}
	
	public List getFunction(User user){
		Connection c;
		try {
			c = ConnectionFactory.getInstance().getConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT funcname,url  FROM functions,permission where functions.funcid=permission.funcid and permission.roleid="
					+ user.getRoleid() );
			while(r.next()){
				FunctionTO func=new FunctionTO();
				String funcname=r.getString("funcname");
				String url=r.getString("url");
				func.setFuncname(funcname);
				func.setUrl(url);
				funcs.add(func);
			}
			s.close();
			c.close();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return funcs;
	}
}
