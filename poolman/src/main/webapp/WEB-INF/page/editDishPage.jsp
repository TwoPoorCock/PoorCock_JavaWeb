<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<h2 class="page-header">
                    	<i class="fa fa-angle-left" onclick="backPage(this)" ></i>
                    	 关于菜品
             </h2>
        </div>

        <div class="row">
        	<div class="col-lg-12">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                        	<i class="fa fa-list-alt"></i>
                            dish Profile
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        		<input id="userId" type="hidden" value="${dishMap.id }"/>
                            <div class="table-responsive">
                            <form role="form">
                                <table class="table">
           
                                    <tbody>
                                        <tr class="">
                                            <td>
                                            	<div class="form-group input-group ">
                                            		<span class="input-group-addon ">名称</span>
                                            		<input type="text" id="dishName" class="form-control" placeholder="dishName" value="${dishMap.dishname}">
                                        		</div>
                                            </td>
                                        </tr>
                                         <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">荤素程度</span>
                                            		<input type="text" id="flag_r" class="form-control" placeholder="flag_r" value="${dishMap.flagR}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">辛辣程度</span>
                                            		<input type="text" id="flag_h" class="form-control" placeholder="flag_h" value="${dishMap.flagH}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group" id="status">
                                            		<span class="input-group-addon">状态</span>
                                            		&nbsp; &nbsp;			
                                            		<label class="radio-inline">
														<input type="radio" name="status" id="statusOn" value="0" <c:if test="${dishMap.type==0 }"> checked</c:if>>隐藏
													</label>
													<label class="radio-inline">
														<input type="radio" name="status" id="statusOff" value="1"<c:if test="${dishMap.type==1 }"> checked</c:if>>启用
													</label>
												</div>	  
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <button type="button" class="btn btn-outline btn-warning btn-lg btn-block" onclick="submitsUserInfo(this)">保存</button>
                            </form>
							</div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
        </div>
	</div>
</div>





<script type="text/javascript">

	function backPage(obj){
		window.history.back(-1);
	}

	function submitsUserInfo(obj) {
		var name = $("#dishName").val();
		var flag_r = $("#flag_r").val();
		var status = $('#status input:radio:checked').val();
		var dishId = ${dishMap.id};
		var flag_h = $("#flag_h").val();

		if (null == name || "" == name) {
			$.alert({title: "警告",content: '----菜名为空'});
			return;
		}
		$.ajax({
			type: "post",
			url: "<%=basePath%>dish/updateDish.htm",
			data:{
				name:name,
				flag_r:flag_r,
				flag_h:flag_h,
				status:status,
				dishId:dishId
			},
			dataType: 'json',
			success: function(result) {
				if (result.status == 1) {
					$.alert({
						title: '修改成功',
						content: '----通过！'
					});
				} else {
					$.alert({
						title: '警告',
						content: '----修改失败！'
					});
				}
			}
		});
		return;
	}
</script>

<script src="<%=guldanPath%>/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=guldanPath%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=guldanPath%>/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script src="<%=guldanPath%>/dist/js/sb-admin-2.js"></script>
<script src="<%=guldanPath%>/jquery-confirm/dist/jquery-confirm.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/ajaxfileupload.js"></script>

</body>
</html>