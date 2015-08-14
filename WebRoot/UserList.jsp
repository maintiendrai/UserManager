<%@ page language="java" import="java.util.*,com.testapp.*" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%> 
<jsp:useBean id="userMgr" scope="session" class="com.testapp.UserManager"/> 
<jsp:setProperty name="userMgr" property="*" /> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="js/ParamUtil.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
Page mypage = userMgr.getAllUserByPage();
List data = mypage.getData();
System.out.println("data.size()="+data.size());
 %>
<center>用户列表</center>
<form name="myform" action="" method=post>
<table border>
<tr>
	<td>选择</td>
	<td>帐号</td>
	<td>名称</td>
	<td>角色</td>
	<td>状态</td> 
</tr>
<%

Iterator it = data.iterator();

while(it.hasNext()){
	User u = (User)it.next();
	
%>
<tr>
	<td><input type=radio name="userid" value="<%=u.getUserid() %>"></td>
	<td><%=u.getUserid() %></td>
	<td><%=u.getUsername() %></td>
	<td><%=userMgr.getRoleName(u.getRoleid()) %></td>
	<td>
	<%if(0==u.getStatus()) {%>
	冻结
	<%}else if(1==u.getStatus()){ %>
	活跃
	<%} %>
	</td> 
</tr>
<%
}
 %>
<tr>
	<td colspan=5>第 <%=mypage.getPageNum() %>/<%=mypage.getPageCount() %> 页  共 <%=mypage.getRecordNum() %>条记录  
	<%if(mypage.getPageNum()==1) {
	%>
	首页&nbsp;&nbsp;
	上页&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='UserList.jsp?pageNum=1&pageLength=<%=mypage.getPageLength() %>'>首页</a>&nbsp;&nbsp;
	<a href='UserList.jsp?pageNum=<%=mypage.getPageNum()-1 %>&pageLength=<%=mypage.getPageLength() %>'>上页</a>&nbsp;&nbsp;
	<%
	}%>
	
	 
	
	<%if(mypage.getPageNum()==mypage.getPageCount()) {
	%>
	下页&nbsp;&nbsp;
	末页&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='UserList.jsp?pageNum=<%=mypage.getPageNum()+1 %>&pageLength=<%=mypage.getPageLength() %>'>下页</a>&nbsp;&nbsp;
	<a href='UserList.jsp?pageNum=<%=mypage.getPageCount() %>&pageLength=<%=mypage.getPageLength() %>'>末页</a>
	<%
	}%> 
	
	</td>
</tr>
</table> 
</form>
<input type=button  value="增加" onclick="adduser()">
<input type=button  value="删除" onclick="deluser()">
<input type=button  value="修改" onclick="moduser()">
<script>
function adduser(){
	document.myform.action="UserServlet?action=viewadd";
	document.myform.submit();
}
function moduser(){
	if(!isChecked(document.myform.userid)){
		alert("请选择要修改的用户！");
		return false;
	}
	document.myform.action="UserServlet?action=viewmod";
	document.myform.submit();
}
function deluser(){
	if(!isChecked(document.myform.userid)){
		alert("请选择要删除的用户！");
		return false;
	}
	document.myform.action="UserServlet?action=del";
	document.myform.submit();
}
</script>
</body>
</html>