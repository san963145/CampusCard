<%@page import="com.sys.card.bean.*" import="com.sys.card.daoImpl.*" import="com.wiscom.is.*, java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
  <head>
  
<%
   
   StudentPoint student=(StudentPoint)request.getAttribute("studentPoint");
   String sData=(String)request.getAttribute("sData");
   String gData=(String)request.getAttribute("gData");
   String month=(String)request.getAttribute("monthData");
   String name=(String)request.getAttribute("sname");
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
            <li class="treeview  active">
              <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>统计分析</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
               
				<c:if test="${sessionScope.authority=='Admin'}">
				<li class="admin"><a href="DrawComparison"><i class="fa fa-circle-o"></i> 学院对比</a></li>
				 </c:if>
				<c:if test="${sessionScope.authority=='Dean'}">
				<li class="dean"><a href="DrawComparison"><i class="fa fa-circle-o"></i> 年级对比</a></li>
                </c:if>
                <li class="admin dean instruct"><a href="pages/statistics/heatMap.jsp"><i class="fa fa-circle-o"></i> 用餐时间</a></li>
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
					<form class="form-horizontal" action="HeatMap" method="post">
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
			  
			  
			<c:if test="${not empty result}">
			    <div class="box box-info">
				  <div class="box-header with-border">
				    <h3 class="box-title">学生基本信息</h3>
				  </div><!-- /.box-header -->
				<c:choose>
				   <c:when test="${not empty studentPoint}">  <!-- 数据 -->
				      <c:choose>
				           <c:when test="${accessFlag=='1'}">    <!-- 权限-->
				           
				           
										<div class="box-body">
											<table id="result" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>学号</th>
														<th>姓名</th>
														<th>性别</th>
														<th>学院</th>
														<th>年级</th>
														<th>专业</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><%=student.getSno()%></td>
														<td><%=name%></td>
														<td><%=student.getGender()%></td>
														<td>${department}</td>
														<td>${grade}</td>
														<td>${major}</td>

														</tr>
												</tbody>
											</table>
										</div>
										<!-- /.box-body -->

							</c:when>
				        <c:otherwise>
				            <h4><b>&nbsp;该学生不在访问权限内！</b></h4>
				        </c:otherwise>
				      </c:choose>
				   </c:when>
				   <c:otherwise>
				       <h4><b>&nbsp;无该项数据或学号不存在！</b></h4>
				   </c:otherwise>

				</c:choose>
				</div><!-- /.box -->
			</c:if>
				
			  

 
             <c:if test="${not empty studentPoint && not empty accessFlag}">
            
			  <div class="col-md-6">
	
				  <div class="box box-info">
					<div class="box-header with-border">
					  <h3 class="box-title">${sname} 用餐次数热力分布图</h3>
					 
					</div>
					  
					<div class="box-body" id="heatMap1" style="height:400px">
					 
					</div>
				  </div>
			  </div>
			  <div class="col-md-6">
  
				  <div class="box box-info">
					<div class="box-header with-border">
					  <h3 class="box-title">学院平均 用餐次数热力分布图</h3>
					  
					</div>
					  
					<div class="box-body" id="heatMap2" style="height:400px">
					 
					</div>
				  </div>
			  </div>
		
			 
			  
			 
			
              </c:if>


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
    
    <script src="dist/js/echarts-all-3.js"></script>
	<!-- my js -->
    <script src="dist/js/my.js"></script>
	
	<!-- Page script -->
	
   <c:if test="${not empty monthData}"> 
    <script>
    var dom = document.getElementById("heatMap1");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
            '7a', '8a', '9a','10a','11a',
            '12p', '1p', '2p', '3p', '4p', '5p',
            '6p', '7p', '8p', '9p', '10p', '11p'];
    var months = [<%=month%>];

    var data = [<%=sData%>];

    data = data.map(function (item) {
        return [item[1], item[0], item[2] || '-'];
    });

    option = {
        tooltip: {
            position: 'top'
        },
        animation: false,
        grid: {
            height: '50%',
            y: '10%'
        },
        xAxis: {
            type: 'category',
            data: hours
        },
        yAxis: {
            type: 'category',
            data: months
        },
        visualMap: {
            min: 1,
            max: 15,
            calculable: true,
            orient: 'horizontal',
            left: 'center',
            bottom: '15%'
        },
        series: [{
            name: '月累计用餐次数',
            type: 'heatmap',
            data: data,
            label: {
                normal: {
                    show: true
                }
            },
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    myChart.setOption(option, true);
    
    </script>
    </c:if>
    <c:if test="${not empty monthData}"> 
    <script>
    var dom2 = document.getElementById("heatMap2");
    var myChart2 = echarts.init(dom2);
    var app2 = {};
    option2 = null;
    var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
            '7a', '8a', '9a','10a','11a',
            '12p', '1p', '2p', '3p', '4p', '5p',
            '6p', '7p', '8p', '9p', '10p', '11p'];
    var months = [<%=month%>];

    var data2 = [<%=gData%>];

    data2 = data2.map(function (item) {
        return [item[1], item[0], item[2] || '-'];
    });

    option2 = {
        tooltip: {
            position: 'top'
        },
        animation: false,
        grid: {
            height: '50%',
            y: '10%'
        },
        xAxis: {
            type: 'category',
            data: hours
        },
        yAxis: {
            type: 'category',
            data: months
        },
        visualMap: {
            min: 1,
            max: 15,
            calculable: true,
            orient: 'horizontal',
            left: 'center',
            bottom: '15%'
        },
        series: [{
            name: '月累计用餐次数',
            type: 'heatmap',
            data: data2,
            label: {
                normal: {
                    show: true
                }
            },
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    myChart2.setOption(option2, true);
    
    </script>
    </c:if>
    
  </body>
</html>
