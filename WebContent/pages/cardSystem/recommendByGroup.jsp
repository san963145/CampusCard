<%@page import="com.sys.card.bean.*" import="com.sys.card.daoImpl.*" import="com.wiscom.is.*, java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" import="java.util.List" import="java.util.Iterator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
  <head>
 
<%
  User user=(User)session.getAttribute("user");
  ArrayList<String[]>result=(ArrayList<String[]>)request.getAttribute("result");
  String department=(String)request.getAttribute("department");
  String major=(String)request.getAttribute("major");
  String grade=(String)request.getAttribute("grade");
  List<RatingResult> resultList=(List<RatingResult>)request.getAttribute("resultList");
  List<String>departmentList=(List<String>)new DepartmentPointDaoImpl().getDepartmentsByUser(user);

  String[] grades=null;
  if(user!=null){
  if(user.getAuthority().equals("Instructor"))
  {
    grades=user.getGrade().split("#");
  } 
  }
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
    
    <script src="pages/js/selector.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body class="skin-blue-light sidebar-mini" onload="init()">
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
            <li class="treeview">
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
			
            <li class="treeview active">
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
				<small>助学金推荐</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="index.jsp" ><i class="fa fa-dashboard"></i> 主页</a></li>
			<li>助学金推荐</li>
			<li class="active">按年级统计计算</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Main row -->
          <div class="row">
			
			<div class="col-md-12">
			<!-- SELECT2 EXAMPLE -->
			  <div class="box box-info">
				<div class="box-header with-border">
				  <h3 class="box-title">按年级统计计算</h3>
				  <div class="box-tools pull-right">
					<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
				  </div>
				</div><!-- /.box-header -->
				<div class="box-body">
					<form action="GrantRecommend" method="post" onsubmit="return check()">
						<div class="row">
							<div class="col-md-3">
							  <div class="form-group">
								<label>学院</label>
								<select class="form-control" id="departments" name="department" style="width: 100%;" onchange="sendDepartment(this.value)">
								   <%if(department==null){ %>
								   <option selected="selected">选择学院</option>
								   <%}else{ %>
								   <option selected="selected"><%=department %></option>
								   <%} %>
								   
								</select>
							  </div><!-- /.form-group -->
							</div><!-- /.col -->
							
							<div class="col-md-3">
							  <div class="form-group">
								<label>专业</label>
								<select class="form-control" id="majors" name="major" style="width: 100%;">	
								   <%if(major==null){ %>
								   <option selected="selected">选择专业</option>
								   <%}else{ %>
								   <option selected="selected"><%=major %></option>
								   <%} %>
								   
								</select>
							  </div><!-- /.form-group -->
							</div><!-- /.col -->
							
							<div class="col-md-3">
							  <div class="form-group">
								<label>年级</label>
								<select class="form-control" id="grades" name="grade" style="width: 100%;">	
								   <%if(grade==null){ %>
								   <option selected="selected">选择年级</option>
								   <%}else{ %>
								   <option selected="selected"><%=grade %></option>
								   <%} %>
								   	
								</select>
							  </div><!-- /.form-group -->
							</div><!-- /.col -->
							<div class="col-md-2 col-md-offset-1">
							  <label>&nbsp;</label>
							  <button type="submit" class="btn btn-primary btn-block btn-flat">查询</button>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</form>
				</div><!-- /.box-body -->
			  </div><!-- /.box -->
			</div>
			
			<%if(result!=null){if(result.size()>0){ %>
			<div class="col-md-12">
				 <!-- DONUT CHART -->
				  <div class="box box-info">
					<div class="box-header with-border">
					  <%if(department!=null && grade!=null && major!=null){ %>
					  <h3 class="box-title"><%=department %><%=grade %>级 <%=major %>专业 助学金推荐等级划分标准</h3>
					  <%}else{ %>
					  <h3 class="box-title">助学金推荐等级划分结果</h3>
					  <%} %>
					</div>
					<div class="box-body">
					<%if(result!=null){ %>
					<table id="result" class="table table-bordered table-hover">
						<thead>
						  <tr>
							<th>消费评分</th>
							<th>推荐等级</th>
							<th>人数比例</th>
						  </tr>
						</thead>
						<tbody>
						    <%Iterator it=result.iterator();
						      while(it.hasNext()){
						      String[] s=(String[])it.next();%>
							<tr>
								<td><%=s[0] %></td>
								<%if(s[1].equals("1")) {%>
								<td><img src="img/1.png"/></td>
								<%} %>
								<%if(s[1].equals("2")) {%>
								<td><img src="img/2.png"/></td>
								<%} %>
								<%if(s[1].equals("3")) {%>
								<td><img src="img/3.png"/></td>
								<%} %>
								<%if(s[1].equals("4")) {%>
								<td><img src="img/4.png"/></td>
								<%} %>
								<td><%=s[2] %></td>
						    </tr>
						    <%} %>
				        </tbody>	
				    </table>			
				    <%} %>	
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			  </div>
			  <div class="col-md-12">
				<!-- BAR CHART -->
				  <div class="box box-info">
					<div class="box-header with-border">
					  <%if(department!=null && grade!=null && major!=null){ %>
					  <h3 class="box-title"><%=department %><%=grade %>级 <%=major %>专业 学生助学金推荐结果</h3>
					  <%}else{ %>
					  <h3 class="box-title">助学金推荐结果</h3>
					  <%} %>
					</div>
					<div class="box-body">
					    <table id="result2" class="table table-bordered table-hover">
						<thead>
						  <tr>
							<th>学号</th>
							<th>姓名</th>
							<th>学院</th>
							<th>专业</th>
							<th>年级</th>
							<th>推荐等级</th>
							<th>备注</th>
						  </tr>
						</thead>
						<tbody>
						    
						    <%Iterator it=resultList.iterator();
						      while(it.hasNext()){
						    	  RatingResult r=(RatingResult)it.next();%>
							<tr>
								<td><a href="SearchByPerson?sno=<%=r.getSno().trim()%>"><%=r.getSno()%></a></td>
								<td><%=r.getSname()%></td>
								<td><%=r.getDepartment()%></td>
						        <td><%=r.getMajor()%></td>
								<td><%=r.getGrade()%></td>
								<%if(r.getPoint().equals("1")) {%>
								<td><img src="img/1.png"/></td>
								<%} %>
								<%if(r.getPoint().equals("2")) {%>
								<td><img src="img/2.png"/></td>
								<%} %>
								<%if(r.getPoint().equals("3")) {%>
								<td><img src="img/3.png"/></td>
								<%} %>
								<%if(r.getPoint().equals("4")) {%>
								<td><img src="img/4.png"/></td>
								<%} %>
								<td><%=r.getInfo()%></td>
						    </tr>
						    <%}%>

						</tbody>
                  </table>				
					</div><!-- /.box-body -->
				  </div><!-- /.box -->
			  </div>
			  <%}else{%>
			<h4><b>&nbsp;&nbsp;&nbsp;不存在该项数据！</b></h4>
			<% } }%>
			  
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
	<!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
    <!-- DataTables -->
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
    
	<!-- my js -->
    <script src="dist/js/my.js"></script>
	<!-- Page script -->
    <script>
     $(function () {
        //Initialize Select2 Elements
        $(".select2").select2();
        
        $("#result2").DataTable({
			"aLengthMenu": [[5,10,20,-1], [5, 10, 20, 50, "All"]],
			"bStateSave": false,
			"bLengthChange":true,
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "没有匹配结果",
				"sInfo": "第_START_至_END_项，共_TOTAL_项",
				"sInfoEmpty": "没有数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"sSearch": "搜索：",
				"oPaginate": ""
				}
			
		} );
      });
    </script>
 
  </body>
</html>


