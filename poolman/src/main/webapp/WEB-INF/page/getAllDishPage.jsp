<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="baseGuldanCss.jsp" %>
</head>

<body>
	<script type="text/javascript" src="<%=basePath%>resource/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/ajaxfileupload.js"></script>
    <div id="wrapper">
        <%@ include file="menu_left.jsp" %>
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">菜品列表</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 alert alert-success">
                        <span>Tips: 总共</span>
                        <span id="Count_User"></span>
                        <span>菜品</span>
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <i class="fa fa-list-alt"></i> 菜品列表
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-8">
                                        <span><button id="enableUser" class="btn btn-outline btn-success"> 启用 </button></span>
                                        <span><button id="disableUser" class="btn btn-outline btn-danger"> 停用 </button></span>
                                        <button class="btn btn-outline btn-info" id="addDish">批量导入</button>
                                        <form enctype="multipart/form-data" method="post">
                                            <input type="file" name="file" id="file" onchange="ajaxFileUpload(this)" style="display: none;" />
                                        </form>
                                    </div>
                                </div>
                                <hr>
                                <div class="dataTable_wrapper">
                                    <table id="dataTables" class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>编号</th>
                                                <th>菜名</th>
                                                <th>荤素程度</th>
                                                <th>辛辣程度</th>
                                                <th>状态</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
            </div>
    </div>
    <script type="text/javascript">
    $(document).ready(function() {

        var table = $('#dataTables').DataTable({
            bServerSide: true,
            searching: true,
            ajax: {
                url: 'dish/getAllDishPage.htm',
                dataSrc: 'rows'
            },
            "columns": [{
                "data": "id"
            }, {
                "data": "dishName"
            }, {
                "data": "flag_r"
            }, {
                "data": "flag_h"
            }, {
                "data": "type"
            }, ],

            "columnDefs": [{
                "name": "id",
                "targets": 0
            }, {
                "targets": 1,
                "name": "dishName",
                "render": function(data, type, row) {
                    return '<a href="' + '<%=basePath%>dish/toEditDishPage.htm?id=' + row.id + '">' + row.dishName + '</a>';
                }
            }, {
                "name": "flag_r",
                "targets": 2,
                "render": function(data, type, row) {
                    if (data == "0") {
                        return '<i>素</i> ';
                    }
                    if (data == "1") {
                        return '<i>一般</i> ';
                    }
                    if (data == "2") {
                        return '<i>荤</i> ';
                    }
                }
            }, {
                "name": "flag_h",
                "targets": 3,
                "render": function(data, type, row) {
                    if (data == "0") {
                        return '<i>不辣</i> ';
                    }
                    if (data == "1") {
                        return '<i>微辣</i> ';
                    }
                    if (data == "2") {
                        return '<i>辣</i> ';
                    }
                }
            }, {
                "name": "type",
                "targets": 4,
                "render": function(data, type, row) {
                    if (data == "1") {
                        return '<i class="fa fa-remove fa-fw"></i> ';
                    } else {
                        return '<i class="fa fa-thumbs-up fa-fw"></i> ';
                    }
                }
            }, {
                "defaultContent": "-",
                "targets": "_all"
            }],
            "fnDrawCallback": function(oSettings) {
                document.getElementById("Count_User").innerHTML = oSettings._iRecordsTotal;
            }

        });

        var num = 0;
        var arr = new Array(num);
        $('#dataTables tbody').on('click', 'tr', function() {
            if ($(this).hasClass('selected')) {
                arr.splice(arr.indexOf($(this)[0].cells[0].innerText), 1);
                $(this).removeClass('selected');
            } else {
                $(this).addClass('selected');
                arr.push($(this)[0].cells[0].innerText);
            }
        });

        function enableAndDisableStatus(title, status, id) {
            $.confirm({
                title: '注意：',
                content: "是否" + title,
                confirm: function() {
                    $.ajax({
                        url: "<%=basePath%>dish/batchEnableOrDisableDish.htm",
                        type: "post",
                        data: {
                            dishIds: id,
                            status: status
                        },
                        dataType: "json",
                        async: false,
                        success: function(data) {
                            //删除失败
                            if (data.status == '1') {
                                $.alert({
                                    title: title + '通知：',
                                    content: '\t\t\t----成功!',
                                    confirm: function() {
                                        table.draw(false);
                                    }
                                });
                            } else {
                                $.alert({
                                    title: title + '通知',
                                    content: '\t\t\t----失败!',
                                });
                            }
                        }
                    });
                },
                cancel: function() {
                    // do nothing
                }
            });
        }

        var isSelect = function() {
            if (table.$('tr.selected')[0] == null) {
                $.alert({
                    title: '通知：',
                    content: '\t\t\t----没有选择任何用户!'
                });
                return 0;
            }
            return 1;
        }
        $('#enableUser').click(function() {
            if (isSelect() == 0) {
                return;
            }
            var id = arr.join(',');
            arr = [];
            table.$('tr.selected').removeClass('selected');
            enableAndDisableStatus("启用", 1, id);
        });

        $('#disableUser').click(function() {
            if (isSelect() == 0) {
                return;
            }
            var id = arr.join(',');
            arr = [];
            table.$('tr.selected').removeClass('selected');
            enableAndDisableStatus("隐藏", 0, id);
        });

    });

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
</body>
	<script type="text/javascript">
	$('#addDish').click(function() {
        $("#file").click();
    });

    function ajaxFileUpload(obj) {
        var fileId = $(obj).attr("id");
        var file = $("#" + fileId).val();
        //获取欲上传的文件路径  
        var allowExtention = ".xls,.xlsx"; //允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
        var extention = file.substring(file.lastIndexOf(".") + 1).toLowerCase();
        if (allowExtention.indexOf(extention) < 0) {
            $(obj).val("");
            $.alert({
                title: "仅支持" + allowExtention + "为后缀名的文件!",
            });
            return;
        }
        $.ajax({
        	url: '<%=basePath%>dish/fileUploadF.htm',
        	type:'post',
        	dataType:'json',
        	success:function(data){
        		 if (data.status == '1') {
                 	$.alert({
                    	content: '\t\t\t----导入成功!',
                    	confirm: function() {
                 		}
                    });
                 } else {
                 	$.alert({
                    	title: title + '通知',
                        content: '\t\t\t----失败!',
                    });
            	}
        	},
        	error:function(data){
        		$.alert({
                   	title: title + '通知',
                    content: '\t\t\t----成功!',
                });
        	}
        	
        })
    }
	</script>
</html>
