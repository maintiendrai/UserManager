<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gbk"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="userManager" class="com.testapp.UserManager" scope="page"></jsp:useBean>
<jsp:useBean id="menuManager" class="com.testapp.MenuManager" scope="page"></jsp:useBean>
<html>
<head>
	<script type="text/javascript" src="js/dtree.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
</head>

<body>
<% 
   String userid=(String)session.getAttribute("userid");
   User user=userManager.getUser(userid);
 %>
<div class="dtree">
	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
	<script type="text/javascript">
		d = new dTree('d');
		d.add(0,-1,'�ҵĲ˵�');
		<% int i=7; 
		List list=menuManager.getFunction(user);
		Iterator itr=list.iterator();
		if(menuManager.isAdmin(user)){ %>
		d.add(1,0,'���ܹ���','function.jsp','test','wndMain');
		d.add(2,0,'�û�����','UserList.jsp','','wndMain');
		d.add(5,0,'��ɫ����','role.jsp','test','wndMain');
		d.add(6,0,'Ȩ�޹���','right.jsp','test','wndMain');
		<%} else{
		while(itr.hasNext()){
		FunctionTO func=(FunctionTO)itr.next();
		 %>
		d.add(<%=i++ %>,0,'<%=func.getFuncname() %>','<%=func.getUrl() %>','test','wndMain')
		<%}
		 } 
		 %>
		d.add(3,0,'�޸�����','changepw.html','','wndMain');
		d.add(4,0,'�˳�ϵͳ','LogoutServlet','','_top','img/trash.gif');
		document.write(d);
	</script>

</div>

</body>

</html>