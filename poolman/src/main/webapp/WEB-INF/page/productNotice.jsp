<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="baseGuldanCss.jsp"%>
  <title>产品介绍</title>
  <link href="<%=guldanPath%>/bower_components/bootstrap-social/bootstrap-social.css" rel="stylesheet">

</head>
<body>

<div id="wrapper">
    <%@ include file="menu_left.jsp"%>
    <div id="page-wrapper">
    <form id="addForm" method="post">
        <div class="row">
            <h2 class="page-header">产品介绍
                <input type="hidden" name="adminId" id="adminId" value="${adminUser.id}"/>
		        <input type="hidden" name="id" id="id" value="${softwareInfomation['id']}"/>
                <input type="hidden" name="flag" id="flag" value="3"/>
                <input type="hidden" name="filePath" value="${softwareInfomation['filePath']}"/>
                <input type="hidden" name="mkType" id="mkType" value="4" />
            </h2>
        </div>
        <div class="row">
            <div class="col-lg-8">
        <div class="panel panel-default">
          <div class="panel-heading">
            <i class="fa fa-edit fa-fw"></i> 产品介绍
           </div>
           <div class="panel-body">
              <div id="epiceditor" style="height: 500px"><textarea id="content" name="content" ></textarea></div>
              <p></p>
              <button type="button" class="btn btn-outline btn-primary btn-lg btn-block" name="updateUseExplanationOne" id="updateUseExplanationOne">更新</button>
           </div>
        </div>
       </div>

            <div class="col-lg-4">
            <div class=" panel panel-default">
                <div class="panel-heading">
                    <i class="fa  fa-info-circle fa-fw"></i> images
                </div>
                <ul class="chat" id="imgUpload">
                    <li class="left clearfix" id="imgUP">
                            <span class="chat-img pull-left">
                                <label class="btn btn-outline btn-success btn-file">
                                    上传图片
                                    <input  style="display: none;" class="inputstyle" id="findFile" type="file" name="file" onchange="ajaxFileUpload(this)"/>
                                </label>
                                <button type="button" class="btn btn-outline btn-info btn-file" name="copyFile" id="copyFile" onclick="copyAllUrlToClipboard(this)">复制所有图片链接</button>
                            </span>
                    </li>
                </ul><!--image-->
            </div>
        </div>
        </div>
    </form>
    </div>
</div>

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