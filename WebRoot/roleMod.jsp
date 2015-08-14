<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'roleMod.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <%
RoleTO role= (RoleTO)request.getAttribute("role");
if(role!=null){
 %>
 <form name="myform" action="" method=post>
 <table border>
 	<tr>
 		<td colspan=2>角色信息</td>
 	</tr>
 	<input type=hidden name=roleid value="<%=role.getRoleid() %>" >
 	<tr>
 		<td>角色名称</td>	<td><input type=text name=rolename value="<%=role.getRolename() %>"></td>
 	</tr>
 	<tr>
 		<td>角色描述</td>	<td><input type=text name=roledesc value="<%=role.getRoledesc() %>"></td>
 	</tr>
 	<tr>
 		<input type=button value="保存" onclick="saveuser();">
 		<input type=button value="返回" onclick="history.back();">
 	</tr>
 </table>
 </form>
 <script language="JavaScript">
 function saveuser(){
  if(document.myform.rolename.value.length==0){
  alert("角色名不可为空！");
   document.form1.funcname.focus();
                 return false;
    }
 	<%
 	if(role.isNew()){
 	%>
 	document.myform.action="RoleServlet?action=add";
 	<%
 	}else{
 	%>
 	document.myform.action="RoleServlet?action=mod";
 	<%
 	}
 	%>
 	document.myform.submit();
 }
 </script>
 <%}
 else response.sendRedirect("login.jsp");
  %>
  </body>
</html>
