<%@ page import="java.util.List" %>
<%@ page import="beans.Email" %><%--
  Created by IntelliJ IDEA.
  User: XMKZ
  Date: 2018/3/28
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    List<Email> emails = (List<Email>) session.getAttribute("spam_email");
    for(Email e:emails){
        out.print(e.getEmail_id()+"   ");
        out.print(e.getReceive_address()+"   ");
        out.print(e.getSubject());
        out.print("<br><br>");
    }
%>


</body>
</html>
