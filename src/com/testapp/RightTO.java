package com.testapp;

public class RightTO {

	private int perid;
	private int roleid;
	private int funcid;
	private String rolename="";
	private String funcname="";
private boolean isNew;
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
		}
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getFuncid() {
		return funcid;
	}
	public void setFuncid(int funcid) {
		this.funcid = funcid;
	}
	public int getPerid() {
		return perid;
	}
	public void setPerid(int perid) {
		this.perid = perid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
}
