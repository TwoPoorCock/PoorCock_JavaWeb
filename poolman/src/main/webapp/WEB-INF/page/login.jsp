<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/basePath.jsp"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
   	<link rel="stylesheet" type="text/css" href="login/css/index.css">
    <script type="text/javascript" src="login/scripts/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="login/scripts/bg.js"></script>
	<title>登录</title>
</head>
<body>
    <div class="warpper">
        <div class="bg" id="container" style="width:100%;height:100%;">
            <div id="anitOut"></div>
        </div>
        <div class="logo-box">
            <img src="login/images/logo.png">
            <span>小鳄吃啥APP专注于选择困难</span>
        </div>
        <div class="login-box">
            <form id="loginForm" method="post">
                <span class="username-box">
                    <i></i>
                    <input id="userName" class="username" placeholder="用户名" type="text" name="username">
                </span>
                
                <span class="password-box">
                    <i></i>
                    <input id="pwd" class="password" placeholder="密码" type="password" name="password">
                </span>
                <span class="yzm-box">
                    <i id="code_pic" class="code_pic"></i>
                    <input id="codeStr" class="yzm" type="text" />
                    <div class="yzm-img-box">
                    	<img src="code" onclick="this.src='code?'+Math.random();" title="change" id="code" />
                   	</div>
                </span>
                <button class="button" type="button" onclick="submitValidate()">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
            </form>
			<div style="margin-top: 150px;">联系邮箱：390812516@qq.com</div>
        </div>
    </div>
    
</body>
<script type="text/javascript">
	document.onkeydown = function(e){
		if(e.keyCode==13){
			submitValidate();
		}
	}

	//表单信息提交
	function submitValidate() {
		var userName = $("#userName").val();
		var pwd = $("#pwd").val();
		var codeStr = $("#codeStr").val();
		if(userName == ""|| userName==null){
			alert("请输入用户名！");
			return false;
		}else if(pwd == ""||pwd == null){
			alert("请输入密码！");
			return false;
		}else if(codeStr ==""||codeStr ==null){
			alert("请输入验证码！");
			return false;
		}else {
			$.ajax({
				url:"<%=basePath%>login/submit.htm",
				type:"post",
				data:{
					userName:userName,
					passWord:pwd,
					codeStr:codeStr
				},
				dataType:"json",
				success: function(data){
					if(data.status == '1'){
						$("#code_pic").addClass("login-true");
						window.location.href="<%=basePath%>login/toIndex.htm?userName="+userName+"";
					}else if(data.status == '3'){
						$("#code_pic").addClass("login-false");
						alert(data.msg);
					}else {
						alert(data.msg);
					}
				}
				
			})			
		}
	}
</script>

</html>
    