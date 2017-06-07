<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="baseGuldanCss.jsp"%>
  <title>用户需知</title>
  <link href="<%=guldanPath%>/bower_components/bootstrap-social/bootstrap-social.css" rel="stylesheet">

</head>
<body>

<div id="wrapper">
  <%@ include file="menu_left.jsp"%>
  <div id="page-wrapper">
  <form id="addForm" method="post">
        <div class="row">
            <h2 class="page-header">用户须知
                <input type="hidden" name="adminId" id="adminId" value="${adminUser.id}"/>
		        <input type="hidden" name="id" id="id" value="${softwareInfomation['id']}"/>
		        <input type="hidden" name="flag" id="flag" value="1"/>
		        <input type="hidden" name="filePath" value="${softwareInfomation['filePath']}"/>
		        <input type="hidden" name="mkType" id="mkType" value="2" />
            </h2>
        </div>
    <div class="row">
      <div class="col-lg-8">
        <div class="panel panel-default">
          <div class="panel-heading">
            <i class="fa fa-edit fa-fw"></i> 用户须知
           </div>
           <div class="panel-body">
              <div id="epiceditor" style="height: 500px"><textarea id="content" name="content" ></textarea></div>
              <p></p>
              <button type="button" class="btn btn-outline btn-primary btn-lg btn-block" name="updateUserNotice" id="updateUserNotice">更新</button>                               
           </div>
        </div>
       </div>
    </div>
</form>
</div></div>

<script src="<%=guldanPath%>/EpicEditor/epiceditor/js/epiceditor.js">
  var editor = new EpicEditor().load();
</script>

<script type="text/javascript">
	
  
  
</script>

<script src="<%=guldanPath%>/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=guldanPath%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=guldanPath%>/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script src="<%=guldanPath%>/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=guldanPath%>/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<script src="<%=guldanPath%>/dist/js/sb-admin-2.js"></script>
<script src="<%=guldanPath%>/Editor-1.5.6/js/dataTables.editor.min.js"></script>
<script src="<%=guldanPath%>/Buttons-1.2.1/js/dataTables.buttons.min.js"></script>
<script src="<%=guldanPath%>/Select-1.2.0/js/dataTables.select.min.js"></script>
<script src="<%=guldanPath%>/jquery-confirm/dist/jquery-confirm.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/ajaxfileupload.js"></script>


<script src="<%=guldanPath%>/EpicEditor/js/commands.js"></script>
<script src="<%=guldanPath%>/EpicEditor/js/toolbar.js"></script>
<script src="<%=guldanPath%>/EpicEditor/js/main.js"></script>


</body>
</html>