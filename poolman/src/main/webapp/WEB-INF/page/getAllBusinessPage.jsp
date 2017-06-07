<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="baseGuldanCss.jsp" %>
</head>
<body>

<div id="wrapper">

    <%@ include file="menu_left.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">商家列表</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12 alert alert-success">
                <span>Tips: 总共</span>
                <span id="Count_User"></span>
                <span>户商家</span>
            </div>
        </div>

        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list-alt"></i>
                       商家列表
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-8">
                                <span><button id="enableUser" class="btn btn-outline btn-success"> 启用 </button></span>
                                <span><button id="disableUser" class="btn btn-outline btn-danger"> 停用 </button></span>
                            </div>

                        </div>

                        <hr>
                        <div class="dataTable_wrapper">
                            <table id="dataTables" class="table table-striped table-bordered table-hover">

                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>名称</th>
                                    <th>联系方式</th>
                                    <th>邮箱</th>
                                    <th>地理位置</th>
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

    $(document).ready(function () {

        var table = $('#dataTables').DataTable({
            bServerSide: true,
            searching: true,
            ajax: {
                url: 'business/getAllBusinessPage.htm',
                dataSrc: 'rows'
            },
            "columns": [
                {"data": "id"},
                {"data": "name"},
                {"data": "phone"},
                {"data": "email"},
                {"data": "position"},
                {"data": "status"}
            ],

            "columnDefs": [
                {"name": "id", "targets": 0},
                {
                    "targets": 1,
                    "name": "name",
                    "render": function (data, type, row) {
                        return '<a href="' + '<%=basePath%>business/toEditBusinessPage.htm?id=' + row.id + '">' + row.name + '</a>';
                    }
                },
                {"name": "phone", "targets": 2},
                {"name": "email", "targets": 3},
                {"name": "position", "targets": 4},
                {	"name": "status",
                	"targets": 5,
                	"render": function (data, type, row) {
                        if (data == "0") {
                            return '<i class="fa fa-remove fa-fw"></i> ';
                        } else {
                            return '<i class="fa fa-thumbs-up fa-fw"></i> ';
                        }
                    }
                },
                {"defaultContent": "-", "targets": "_all"}
            ],
           "fnDrawCallback": function (oSettings) {
                document.getElementById("Count_User").innerHTML=oSettings._iRecordsTotal;
            }

        });

        var num = 0;
        var arr = new Array(num);
        $('#dataTables tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                arr.splice(arr.indexOf($(this)[0].cells[0].innerText), 1);
                $(this).removeClass('selected');
            }
            else {
                $(this).addClass('selected');
                arr.push($(this)[0].cells[0].innerText);
            }
        });

        function enableAndDisableStatus(title, status, id) {
            $.confirm({
                title: '注意：',
                content: "是否"+title,
                confirm: function () {
                    $.ajax({
                        url: "<%=basePath%>business/batchEnableOrDisableBusiness.htm",
                        type: "post",
                        data: {
                            businessIds: id,
                            status: status
                        },
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            //删除失败
                            if (data.status == '1') {
                                $.alert({
                                    title: title + '通知：',
                                    content: '\t\t\t----成功!',
                                    confirm: function () {
                                        table.draw(false);
                                    }
                                });
                            } else {
                                $.alert({title: title + '通知', content: '\t\t\t----失败!',});
                            }
                        }
                    });
                },
                cancel: function () {
                    // do nothing
                }
            });
        }

        var isSelect = function () {
            if (table.$('tr.selected')[0] == null) {
                $.alert({
                    title: '通知：',
                    content: '\t\t\t----没有选择任何用户!'
                });
                return 0;
            }
            return 1;
        }
        $('#enableUser').click(function () {
            if (isSelect() == 0) {
                return;
            }
            var id = arr.join(',');
            arr = [];
            table.$('tr.selected').removeClass('selected');
            enableAndDisableStatus("启用", 1, id);
        });

        $('#disableUser').click(function () {
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
</html>