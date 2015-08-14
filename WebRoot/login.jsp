<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
<script>
function log(){
  if(document.loginform.userid.value==""){
    alert("请输入用户帐号");
    document.loginform.userid.focus();
    return false;
  }
  if(document.loginform.password.value==""){
    alert("请输入密码");
    document.loginform.password.focus();
    return false;
  }
  document.loginform.submit();
}
</script>
</head>
<body>
<%
String userid = (String)session.getAttribute("userid"); 
if(userid!=null){
%>
您已经登录为<%=userid %>

<a href='LogoutServlet'>退出系统</a>
<% 
}else{
%>
您尚未登录
<%
}
 %>
<form name="loginform" action="LoginServlet" method=post>
<table border>
<tr>
<td colspan=2 align=center>
用户登录
</td>
</tr>
<tr>
<td>帐号:</td>
<td><input type=text name="userid"  ></td>
</tr>
<tr>
<td>密码:</td>
<td><input type=password name="password"></td>
</tr> 
<tr>
<!--  <tr>
<td>角色</td>
<td>
<input type=radio name=role value="2">
超级管理员
<input type=radio name=role value="1">
管理员
<input type=radio name=role value="0" checked>
会员
</td>  
</tr> -->
<td colspan=2 align=center>
<input type=button onclick="log()" value="登录">
<input type=reset value="清空">
</td>
</tr>
</table>  
</form>
</body>
</html>