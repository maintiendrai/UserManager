<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="rmanager" class="com.testapp.RightManager" scope="request">
<jsp:setProperty property="*" name="rmanager"/>
</jsp:useBean>
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
<script type="text/javascript" src="js/ParamUtil.js"></script>
  </head>
  
  <body>
   <%
Page mypage = rmanager.getAllRightByPage();
List data = mypage.getData();
 %>
 <center>Ȩ���б�</center>
 
   <form name="rightform" action="" method="post">
   <table border>
   <tbody>
   <tr>
   <td>ѡ��</td>
   <td>Ȩ�ޱ��</td>
   <td>��ɫ����</td>
   <td>��������</td>
   </tr>
    <%

Iterator it = data.iterator();

while(it.hasNext()){
	   RightTO right=(RightTO)it.next();
	
%>
<tr>
	<td><input type=radio name="perid" value="<%=right.getPerid() %>"></td>
	<td><%=right.getPerid() %></td>
	<td><%=right.getRolename() %></td>
	<td><%=right.getFuncname() %></td>
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
	<a href='right.jsp?pageNum=1&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<a href='right.jsp?pageNum=<%=mypage.getPageNum()-1 %>&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<%
	}%>
	
	 
	
	<%if(mypage.getPageNum()==mypage.getPageCount()) {
	%>
	��ҳ&nbsp;&nbsp;
	ĩҳ&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='right.jsp?pageNum=<%=mypage.getPageNum()+1 %>&pageLength=<%=mypage.getPageLength() %>'>��ҳ</a>&nbsp;&nbsp;
	<a href='right.jsp?pageNum=<%=mypage.getPageCount() %>&pageLength=<%=mypage.getPageLength() %>'>ĩҳ</a>
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
	document.rightform.action="RightServlet?action=viewadd";
	document.rightform.submit();
}
function mod(){
	if(!isChecked(document.rightform.perid)){
		alert("��ѡ��Ҫ�޸ĵ��û���");
		return false;
	}
	document.rightform.action="RightServlet?action=viewmod";
	document.rightform.submit();
}
function del(){
	if(!isChecked(document.rightform.perid)){
		alert("��ѡ��Ҫɾ�����û���");
		return false;
	}
	document.rightform.action="RightServlet?action=del";
	document.rightform.submit();
}
</script>
  </body>
</html>