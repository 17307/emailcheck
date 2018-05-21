<%@ page import="javax.mail.Message" %>
<%@ page import="javax.mail.Address" %>
<%@ page import="javax.mail.internet.InternetAddress" %>
<%@ page import="javax.mail.Multipart" %>
<%@ page import="beans.UserLogin" %><%--
  Created by IntelliJ IDEA.
  User: XMKZ
  Date: 2018/1/1
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+path+"/";
        System.out.println(basePath);
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layui</title>
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css">
</head>
<body>
<br>
<br>

<table class="layui-table">
    <thead>
    <tr>
        <th>编号</th><th>地址</th><th>主题</th><th>查看</th>
    </tr>
    </thead>
<%
    request.setCharacterEncoding("utf8");
    Message[] messages = (Message[]) request.getAttribute("messages");
%>
    <%
        UserLogin userLogin = (UserLogin) request.getAttribute("userLogin");
    String username = userLogin.getUsername();
    for(int i=0;i<messages.length;i++){
        //String address = messages[i].getFrom()[0].toString();
        Address[] froms = messages[i].getFrom();
        String subject = messages[i].getSubject();
        String type = messages[i].getContentType();
        int message_num = messages[i].getMessageNumber();
        out.print("<tr>");
        out.print("<td>"+message_num+"</td>");
        if(froms != null) {
            //System.out.println("发件人信息:" + froms[0]);
            InternetAddress addr = (InternetAddress)froms[0];
            out.print("<td>");
            out.println("发件人地址:" + addr.getAddress());
            out.print(",");
            out.println("发件人显示名:" + addr.getPersonal());
            out.print("</td>");
        }
        out.print("<td>"+subject+"</td>");

        //纯文本直接输出-----以下是content内容
%>
    <td>
<a href="/read?id=<%=message_num%>&username=<%=username%>">查看</a></td>
<%
        out.print("</tr>");
    }
%>


</table>
</body>
</html>