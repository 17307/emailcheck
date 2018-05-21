<%--展示过滤后的内容--%>
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
            ,url: '/guolv'
            ,page: true
            ,cols: [[
                {field: 'id', title: '编号',  fixed: 'left'},
                {field: 'address', title: '地址'},
                {field: 'theme', title: '主题' },
                {field: 'look', title: '查看'},
                {field: 'spam', title: '垃圾'}
            ]]
        });

    });
</script>
<a href="/info">查看信息</a>
</body>
</html>
