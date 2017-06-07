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
                    	 关于管理员
             </h2>
        </div>

        <div class="row">
        	<div class="col-lg-12">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                        	<i class="fa fa-list-alt"></i>
                            Admin Profile
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        		<input id="userId" type="hidden" value="${userMap.id }"/>
                            <div class="table-responsive">
                            <form role="form">
                                <table class="table">
           
                                    <tbody>
                                        <tr class="">
                                            <td>
                                            	<div class="form-group input-group ">
                                            		<span class="input-group-addon ">姓名</span>
                                            		<input type="text" id="name" class="form-control" placeholder="name" value="${userMap.name}">
                                        		</div>
                                            </td>
                                        </tr>
                                         <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">用户名</span>
                                            		<input type="text" id="userName" class="form-control" placeholder="userName" value="${userMap.username}">
                                        		</div>
                                            </td>
                                        </tr>
                                         <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">密码</span>
                                            		<input type="text" id="passWord" class="form-control" placeholder="passWord" value="${userMap.password}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">邮箱</span>
                                            		<input type="text" id="email" class="form-control" placeholder="email" value="${userMap.email}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group" id="status">
                                            		<span class="input-group-addon">状态</span>
                                            		&nbsp; &nbsp;			
                                            		<label class="radio-inline">
														<input type="radio" name="status" id="statusOn" value="0" <c:if test="${userMap.isdel==0 }"> checked</c:if>>隐藏
													</label>
													<label class="radio-inline">
														<input type="radio" name="status" id="statusOff" value="1"<c:if test="${userMap.isdel==1 }"> checked</c:if>>启用
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
		var username = $("#userName").val();
		var passWord = $("#passWord").val();
		var name = $("#name").val();
		var email = $("#email").val();
		var userstatus = $('#status input:radio:checked').val();
		var userid = ${userMap.id};
		if (null == username || "" == username) {
			$.alert({title: "用户名为空"});
			return;
		}
		if (null == name || "" == name) {
			$.alert({title: "姓名为空"});
			return;
		}
		if (null == passWord || "" == passWord) {
			$.alert({title: "密码为空"});
			return;
		}
		if (username.length<=1){
			$.alert({title: "用户名长度应大于3位字符"});
			return;
		}
		
		if (null == userstatus || "" == userstatus) {
			$.alert({title: "用户状态为空",content: '----正义的伙伴!'});
			return;
		}

		$.ajax({
			type: "post",
			url: "<%=basePath%>user/updateAdmin.htm",
			data:{
				name:name,
				userName:username,
				passWord:passWord,
				email:email,
				status:userstatus,
				userId:userid
			},
			dataType: 'json',
			success: function(result) {
				if (result.status == 1) {
					$.alert({
						title: '修改成功',
						content: '通过！'
					});
				} else {
					$.alert({
						title: '修改失败'
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