<%@ page import="beans.Email" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>cssmoban</title>
    <!-- Bootstrap Styles-->
    <link href="../../assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FontAwesome Styles-->
    <link href="../../assets/css/font-awesome.css" rel="stylesheet"/>
    <!-- Morris Chart Styles-->
    <link href="../../assets/js/morris/morris-0.4.3.min.css" rel="stylesheet"/>
    <!-- Custom Styles-->
    <link href="../../assets/css/custom-styles.css" rel="stylesheet"/>
    <!-- Google Fonts-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="../../assets/js/Lightweight-Chart/cssCharts.css">
</head>

<body>
<div id="wrapper">

    <!--/. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li>
                    <a  href="/getresult"><i class="fa fa-bar-chart-o"></i> 邮件展示</a>
                </li>
                <li>
                    <a href="/maliguolv"><i class="fa fa-desktop"></i>主题过滤</a>
                </li>
                <li>
                    <a href="/guolvbycontent"><i class="fa fa-qrcode"></i> 内容过滤</a>
                </li>
                <li>
                    <a class="active-menu" href="/info"><i class="fa fa-dashboard"></i>信息记录</a>
                </li>
            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper">

        <div id="page-inner">
            <table border="1">
                <caption>垃圾邮件</caption>
                <tr>
                    <th>编号</th>
                    <th>地址</th>
                    <th>主题</th>
                </tr>
            <!-- /. ROW  -->
                <%
                List<Email> emails = (List<Email>) session.getAttribute("spam_email");
                for(Email e:emails){
                    out.print("<tr>");
                    out.print("<td>"+e.getEmail_id()+"</td>");
                    out.print("<td>"+e.getReceive_address()+"</td>");
                    out.print("<td>"+e.getSubject()+"</td>");
                    out.print("</tr>");
                }
                %>
            </table>
        </div>
    </div>

</div>
<!-- /. PAGE INNER  -->

<!-- /. PAGE WRAPPER  -->

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


<script src="../../assets/js/easypiechart.js"></script>
<script src="../../assets/js/easypiechart-data.js"></script>

<script src="../../assets/js/Lightweight-Chart/jquery.chart.js"></script>

<!-- Custom Js -->
<script src="../../assets/js/custom-scripts.js"></script>

<script>

</script>

</body>

</html>