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
<center>�û��б�</center>
<form name="myform" action="" method=post>
<table border>
<tr>
	<td>ѡ��</td>
	<td>�ʺ�</td>
	<td>����</td>
	<td>��ɫ</td>
	<td>״̬</td> 
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
	����
	<%}else if(1==u.getStatus()){ %>
	��Ծ
	<%} %>
	</td> 
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
	<a href='UserList.jsp?pageNum=1&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<a href='UserList.jsp?pageNum=<%=mypage.getPageNum()-1 %>&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<%
	}%>
	
	 
	
	<%if(mypage.getPageNum()==mypage.getPageCount()) {
	%>
	��ҳ&nbsp;&nbsp;
	ĩҳ&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='UserList.jsp?pageNum=<%=mypage.getPageNum()+1 %>&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<a href='UserList.jsp?pageNum=<%=mypage.getPageCount() %>&pageLength=<%=mypage.getPageLength() %>'>ĩҳ</a>
	<%
	}%> 
	
	</td>
</tr>
</table> 
</form>
<input type=button  value="����" onclick="adduser()">
<input type=button  value="ɾ��" onclick="deluser()">
<input type=button  value="�޸�" onclick="moduser()">
<script>
function adduser(){
	document.myform.action="UserServlet?action=viewadd";
	document.myform.submit();
}
function moduser(){
	if(!isChecked(document.myform.userid)){
		alert("��ѡ��Ҫ�޸ĵ��û���");
		return false;
	}
	document.myform.action="UserServlet?action=viewmod";
	document.myform.submit();
}
function deluser(){
	if(!isChecked(document.myform.userid)){
		alert("��ѡ��Ҫɾ�����û���");
		return false;
	}
	document.myform.action="UserServlet?action=del";
	document.myform.submit();
}
</script>
</body>
</html>