<%@ page import="java.util.List" %>
<%@ page import="beans.Email" %><%--
  Created by IntelliJ IDEA.
  User: XMKZ
  Date: 2018/3/3
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
ttt
    <%
        List<Email>emailList=(List<Email>) request.getAttribute("shujuku");
        for(Email e:emailList){
            out.print(e);
        }
    %>
</body>
</html>
