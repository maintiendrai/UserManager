<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="rmanager" class="com.testapp.RoleManager" scope="page"></jsp:useBean>
<jsp:useBean id="fmanager" class="com.testapp.FuncManager" scope="page"></jsp:useBean>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body onload="load()">
     <%
RightTO right= (RightTO)request.getAttribute("right");
List rlist=rmanager.getRoles();
Iterator ri=rlist.iterator();
List flist=fmanager.getFunctions();
Iterator fi=flist.iterator();
if(right!=null){
 %>
 <form action="" name="rightform" method="post">
 <table border>
 	<tr>
 		<td colspan=2>权限设置</td>
 	</tr>
 	<tr>
 		<td>选择角色</td>
 		<input type=hidden name="perid" value="<%=right.getPerid() %>">
 		<td>
 		<select name="roleid">
 		<% while(ri.hasNext()){ 
 		RoleTO role=(RoleTO)ri.next();
 		int roleid=role.getRoleid();	
 		String rolename=role.getRolename() ;
 		 %>
 		<option value="<%=roleid %>" > <%=rolename %> </option>
 		<% } %>
 		</select>
 		</td>
 	</tr>
 	<tr>
 		<td>选择功能</td>	
 		<td>
 		<select name="funcid">
 		<%while(fi.hasNext()){
 		FunctionTO func=(FunctionTO)fi.next();
 		 %>
 		<option value="<%=func.getFuncid() %>"> <%=func.getFuncname() %></option>
 		<%} %>
 		</select>				
 		 </td>
 	</tr>
 	<tr>
 		<input type=button value="保存" onclick="saveuser();">
 		<input type=button value="返回" onclick="history.back();">
 	</tr>
 </table>
 </form>
 <script language="JavaScript">
 function saveuser(){
 	<%
 	if(right.isNew()){
 	%>
 	document.rightform.action="RightServlet?action=add";
 	<%
 	}else{
 	%>
 	document.rightform.action="RightServlet?action=mod";
 	<%
 	}
 	%>
 	document.rightform.submit();
 }
 
function load(){
   for(var i=0;i<document.rightform.roleid.length;i++)
  		{
   	     	if(document.rightform.roleid.options[i].value==<%=right.getRoleid() %>)
         	{
            	document.rightform.roleid.options[i].selected=true;
            	break;
        	}
    	}
     for(var i=0;i<document.rightform.funcid.length;i++)
  		{
   	     	if(document.rightform.funcid.options[i].value==<%=right.getFuncid() %>)
         	{
         	   
            	document.rightform.funcid.options[i].selected=true;
            	break;
        	}
    	}	
}
 </script>
 <%}
 else response.sendRedirect("login.jsp");
  %>
  </body>
</html>
