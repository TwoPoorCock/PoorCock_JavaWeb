<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <span class="navbar-brand">后台管理</span>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">


        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li class="divider"></li>
                <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a href="<%=basePath%>/admin/main.htm"><i class="fa fa-dashboard fa-fw"></i> 主页</a>
                </li>
                <li>
                    <a href="<%=basePath%>/admin/toGetAllUsersPage.htm"><i class="fa fa-user fa-fw"></i> 用户管理</a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-coffee fa-fw"></i>运营管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=basePath%>/business/toGetAllBusinessPage.htm">商家管理</a>
                        </li>
                        <%-- <li>
                            <a href="<%=basePath%>/admin/event/toGetAllEventsPage.htm">菜单管理</a>
                        </li> --%>
                        <li>
                            <a href="<%=basePath%>/dish/toGetAllDishPage.htm">菜品管理</a>
                        </li>

                    </ul>
                    <!-- /.nav-second-level -->
                </li>

                <li>
                    <a href="#"><i class="fa fa-github-alt fa-fw"></i> 系统管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=basePath%>/user/toGetAllAdminsPage.htm ">系统用户管理</a>
                        </li>
                    </ul>
                </li>

                <li>
                  <a href="<%=basePath%>/school/toGetAllSystemMenuPage.htm"><i class="fa fa-sitemap fa-fw"></i>校园菜单管理</a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-mobile-phone fa-fw"></i> 移动端基础设置<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=basePath%>/admin/toUserNotice.htm ">用户须知</a>
                        </li>
                        <li>
                            <a href="<%=basePath%>/admin/toUseExplanationOne.htm ">产品介绍</a>
                        </li>
                        
                    </ul>
                    <!-- /.nav-second-level -->
                </li>

            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>
