<%@ page import="javax.mail.Message" %>
<%@ page import="java.io.IOException" %><%--用于展示邮件内容--%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>邮件</title>
  <link rel="stylesheet" href="/js/layui/css/layui.css" media="all">
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table id="demo" lay-filter="test"></table>
 
<script src="/js/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;

  table.render({
    elem: '#demo'
    ,height: 465
    ,url: '/getdata'
    ,page: true
    ,cols: [[
      {field: 'id', title: '编号',  fixed: 'left'},
      {field: 'address', title: '地址'},
      {field: 'theme', title: '主题' },
      {field: 'look', title: '查看'}
    ]]
  });
  
});
</script>
<a href="/maliguolv">一键过滤-基于主题（快）</a>
<%--测试message--%>
<%--测试其他--%>
<a href="/guolvbycontent">一键过滤-基于内容（慢）</a>
</body>
</html>