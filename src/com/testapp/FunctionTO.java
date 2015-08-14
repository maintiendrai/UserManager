package com.testapp;

public class FunctionTO {
	private int funcid;

	private String funcname = "";

	private String url = "";

	private String funcdesc = "";

	private boolean isNew;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getFuncdesc() {
		return funcdesc;
	}

	public void setFuncdesc(String funcdesc) {
		this.funcdesc = funcdesc;
	}

	public int getFuncid() {
		return funcid;
	}

	public void setFuncid(int funcid) {
		this.funcid = funcid;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
