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
  List<String>departmentList=(List<String>)new DepartmentPointDaoImpl().getDepartments();
  User user=(User)session.getAttribute("user");
%>


  
    <base href="${sessionScope.basePath }">
    <meta charset="UTF-8">
    <title>用户及系统管理</title>
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
  <%if(user!=null){if(user.getAuthority().equals("Dean")){%>
  <script>
  function check()
	{
		var userId=document.getElementById("inputUserId").value;
		var department=document.getElementById("inputCollege").value;		
		var grade=document.getElementById("inputGrade").value;		
		var authority=document.getElementById("inputAuthority").value;
		if(userId=="")
	    {
			alert("请输入账号!");
			return false;
	    }
		else if(department=="选择学院")
		{
			alert("请选择学院!");
			return false;
		}
		else if(grade=="选择年级")
		{
			alert("请选择年级!");
			return false;
		}
		else if(authority=="选择权限")
		{
			alert("请选择权限!");
			return false;
		}
		else
		{
			return true;
		}
	}
  </script>
  <%}}%>
  
  <%if(user!=null){if(user.getAuthority().equals("Admin")){%>
  <script>
  function check()
	{
		var userId=document.getElementById("inputUserId").value;
		var department=document.getElementById("inputCollege").value;				
		var authority=document.getElementById("inputAuthority").value;
		if(userId=="")
	    {
			alert("请输入账号!");
			return false;
	    }
		else if(department=="选择学院")
		{
			alert("请选择学院!");
			return false;
		}
		else if(authority=="选择权限")
		{
			alert("请选择权限!");
			return false;
		}
		else
		{
			return true;
		}
	}
  </script>
  <%}}%>
  
  <body class="admin dean skin-blue-light sidebar-mini">
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
            <li class="treeview">
              <a href="#">
                <i class="fa fa-signal"></i> <span>助学金推荐指数</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/cardSystem/recommendByGroup.jsp"><i class="fa fa-circle-o"></i> 按年级查询</a></li>
                <li><a href="pages/cardSystem/recommendByPerson.jsp"><i class="fa fa-circle-o"></i> 按学生查询</a></li>
              </ul>
            </li>
            
            <li class="treeview active">
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
				<small>添加用户</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="index.jsp" ><i class="fa fa-dashboard"></i> 主页</a></li>
			<li class="active"> 添加用户</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Main row -->
          <div class="row">
			
			<!-- Horizontal Form -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">添加用户</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="AddUser" method="post" onsubmit="return check()">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="inputAdmin" class="col-sm-2 col-md-2 col-md-offset-1 control-label">账号：</label>
                      <div class="col-sm-10 col-md-6">
                        <input type="text" class="form-control" id="inputUserId" name="userId">
                      </div>
                    </div>
					<div class="form-group">
						<label for="inputCollege" class="col-sm-2 col-md-2 col-md-offset-1 control-label">学院：</label>
						<div class="col-sm-10 col-md-6">
							<select class="form-control" id="inputCollege" name="department">
							  <option selected="selected">选择学院</option>
							  <%if(user!=null){if(user.getAuthority().equals("Admin")){ %>
							  <%for(int i=0;i<departmentList.size();i++){%>
							  <option><%=departmentList.get(i) %></option>
							  <%} %>
							  <%}else{ %>
							  <option><%=user.getDepartment() %></option>
							  <%}} %>
							</select>
						</div>
					</div><!-- /.form-group -->
					
					<div class="form-group dean">
						<label for="inputCollege" class="col-sm-2 col-md-2 col-md-offset-1 control-label">年级：</label>
						<div class="col-sm-10 col-md-6">
							<select class="form-control" id="inputGrade" name="grade">
							  <option selected="selected">选择年级</option>
							  <%if(user!=null){if(user.getAuthority().equals("Dean")){
								  List gradeList=new StudentInfoDaoImpl().getGradesByDepartment(user.getDepartment());
							   %>
							     <%for(int i=0;i<gradeList.size();i++){%>
							       <%if(!gradeList.get(i).toString().equals("0")){%>
							       <option><%=gradeList.get(i).toString() %></option>
							       <%} %>
							     <%} %>
							  <%} }%>
							  
							</select>
						</div>
					</div><!-- /.form-group -->
					
					<div class="form-group">
						<label for="inputAuthority" class="col-sm-2 col-md-2 col-md-offset-1 control-label">身份：</label>
						<div class="col-sm-10 col-md-6">
							<select class="form-control" id="inputAuthority" name="authority">
							 <option>选择权限</option>
							 <option class="dean">辅导员</option>
							 <option class="admin">院长</option>
							</select>
						</div>
					</div><!-- /.form-group -->
                    
                    <div class="form-group">
                      <label for="inputComments" class="col-sm-2 col-md-2 col-md-offset-1 control-label">备注：</label>
                      <div class="col-sm-10 col-md-6">
                        <input type="text" class="form-control" id="inputComments" name="comments">
                      </div>
                    </div>
                    
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary pull-right">添加</button>
                  </div><!-- /.box-footer -->
                </form>
              </div><!-- /.box -->
			
          </div><!-- /.row (main row) -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.0
        </div>
		<p class="text-center">
        <strong>重庆大学学工部</strong>
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
	<!-- my js -->
    <script src="dist/js/my.js"></script>

  </body>
  <%if(result!=null){ %>
    <script>
    alert("添加成功!");
    </script>
  <%}%>
</html>


