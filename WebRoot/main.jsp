<%@ page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>欢迎。。。</title>
<%
String strUserid = (String) session.getAttribute("userid");
			if (strUserid != null && strUserid != "") {

				%>

</head>
<!-- frames -->
<frameset rows="80,*" framespacing="0" border="0" frameborder="0"
	style="border:0px">
	<frame name="title" src="top.htm" marginwidth="1"
		marginheight="1" scrolling="no" frameborder="0" >
	<frameset name="content" cols="180,*" frameborder="yes"
		style="border-right:1px solid threedhighlight;border-left:1px solid threedshadow;border-bottom:1px solid threedhighlight;border-top:1px solid threedshadow">
		<frame name="menu" src="menu.jsp" marginwidth="1"
			marginheight="1" scrolling="no" frameborder="0" target="wndMain">
		<frame name="wndMain" src="introduce.htm" marginwidth="1"
			marginheight="1" scrolling="auto"  target="_self">
	</frameset>
</frameset>

<%
//无权访问返回无权页面
			} else {
				response.sendRedirect("login.jsp");
			}

		%>

</html>
