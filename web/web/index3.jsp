<%--
  Created by IntelliJ IDEA.
  User: XMKZ
  Date: 2018/3/3
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<a href="/test">$END$</a>

    <%--登陆界面--%>
    <form method="post" action="${pageContext.request.contextPath}/login.do">
        用户名:<input type="text" name="username">
        密码: <input type="password" name="password">
        <input type="radio" name="mail_pop3_host" value="pop3.163.com" checked>163
        <input type="radio" name="mail_pop3_host" value="pop3.qq.com">qq
        <br>
        <input type="submit" value="登陆">
    </form>
</body>
</html>
