<%@page import="com.sys.card.bean.*" import="com.sys.card.daoImpl.*" import="com.wiscom.is.*, java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" import="java.util.List" import="java.util.Iterator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
  <head>
  
<%
   String result=(String)request.getAttribute("result");
   StudentPoint studentPoint=(StudentPoint)request.getAttribute("studentPoint");
   List list=(List)request.getAttribute("list");
   String sname=(String)request.getAttribute("sname");
   String accessFlag=(String)request.getAttribute("accessFlag");
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
	<!-- Select2 选择框输入-->
    <link rel="stylesheet" href="plugins/select2/select2.min.css">
	<!-- Theme style 网站构造-->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css"> 
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
	<!-- DataTables -->
    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
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
            
            <li class="treeview active">
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
            <li class="treeview">
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
				<small>消费记录</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="index.jsp" ><i class="fa fa-dashboard"></i> 主页</a></li>
			<li>消费记录</li>
			<li class="active">按学生查询</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Main row -->
          <div class="row">
			
			<!-- SELECT2 EXAMPLE -->
			  <div class="box box-default">
				<div class="box-header with-border">
				  <h3 class="box-title">按学生查询</h3>
				  <div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
				  </div>
				</div><!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal" action="SearchByPerson" method="post">
						<div class="row">
							<div class="col-md-6">
							    <div class="form-group">
								  <label for="studentID" class="col-sm-2 control-label">学号</label>
								  <div class="col-sm-10">
									<input type="text" class="form-control" id="studentID" name="sno" placeholder="输入学生学号">
								  </div>
								</div><!-- /.form-group -->
							</div><!-- /.col -->
							
							<div class="col-md-2 col-md-offset-2">
							  <button type="submit" class="btn btn-primary btn-block btn-flat">查询</button>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</form>
				</div><!-- /.box-body -->
			  </div><!-- /.box -->
			  
			  <%if(result!=null){ %>
			  <div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">查询结果</h3>
				</div><!-- /.box-header -->
				<%if(studentPoint!=null){ %>
				    <%if(accessFlag.equals("1")){ %>
				<div class="box-body">
				  <table id="result" class="table table-bordered table-hover">
					<thead>
					  <tr>
						<th>学号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>去年得过助学金</th>
						<th>总值</th>
						<th>用餐次数</th>
						<th>每餐均值</th>
						<th>午餐总值</th>
						<th>午餐次数</th>
						<th>午餐均值</th>
						<th>晚餐总值</th>
						<th>晚餐次数</th>
						<th>晚餐均值</th>
					  </tr>
					</thead>
					<tbody>
						<tr>
							<td><%=studentPoint.getSno() %></td>
							<td><%=sname %></td>
							<td><%=studentPoint.getGender() %></td>
							<td><%=studentPoint.getHasScholarship() %></td>
						    <td><%=studentPoint.getTotal() %></td>
							<td><%=studentPoint.getCount() %></td>
							<td><%=studentPoint.getAverage() %></td>
							<td><%=studentPoint.getLunchTTL() %></td>
							<td><%=studentPoint.getLunchCNT() %></td>
							<td><%=studentPoint.getLunchAVG() %></td>
							<td><%=studentPoint.getSupperTTL() %></td>
							<td><%=studentPoint.getSupperCNT() %></td>
							<td><%=studentPoint.getSupperAVG() %></td>					
						</tr>
					</tbody>
				  </table>
				</div><!-- /.box-body -->
				  <%}else{ %><h4><b>&nbsp;该学生不在访问权限内！</b></h4><%} %>
				<%}else{ %><h4><b>&nbsp;无该项数据或学号不存在！</b></h4><%} %>
				
			  </div><!-- /.box -->
			  <%if(studentPoint!=null && accessFlag.equals("1")){ %>
			  <div class="col-md-6">
				 <!-- DONUT CHART -->
				  <div class="box box-info">
				    <div class="box-header with-border">
					 <h3 class="box-title">各餐消费比例饼状图(单位:元)</h3>
					 <div class="pull-right">				    
						<i class="fa fa-square" style="color:#f56954"></i>午餐
						<i class="fa fa-square" style="color:#00a65a"></i>晚餐
					  </div>
					</div>
					  
					<div class="box-body">
						<canvas id="pieChart" style="height:250px"></canvas>
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			  </div>
			  <div class="col-md-6">
				<!-- BAR CHART -->
				  <div class="box box-info">
					<div class="box-header with-border">
					  <h3 class="box-title">学生消费统计柱状图(单位:元)</h3>
					  <div class="pull-right">				    
						<i class="fa fa-square" style="color:rgba(60,141,188,0.9)"></i>个人消费水平
						<i class="fa fa-square" style="color:#00a65a"></i>全校平均水平
					  </div>
					</div>
					  
					<div class="box-body">
					  <div class="chart">
						<canvas id="barChart" style="height:250px"></canvas>
					  </div>
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			  </div>
			  <%} %>
			  <%} %>
          </div><!-- /.row (main row) -->
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
	 <!-- Select2 -->
    <script src="plugins/select2/select2.full.min.js"></script>
    <!-- FastClick 
    从点击屏幕上的元素到触发元素的 click 事件，移动浏览器会有大约 300 毫秒的等待时间。
    它想看看你是不是要进行双击（double tap）操作。-->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>
	<!-- DataTables -->
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
	<!-- my js -->
    <script src="dist/js/my.js"></script>
	
	<!-- Page script -->
	<%if(result!=null && studentPoint!=null){ %>
    <script>
     $(function () {        
    	 //Initialize Select2 Elements
        $(".select2").select2();
		
		$("#result").DataTable({
		  "bSort": false,
		  "bPaginage":false,
		  "sInfo":"",
          "lengthChange": false,
          "searching": false,
          "autoWidth": false,
          "oLanguage": {
  			"sLengthMenu": "每页显示 _MENU_ 条记录",
  			"sZeroRecords": "没有匹配结果",
  			"sInfo": "显示第_START_至_END_项结果，共_TOTAL_项",
  			"sInfoEmpty": "没有数据",
  			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
  			"sSearch": "搜索：",
  			"oPaginate": {
  			"sFirst": "<<",
  			"sPrevious": "<",
  			"sNext": ">",
  			"sLast": ">>"
  			}
  			}
		});
		
		var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
        var pieChart = new Chart(pieChartCanvas);     
        var PieData = [
          {
            value: <%=studentPoint.getLunchTTL() %>,
            color: "#f56954",
            highlight: "#f56954",
            label: "午餐"
          },
          {
            value: <%=studentPoint.getSupperTTL() %>,
            color: "#00a65a",
            highlight: "#00a65a",
            label: "晚餐"
          }
        ];
        var pieOptions = {
          //Boolean - Whether we should show a stroke on each segment
          segmentShowStroke: false,
          //Number - Amount of animation steps
          animationSteps: 10,
          //String - Animation easing effect
          animationEasing: "easeOutBounce",
          //Boolean - Whether we animate the rotation of the Doughnut
          animateRotate: true,
          //Boolean - Whether we animate scaling the Doughnut from the centre
          animateScale: false,
          //Boolean - whether to make the chart responsive to window resizing
          responsive: true,
          // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
          maintainAspectRatio: true,
		  scaleShowLabels: true
        };
        //Create pie or douhnut chart
        // You can switch between pie and douhnut using the method below.
        pieChart.Pie(PieData, pieOptions);
		
		//-------------
        //- BAR CHART -
        //-------------
        var barChartCanvas = $("#barChart").get(0).getContext("2d");
        var barChart = new Chart(barChartCanvas);
        var barChartData = {
          labels: ["总平均值","午餐平均值","晚餐平均值"],
          datasets: [
            {
              label: "Electronics",
              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: [<%=studentPoint.getAverage()%>,<%=studentPoint.getLunchAVG()%>,<%=studentPoint.getSupperAVG()%>]
            },
            {
              label: "Digital Goods",
              fillColor: "rgba(210, 214, 222, 1)",
              strokeColor: "rgba(210, 214, 222, 1)",
              pointColor: "rgba(210, 214, 222, 1)",
              pointStrokeColor: "#c1c7d1",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(220,220,220,1)",
              data: [<%=((Object[])list.get(0))[0].toString()%>,<%=((Object[])list.get(0))[1].toString()%>,<%=((Object[])list.get(0))[2].toString()%>]
            }
          ]
        };
        barChartData.datasets[1].fillColor = "#00a65a";
        barChartData.datasets[1].strokeColor = "#00a65a";
        barChartData.datasets[1].pointColor = "#00a65a";
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
        barChart.Bar(barChartData, barChartOptions);
		

      });
    </script>
    <%}%>
  </body>
</html>
