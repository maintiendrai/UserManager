<%@ page language="java" import="java.util.*,com.testapp.*" contentType="text/html; charset=gbk"
    pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="role" class="com.testapp.RoleManager" scope="page"></jsp:useBean>
<body onload="load()">
<%
User user = (User)request.getAttribute("user");
if(user!=null){
 %>
 <form name="myform" action="" method=post>
 <table border>
 	<tr>
 		<td colspan=2>�û���Ϣ</td>
 	</tr>
 	<tr>
 		<td>�ʺ�</td>	<td><input type=text name=userid value="<%=user.getUserid() %>"></td>
 	</tr>
 	<tr>
 		<td>����</td>	<td><input type=text name=username value="<%=user.getUsername() %>"></td>
 	</tr>
 	<tr> 
 		<td>����</td>	<td><input type=password name=password value="<%=user.getPassword() %>"></td>
 	</tr>
 	<tr>
 		<td>��ɫ���</td>	
 		<td>
 	 <select name="roleid">
 	 	<% 
 		List roles=role.getRoles();
 	   Iterator i=roles.iterator();
 	   while(i.hasNext()){
 	    RoleTO r=(RoleTO)i.next();
 	    %>
 	   <option value="<%=r.getRoleid() %>"><%=r.getRolename() %></option>
 	   <%} %>
 	 </select>
 	 </td>
 	</tr>
 	<tr>
 		<td>״̬</td>	
 		<td>
 		<select name="status">
 		<option value="0">����</option>
 		<option value="1">��Ծ</option>
 		</select>
 		</td>
 		
 	</tr> 
 	<tr>
 		<input type=button value="����" onclick="saveuser();">
 		<input type=button value="����" onclick="history.back();">
 	</tr>
 </table>
 </form>
 <script>
 function saveuser(){
 	<%
 	if(user.isNew()){
 	%>
 	document.myform.action="UserServlet?action=add";
 	<%
 	}else{
 	%>
 	document.myform.action="UserServlet?action=mod";
 	<%
 	}
 	%>
 	document.myform.submit();
 }
 function load(){
   for(var i=0;i<document.myform.roleid.length;i++)
  		{
   	     	if(document.myform.roleid.options[i].value==<%=user.getRoleid() %>)
         	{
            	document.myform.roleid.options[i].selected=true;
            	break;
        	}
    	}
    	 for(var i=0;i<document.myform.status.length;i++)
  		{
   	     	if(document.myform.status.options[i].value==<%=user.getStatus() %>)
         	{
            	document.myform.status.options[i].selected=true;
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