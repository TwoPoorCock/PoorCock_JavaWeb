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
                    	 关于用户
             </h2>
        </div>

        <div class="row">
        	<div class="col-lg-12">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                        	<i class="fa fa-list-alt"></i>
                            User Profile
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
                                            		<input type="text" id="userName" class="form-control" placeholder="Username" value="${userMap.username}">
                                        		</div>
                                            </td>
                                        </tr>
                                         <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">手机</span>
                                            		<input type="text" id="phone" class="form-control" placeholder="phone" value="${userMap.phone}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="">
                                            <td>
                                            <div class="form-group input-group" id="gender">
                                            	<span class="input-group-addon">性别</span>
                                            	&nbsp; &nbsp;			
                                            	<label class="radio-inline">
													<input type="radio" name="gender" id="userIsMan" value="男" <c:if test="${userMap.gender=='男' }"> checked</c:if>>男
												</label>
												<label class="radio-inline">
													<input type="radio" name="gender" id="userIsWonman" value="女"<c:if test="${userMap.gender=='女' }"> checked</c:if>>女
												</label>
											</div>	                                           	
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group" id="status">
                                            		<span class="input-group-addon">状态</span>
                                            		&nbsp; &nbsp;			
                                            		<label class="radio-inline">
														<input type="radio" name="status" id="statusOn" value="0" <c:if test="${userMap.status==0 }"> checked</c:if>>隐藏
													</label>
													<label class="radio-inline">
														<input type="radio" name="status" id="statusOff" value="1"<c:if test="${userMap.status==1 }"> checked</c:if>>启用
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

	var checkPhone=function(value){
	var isSucc = true;
	if (value != "") {
		if(!/^(13|15|17|18)\d{9}$/i.test(value)){
			$.alert({title: "手机号码格式不正确:" + value ,content: '----正义的伙伴!'});
			isSucc = false;
		}else{
			$.ajax({
			    type: "post",
			    url: "<%=basePath%>admin/checkPhone.htm",
			    data:{
			    	phone:value
			    },
			    async: false,
			    dataType: 'json',
			    success: function(result) {
			        if (result.status == 0) {
			        	$.alert({title: "手机号已经被用过了",content: '----正义的伙伴!'});
			        	isSucc = false;    
			        }
			    }
			});
		}

	}else{
		$.alert({title: "手机号为空",content: '----正义的伙伴!'});
		isSucc = false;
	}
	return isSucc;
}



function submitsUserInfo(obj) {
		var oldPhone= ${userMap.phone};

		var username = $("#userName").val();
		var userphone = $("#phone").val();
		var usergender = $('#gender input:radio:checked').val();
		var userstatus = $('#status input:radio:checked').val();
		var userid = ${userMap.id};
		if(oldPhone != userphone){
			if (! checkPhone(userphone) ) {return;}
		}

		if (null == username || "" == username) {
			$.alert({title: "用户名为空"});
			return;
		}
		if (username.length<=1){
			$.alert({title: "用户名长度应大于3位字符"});
			return;
		}
		if (null == usergender || "" == usergender) {
			$.alert({title: "用户性别为空",content: '----正义的伙伴!'});
			return;
		}
		if (null == userstatus || "" == userstatus) {
			$.alert({title: "用户状态为空",content: '----正义的伙伴!'});
			return;
		}

		$.ajax({
			type: "post",
			url: "<%=basePath%>admin/updateUser.htm",
			data:{
				userName:username,
				phone:userphone,
				status:userstatus,
				gender:usergender,
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