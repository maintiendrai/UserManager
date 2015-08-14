<%@ page language="java" import="java.util.*,com.testapp.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <jsp:useBean id="funcManager" class="com.testapp.FuncManager" scope="request">
 <jsp:setProperty name="funcManager" property="*" /> 
 </jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'function.jsp' starting page</title>
    
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
Page mypage = funcManager.getAllFuncByPage();
List data = mypage.getData();
 %>
 <center>功能列表</center>
   <form name="fancform" action="" method="post">
   <table border>
   <tbody>
   <tr>
   <td>选择</td>
   <td>功能编号</td>
   <td>功能</td>
   <td>URL</td>
   <td>功能描述</td>
   </tr>
    <%

Iterator it = data.iterator();

while(it.hasNext()){
	   FunctionTO func=(FunctionTO)it.next();
	
%>
<tr>
	<td><input type=radio name="funcid" value="<%=func.getFuncid() %>"></td>
	<td><%=func.getFuncid() %></td>
	<td><%=func.getFuncname() %></td>
	<td><%=func.getUrl() %></td>
	<td><%=func.getFuncdesc() %></td> 
</tr>
<%
}
 %>
<tr>
	<td colspan=5>第 <%=mypage.getPageNum() %>/<%=mypage.getPageCount() %> 页  共 <%=mypage.getRecordNum() %>条记录  
	<%if(mypage.getPageNum()==1) {
	%>
	首页&nbsp;&nbsp;
	上页&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='function.jsp?pageNum=1&pageLength=<%=mypage.getPageLength() %>'>首页</a>&nbsp;&nbsp;
	<a href='function.jsp?pageNum=<%=mypage.getPageNum()-1 %>&pageLength=<%=mypage.getPageLength() %>'>上页</a>&nbsp;&nbsp;
	<%
	}%>
	
	 
	
	<%if(mypage.getPageNum()==mypage.getPageCount()) {
	%>
	下页&nbsp;&nbsp;
	末页&nbsp;&nbsp;
	<%
	}else{
	%>
	<a href='function.jsp?pageNum=<%=mypage.getPageNum()+1 %>&pageLength=<%=mypage.getPageLength() %>'>下页</a>&nbsp;&nbsp;
	<a href='function.jsp?pageNum=<%=mypage.getPageCount() %>&pageLength=<%=mypage.getPageLength() %>'>末页</a>
	<%
	}%> 
	
	</td>
</tr>
</tbody>
</table> 
</form>
<input type=button  value="增加" onclick="add()">
<input type=button  value="删除" onclick="del()">
<input type=button  value="修改" onclick="mod()">
<script language="JavaScript">
function add(){
	document.fancform.action="FuncServlet?action=viewadd";
	document.fancform.submit();
}
function mod(){
	if(!isChecked(document.fancform.funcid)){
		alert("请选择要修改的用户！");
		return false;
	}
	document.fancform.action="FuncServlet?action=viewmod";
	document.fancform.submit();
}
function del(){
	if(!isChecked(document.fancform.funcid)){
		alert("请选择要删除的用户！");
		return false;
	}
	document.fancform.action="FuncServlet?action=del";
	document.fancform.submit();
}
</script>
  </body>
</html>
