<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>后台管理系统</title>
	<%@ include file="baseGuldanCss.jsp" %>
    <link href="<%=guldanPath%>/dist/css/timeline.css" rel="stylesheet">
</head>
<body>


<div id="wrapper">

    <%@ include file="menu_left.jsp" %>

    <div id="page-wrapper">
		<div class="row">
		
		</div>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Tip：</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-group fa-2x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div id="userCount" class="huge"></div>
                                <div>All Users!</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-right">正义的伙伴 <i class="fa  fa-twitter"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-stumbleupon-circle fa-2x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div id="dishCount" class="huge"></div>
                                <div>All Dish!</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-right">正义的伙伴 <i class="fa  fa-twitter"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-yellow">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-files-o fa-2x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div id="businessCount" class="huge"></div>
                                <div>All Business!</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-right">正义的伙伴 <i class="fa  fa-twitter"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-search fa-2x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div id="systemCount" class="huge"></div>
                                <div>All Admin!</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-right">正义的伙伴 <i class="fa  fa-twitter"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Info</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-info-circle fa-fw"></i>外部资源
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <ul class="timeline">
                        <li>
                            <div class="timeline-badge">U
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h4 class="timeline-title"><a
                                            href="http://mobile.umeng.com/apps?spm=0.0.0.0.EYbyTj">Umeng数据平台</a></h4>
                                    <p>
                                        <small class="text-muted"><i class="fa  fa-envelope-o"></i> sanguojwh@163.com
                                        </small>
                                    </p>
                                </div>
                                <div class="timeline-body">
                                    <p>umeng数据平台，查看APP用户行数据</p>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <!-- /.panel-body -->
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!--  count  -->
<script type="text/javascript">
	$.ajax({
        type: "post",
        url: "<%=basePath%>/login/tipCount.htm",
        dataType:'json',
        success:function (data) {
        	console.log(data.result);
        	$("#userCount").html(data.userCount);
        	$("#dishCount").html(data.dishCount);
        	$("#businessCount").html(data.businessCount);
        	$("#systemCount").html(data.systemCount);
        },
    });
</script>

<!-- jQuery -->
<script src="<%=guldanPath%>/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=guldanPath%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=guldanPath%>/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="<%=guldanPath%>/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=guldanPath%>/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=guldanPath%>/dist/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function () {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>

</body>

</html>
