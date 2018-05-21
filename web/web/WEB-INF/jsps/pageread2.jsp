<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>cssmoban</title>
<!-- Bootstrap Styles-->
<link href="../../assets/css/bootstrap.css" rel="stylesheet" />
 <!-- FontAwesome Styles-->
<link href="../../assets/css/font-awesome.css" rel="stylesheet" />
 <!-- Morris Chart Styles-->
<link href="../../assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
	<!-- Custom Styles-->
<link href="../../assets/css/custom-styles.css" rel="stylesheet" />
 <!-- Google Fonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	<link rel="stylesheet" href="/js/layui/css/layui.css" media="all">
<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
</head>
<body>
<div id="wrapper">

	<!--/. NAV TOP  -->
	<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li>
					<a class="active-menu" href="/getresult"><i class="fa fa-bar-chart-o"></i> 邮件展示</a>
				</li>
				<li>
					<a href="/maliguolv"><i class="fa fa-desktop"></i>主题过滤</a>
				</li>
				<li>
					<a href="/guolvbycontent"><i class="fa fa-qrcode"></i> 内容过滤</a>
				</li>
				<li>
					<a  href="/info"><i class="fa fa-dashboard"></i>信息记录</a>
				</li>
			</ul>

		</div>

	</nav>
	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">

		<div id="page-inner">

			<%@ page contentType="text/html;charset=UTF-8" language="java" %>

			<table id="demo" lay-filter="test"></table>


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
		</div>
		 <!-- /. PAGE INNER  -->
		</div>
	 <!-- /. PAGE WRAPPER  -->
	</div>
 <!-- /. WRAPPER  -->
<!-- JS Scripts-->
<!-- jQuery Js -->
<script src="../../assets/js/jquery-1.10.2.js"></script>
  <!-- Bootstrap Js -->
<script src="../../assets/js/bootstrap.min.js"></script>
<!-- Metis Menu Js -->
<script src="../../assets/js/jquery.metisMenu.js"></script>
 <!-- Morris Chart Js -->
 <script src="../../assets/js/morris/raphael-2.1.0.min.js"></script>
<script src="../../assets/js/morris/morris.js"></script>
  <!-- Custom Js -->
<script src="../../assets/js/custom-scripts.js"></script>


</body>
</html>
