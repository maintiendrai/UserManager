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
    alert("�������û��ʺ�");
    document.loginform.userid.focus();
    return false;
  }
  if(document.loginform.password.value==""){
    alert("����������");
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
���Ѿ���¼Ϊ<%=userid %>

<a href='LogoutServlet'>�˳�ϵͳ</a>
<% 
}else{
%>
����δ��¼
<%
}
 %>
<form name="loginform" action="LoginServlet" method=post>
<table border>
<tr>
<td colspan=2 align=center>
�û���¼
</td>
</tr>
<tr>
<td>�ʺ�:</td>
<td><input type=text name="userid"  ></td>
</tr>
<tr>
<td>����:</td>
<td><input type=password name="password"></td>
</tr> 
<tr>
<!--  <tr>
<td>��ɫ</td>
<td>
<input type=radio name=role value="2">
��������Ա
<input type=radio name=role value="1">
����Ա
<input type=radio name=role value="0" checked>
��Ա
</td>  
</tr> -->
<td colspan=2 align=center>
<input type=button onclick="log()" value="��¼">
<input type=reset value="���">
</td>
</tr>
</table>  
</form>
</body>
</html>