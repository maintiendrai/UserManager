<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'funcMod.jsp' starting page</title>
    
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
FunctionTO func= (FunctionTO)request.getAttribute("func");
if(func!=null){
 %>
 <form name="myform" action="" method=post>
 <table border>
 	<tr>
 		<td colspan=2>功能信息</td>
 	</tr>
 	<input type=hidden name=funcid value="<%=func.getFuncid() %>" >
 	<tr>
 		<td>功能名称</td>	<td><input type=text name=funcname value="<%=func.getFuncname() %>"></td>
 	</tr>
 	<tr> 
 		<td>URL</td>	<td><input type=text name=url value="<%=func.getUrl() %>"></td>
 	</tr>
 	<tr>
 		<td>功能描述</td>	<td><input type=text name=funcdesc value="<%=func.getFuncdesc() %>"></td>
 	</tr>
 	<tr>
 		<input type=button value="保存" onclick="saveuser();">
 		<input type=button value="返回" onclick="history.back();">
 	</tr>
 </table>
 </form>
 <script language="JavaScript">
 function saveuser(){
    if(document.myform.funcname.value.length==0){
  alert("功能名不可为空！");
   document.form1.funcname.focus();
                 return false;
    }
if(document.myform.url.value.length==0){
    alert("URL不可为空！");
   document.form1.newpw1.focus();
                 return false;
    }
    
 	<%
 	if(func.isNew()){
 	%>
 	document.myform.action="FuncServlet?action=add";
 	<%
 	}else{
 	%>
 	document.myform.action="FuncServlet?action=mod";
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
