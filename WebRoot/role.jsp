<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="roleMge" class="com.testapp.RoleManager" scope="request">
<jsp:setProperty property="*" name="roleMge"/>
</jsp:useBean>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'role.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/ParamUtil.js"></script>
  </head>
  
  <body>
  <%
Page mypage = roleMge.getAllRoleByPage();
List data = mypage.getData();
 %>
 <center>�����б�</center>
   <form name="roleform" action="" method="post">
   <table border>
   <tbody>
   <tr>
   <td>ѡ��</td>
   <td>��ɫ���</td>
   <td>��ɫ����</td>
   <td>��ɫ����</td>
   </tr>
    <%

Iterator it = data.iterator();

while(it.hasNext()){
	   RoleTO role=(RoleTO)it.next();
	
%>
<tr>
	<td><input type=radio name="roleid" value="<%=role.getRoleid() %>"></td>
	<td><%=role.getRoleid() %></td>
	<td><%=role.getRolename() %></td>
	<td><%=role.getRoledesc() %></td>
</tr>
<%
}
 %>
<tr>
	<td colspan=5>�� <%=mypage.getPageNum() %>/<%=mypage.getPageCount() %> ҳ  �� <%=mypage.getRecordNum() %>����¼  
	<%if(mypage.getPageNum()==1) {
	%>
	��ҳ&nbsp;&nbsp;
	��ҳ&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='role.jsp?pageNum=1&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<a href='role.jsp?pageNum=<%=mypage.getPageNum()-1 %>&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<%
	}%>
	
	 
	
	<%if(mypage.getPageNum()==mypage.getPageCount()) {
	%>
	��ҳ&nbsp;&nbsp;
	ĩҳ&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='role.jsp?pageNum=<%=mypage.getPageNum()+1 %>&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<a href='role.jsp?pageNum=<%=mypage.getPageCount() %>&pageLength=<%=mypage.getPageLength() %>'>ĩҳ</a>
	<%
	}%> 
	
	</td>
</tr>
</tbody>
</table> 
</form>
<input type=button  value="����" onclick="add()">
<input type=button  value="ɾ��" onclick="del()">
<input type=button  value="�޸�" onclick="mod()">
<script language="JavaScript">
function add(){
	document.roleform.action="RoleServlet?action=viewadd";
	document.roleform.submit();
}
function mod(){
	if(!isChecked(document.roleform.roleid)){
		alert("��ѡ��Ҫ�޸ĵ��û���");
		return false;
	}
	document.roleform.action="RoleServlet?action=viewmod";
	document.roleform.submit();
}
function del(){
	if(!isChecked(document.roleform.roleid)){
		alert("��ѡ��Ҫɾ�����û���");
		return false;
	}
	document.roleform.action="RoleServlet?action=del";
	document.roleform.submit();
}
</script>
  </body>
</html>
