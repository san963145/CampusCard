<%@page import="com.sys.card.bean.*" import="com.sys.card.daoImpl.*" import="com.wiscom.is.*, java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" import="java.util.List" import="java.util.Iterator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
  <head>
  
<%
  String department="";
  String[] grades=null;
  User user=(User)session.getAttribute("user");
  if(user!=null)
  {
	  department=user.getDepartment();
	  grades=user.getGrade().split("#");
  }
  String grade=(String)request.getAttribute("grade");
  List list1=(List)request.getAttribute("list1");
  List list2_1=(List)request.getAttribute("list2_1");
  List list2_2=(List)request.getAttribute("list2_2");
%>  


  
    <base href="${sessionScope.basePath }">
    <meta charset="UTF-8">
    <title>一卡通消费分析系统</title>
	 <!-- 禁缓存-->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <!-- 响应屏幕宽度	-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- 添加的css-->
    <link rel="stylesheet" href="dist/css/my.css">
    <!-- Bootstrap 3.3.4 框架-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- FontAwesome 4.3.0 图标-->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<!-- Theme style 网站构造-->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css"> 
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">	

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
   <script charset="UTF-8">
    	   
	function check()
	{
		var s=document.getElementById("grades").value;
		if(s=="选择年级")
	    {
			alert("请选择年级!");
			return false;
	    }
		else
		{
			return true;
		}
	}
	
	
  </script>
  
  <body class="skin-blue-light sidebar-mini">
    <div class="wrapper">

      <header class=" main-header" id="my-menu">
        <!-- Logo -->
		<a class="logo" href="#systems" data-toggle="collapse" data-parent="#my-menu">
			<b>一卡通消费分析系统</b>
		</a>
		
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle visible-xs" data-toggle="offcanvas" role="button">
            <span class="sr-only">${sessionScope.curUser}</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

              <!-- User info -->
              <li>
			    <a href="pages/manager/info.jsp">${sessionScope.curUser}</a>
			  </li>
			  <li>
			    <a href="logout.jsp"><i class="fa fa-mail-forward"></i></a>
			  </li>
              
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
		
		<!-- sidebar: style can be found in sidebar.less -->
        <section id="sidebar" class="sidebar collapse in">
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa fa-edit"></i> 
				<span>消费记录</span> 
				<i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
               <c:if test="${sessionScope.authority=='Admin'}">
                  <li class="admin"><a href="pages/cardSystem/searchByGroup.jsp"><i class="fa fa-circle-o"></i> 按学院查询</a></li>
               </c:if>
               <c:if test="${sessionScope.authority=='Dean'}">
                  <li class="dean"><a href="pages/cardSystem/searchByGroup.jsp"><i class="fa fa-circle-o"></i> 按年级查询</a></li>
               </c:if>
               <c:if test="${sessionScope.authority=='Instructor'}">
                  <li class="instruct"><a href="pages/cardSystem/searchByGroup.jsp"><i class="fa fa-circle-o"></i> 年级整体查询</a></li>
               </c:if>
                <li><a href="pages/cardSystem/searchByPerson.jsp"><i class="fa fa-circle-o"></i> 按学生查询</a></li>
              </ul>
            </li>
            <li class="treeview active">
              <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>统计分析</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <c:if test="${sessionScope.authority=='Admin'}">
                  <li class="admin"><a href="DrawStatistics"><i class="fa fa-circle-o"></i> 全校统计</a></li>
                </c:if>
                <c:if test="${sessionScope.authority=='Dean'}">
				<li class="dean"><a href="DrawStatistics"><i class="fa fa-circle-o"></i> 学院统计</a></li>
				</c:if>
				<c:if test="${sessionScope.authority=='Instructor'}">
				<li class="instruct"><a href="pages/cardSystem/statisticalResultsInstructor.jsp"><i class="fa fa-circle-o"></i> 年级统计</a></li>
				</c:if>
				<c:if test="${sessionScope.authority=='Admin'}">
				<li class="admin"><a href="DrawComparison"><i class="fa fa-circle-o"></i> 学院对比</a></li>
				 </c:if>
				<c:if test="${sessionScope.authority=='Dean'}">
				<li class="dean"><a href="DrawComparison"><i class="fa fa-circle-o"></i> 年级对比</a></li>
                </c:if>
                <li class="admin dean instruct"><a href="MapStatistics"><i class="fa fa-circle-o"></i> 生源差异</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-sort-amount-desc"></i>
                <span>消费指数</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/cardSystem/ratingsResults.jsp"><i class="fa fa-circle-o"></i> 查看结果</a></li>
              </ul>
            </li>
			<li>
              <a href="pages/cardSystem/consumptionForecast.jsp">
                 <i class="fa fa-line-chart"></i> <span>消费预测</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-signal"></i> <span>助学金推荐</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/cardSystem/recommendByGroup.jsp"><i class="fa fa-circle-o"></i> 按年级查询</a></li>
                <li><a href="pages/cardSystem/recommendByPerson.jsp"><i class="fa fa-circle-o"></i> 按学生查询</a></li>
              </ul>
            </li>
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-cog"></i> <span>系统管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li>
                     <a href="pages/manager/info.jsp">
                         <i class="fa fa-user"></i> <span>个人信息</span>
                     </a>
                 </li>
			     <li class="admin dean active">
                       <a href="pages/manager/addUser.jsp">
                         <i class="fa fa-user-plus"></i> <span>添加用户</span>
                       </a>
                </li>
               </ul>
            </li>
            
          </ul>
        </section>
        <!-- /.sidebar -->
	  </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
				<small>统计分析</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="index.jsp" ><i class="fa fa-dashboard"></i> 主页</a></li>
			<li> 统计分析</li>
			<li class="instruct active">年级统计</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
        
        <div class="box-body">
					<form action="DrawStatistics" method="post" onsubmit="return check()">
						<div class="row">
							<div class="col-md-4">
							  <div class="form-group">
								<label>选择年级</label>
								<select class="form-control select2" id="grades" name=grade style="width: 100%;">

								  <option selected="selected">选择年级</option>
								    <%for(int i=0;i<grades.length;i++){ %>
								     <option><%=grades[i] %></option>
								    <%} %>
								    
								</select>
							  </div><!-- /.form-group -->
							</div><!-- /.col -->
							<div class="col-md-2 col-md-offset-1">
							  <label>&nbsp;</label>
							  <button type="submit" class="btn btn-primary btn-block btn-flat">查询统计结果</button>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</form>
				</div><!-- /.box-body -->
        
        <%if(list1!=null){if(list1.size()>0){ %>
          <!-- Main row -->
          <div class="row">
			
			 <div class="col-xs-6 col-md-6">
				<!-- SELECT2 EXAMPLE -->
				  <div class="box box-info">
					<div class="box-header with-border">
					  <h3 class="instruct box-title"><%=department %><%=grade %>级学生消费均值(单位:元)</h3>
					  <div class="box-tools pull-right">
						<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					  </div>
					</div><!-- /.box-header -->
					<div class="box-body">
					    <canvas id="barChart1" style="height:250px"></canvas>
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			 </div>
			 
			 <div class="col-xs-6 col-md-6">
				<!-- SELECT2 EXAMPLE -->
				  <div class="box box-info">
					<div class="box-header with-border">
					  <h3 class="instruct box-title"><%=department %><%=grade %>级学生消费次数(单位:次)</h3>
					  <div class="box-tools pull-right">
						<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					  </div>
					</div><!-- /.box-header -->
					<div class="box-body">
					    <canvas id="barChart2" style="height:250px"></canvas>
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			 </div>

			  <div class="col-xs-12 col-md-6">
				<div class="box box-info">
					<div class="box-header with-border">
					    <h3 class="box-title">按性别差异显示消费情况</h3>					    				  
					  <div class="box-tools pull-right">
						<button class="btn btn-box-tool" data-widget="collapse"></button>
					  </div>
					  <div class="pull-right">				    
						<i class="fa fa-square" style="color:rgba(60,141,188,0.9)"></i>男
						<i class="fa fa-square" style="color:#00a65a"></i>女
					  </div>
					</div><!-- /.box-header -->
					<div class="box-body">
					    <canvas id="barChart3" style="height:250px"></canvas>
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			  </div>
			  <!--  
			  <div class="col-xs-12 col-md-6">
				<div class="box box-info">
					<div class="box-header with-border">
					  <h3 class="box-title">按年级差异显示消费情况</h3>
					  <div class="box-tools pull-right">
						<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					  </div>
					</div>
					<div class="box-body">
					    				
					</div>
				 </div>
			  </div>
             -->
			  
          </div><!-- /.row (main row) -->
          <%}else{%>
			<h4><b>&nbsp;&nbsp;&nbsp;不存在该项数据！</b></h4>
			<% } }%>
          
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.0
        </div>
		<p class="text-center">
        <strong>一卡通消费分析系统</strong>
		</p>
      </footer>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick 
    从点击屏幕上的元素到触发元素的 click 事件，移动浏览器会有大约 300 毫秒的等待时间。
    它想看看你是不是要进行双击（double tap）操作。-->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>

	<!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
	<!-- my js -->
    <script src="dist/js/my.js"></script>
    <%if(list1!=null){if(list1.size()>0){ %>
    <script>
     $(function () {        
	
		//-------------
        //- BAR CHART -
        //-------------
        var barChartCanvas1 = $("#barChart1").get(0).getContext("2d");
        var barChart1 = new Chart(barChartCanvas1);
        var barChartData1 = {
          labels: ["午餐均值","晚餐均值","总消费均值"],
          datasets: [
            {

              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: [<%=((Object[])list1.get(0))[0].toString()%>,<%=((Object[])list1.get(0))[1].toString()%>,<%=((Object[])list1.get(0))[2].toString()%>]
            }
          ]
        };
        //barChartData.datasets[0].fillColor = "#00a65a";
       // barChartData.datasets[0].strokeColor = "#00a65a";
       // barChartData.datasets[0].pointColor = "#00a65a";
        var barChartOptions = {

          //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
          scaleBeginAtZero: true,
          //Boolean - Whether grid lines are shown across the chart
          scaleShowGridLines: true,
          //String - Colour of the grid lines
          scaleGridLineColor: "rgba(0,0,0,.05)",
          //Number - Width of the grid lines
          scaleGridLineWidth: 1,
          //Boolean - Whether to show horizontal lines (except X axis)
          scaleShowHorizontalLines: true,
          //Boolean - Whether to show vertical lines (except Y axis)
          scaleShowVerticalLines: true,
          //Boolean - If there is a stroke on each bar
          barShowStroke: true,
          //Number - Pixel width of the bar stroke
          barStrokeWidth: 2,
          //Number - Spacing between each of the X value sets
          barValueSpacing: 5,
          //Number - Spacing between data sets within X values
          barDatasetSpacing: 1,
      
          //Boolean - whether to make the chart responsive
          responsive: true,
          maintainAspectRatio: true
        };

        barChartOptions.datasetFill = false;
        barChart1.Bar(barChartData1, barChartOptions);
		
        var barChartCanvas2 = $("#barChart2").get(0).getContext("2d");
        var barChart2 = new Chart(barChartCanvas2);
        var barChartData2 = {
          labels: ["午餐次数","晚餐次数","总消费次数"],
          datasets: [
            {
              label: "Electronics",
              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: [<%=((Object[])list1.get(0))[3].toString()%>,<%=((Object[])list1.get(0))[4].toString()%>,<%=((Object[])list1.get(0))[5].toString()%>]
            }
          ]
        };
        barChartOptions.datasetFill = false;
        barChart2.Bar(barChartData2, barChartOptions);
        
        var barChartCanvas3 = $("#barChart3").get(0).getContext("2d");
        var barChart3 = new Chart(barChartCanvas3);
        var barChartData3 = {
          labels: ["午餐均值","晚餐均值","总消费均值"],
          datasets: [
            {
              label: "男",
              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: [<%=((Object[])list2_1.get(0))[0].toString()%>,<%=((Object[])list2_1.get(0))[1].toString()%>,<%=((Object[])list2_1.get(0))[2].toString()%>]
            },
            {
            	label: "女",
                fillColor: "rgba(210, 214, 222, 1)",
                strokeColor: "rgba(210, 214, 222, 1)",
                pointColor: "rgba(210, 214, 222, 1)",
                pointStrokeColor: "#c1c7d1",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [<%=((Object[])list2_2.get(0))[0].toString()%>,<%=((Object[])list2_2.get(0))[1].toString()%>,<%=((Object[])list2_2.get(0))[2].toString()%>]
              }
          ]
        };
        barChartData3.datasets[1].fillColor = "#00a65a";
        barChartData3.datasets[1].strokeColor = "#00a65a";
        barChartData3.datasets[1].pointColor = "#00a65a";
        barChartOptions.datasetFill = false;
        barChart3.Bar(barChartData3, barChartOptions);

      });
    </script>
    <%}} %>
  </body>
</html>


