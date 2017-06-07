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
                    	 关于商家
             </h2>
        </div>

        <div class="row">
        	<div class="col-lg-12">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                        	<i class="fa fa-list-alt"></i>
                            Business Profile
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        		<input id="userId" type="hidden" value="${businessMap.id }"/>
                            <div class="table-responsive">
                            <form role="form">
                                <table class="table">
           
                                    <tbody>
                                        <tr class="">
                                            <td>
                                            	<div class="form-group input-group ">
                                            		<span class="input-group-addon ">名称</span>
                                            		<input type="text" id="name" class="form-control" placeholder="Username" value="${businessMap.name}">
                                        		</div>
                                            </td>
                                        </tr>
                                         <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">联系方式</span>
                                            		<input type="text" id="phone" class="form-control" placeholder="phone" value="${businessMap.phone}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">邮箱</span>
                                            		<input type="text" id="email" class="form-control" placeholder="email" value="${businessMap.email}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group">
                                            		<span class="input-group-addon">地理位置</span>
                                            		<input type="text" id="position" class="form-control" placeholder="position" value="${businessMap.position}">
                                        		</div>
                                            </td>
                                        </tr>
                                        <tr class="guldan">
                                            <td>
                                            	<div class="form-group input-group" id="status">
                                            		<span class="input-group-addon">状态</span>
                                            		&nbsp; &nbsp;			
                                            		<label class="radio-inline">
														<input type="radio" name="status" id="statusOn" value="0" <c:if test="${businessMap.status==0 }"> checked</c:if>>隐藏
													</label>
													<label class="radio-inline">
														<input type="radio" name="status" id="statusOff" value="1"<c:if test="${businessMap.status==1 }"> checked</c:if>>启用
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
			$.alert({title: "通知" ,content: '----手机号码格式不正确!' + value});
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
			        	$.alert({title: "警告",content: '----手机号已经被用过了!'});
			        	isSucc = false;    
			        }
			    }
			});
		}

	}else{
		$.alert({title: "警告",content: '----手机号为空!'});
		isSucc = false;
	}
	return isSucc;
}



function submitsUserInfo(obj) {
		var oldPhone= ${businessMap.phone};

		var name = $("#name").val();
		var phone = $("#phone").val();
		var status = $('#status input:radio:checked').val();
		var businessId = ${businessMap.id};
		var email = $("#email").val();
		var position = $("#position").val();
		if(oldPhone != phone){
			if (! checkPhone(phone) ) {return;}
		}

		if (null == name || "" == name) {
			$.alert({title: "警告",content: '----用户名为空'});
			return;
		}
		if (name.length<=1){
			$.alert({title: "警告",content: '----用户名长度应大于3位字符'});
			return;
		}
		if (null == status || "" == status) {
			$.alert({title: "警告",content: '----用户状态为空'});
			return;
		}
		if (null == position || "" == position){
			$.alert({title: "警告",content: '----地理位置不能为空'});
			return;
		}
		$.ajax({
			type: "post",
			url: "<%=basePath%>business/updateBusiness.htm",
			data:{
				name:name,
				phone:phone,
				email:email,
				position:position,
				status:status,
				businessId:businessId
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