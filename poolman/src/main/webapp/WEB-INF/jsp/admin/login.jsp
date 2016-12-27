<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
   	<link rel="stylesheet" type="text/css" href="../login/css/index.css">
    <script type="text/javascript" src="../login/scripts/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="../login/scripts/bg.js"></script>
	<title>登录</title>
</head>
<body onload="createCode()">
    <div class="warpper">
        <div class="bg" id="container" style="width:100%;height:100%;">
            <div id="anitOut"></div>
        </div>
        <div class="logo-box">
            <img src="../login/images/logo.png">
        </div>
        <div class="login-box">
            <form id="loginForm" action="submit.htm" method="post" onsubmit="return submitValidate();">
                <span class="username-box">
                    <i></i>
                    <input id="userName" class="username" placeholder="用户名" type="text" name="username" 
                    	onblur='userNameBlur()'>
                </span>
                
                <span class="password-box">
                    <i></i>
                    <input id="pwd" class="password" placeholder="密码" type="password" name="password">
                </span>
                <span class="yzm-box">
                    <i id="code_pic" class="code_pic"></i>
                    <input id="codeStr" class="yzm" type="text" 
                    	oninput="OnInput (event)" onpropertychange="OnPropChanged (event)" />
                    <div class="yzm-img-box">
                    	<input type="button" id="code" class="yzm-img" onclick="createCode()"  title='点击更换验证码' />
                   	</div>
                </span>
                <button class="button" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
            </form>
        </div>
    </div>

</body>
<script type="text/javascript">

	var code; //在全局定义验证码   
	var b_yzm = false;
	//产生验证码  
	function createCode() {
		code = "";
		var codeLength = 4;//验证码的长度  
		var checkCode = document.getElementById("code");
		var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//随机数  
		for (var i = 0; i < codeLength; i++) {//循环操作  
			var index = Math.floor(Math.random() * 36);//取得随机数的索引（0~35）  
			code += random[index];//根据索引取得随机数加到code上  
		}
		checkCode.value = code;//把code值赋给验证码
	}
	//光标移开时，判断用户名是否为空
	function userNameBlur() {
		var userName = document.getElementById("userName").value;

		if (userName == null || userName == '') {
			document.getElementById("userName").value = "请输入用户名";
		}
	}
	
	//验证码点击清空
	function yzmFocus() {
		document.getElementById("codeStr").value = "";
		b_yzm = false;
	}
	//校验验证码
	function yzmBlur(value) {
		var yzm = value;
		if (yzm.length == 4) {
			if (yzm.toLowerCase() == code.toLowerCase()) {
				document.getElementById("code_pic").className = "login-true";
				b_yzm = true;
			} else {
				document.getElementById("code_pic").className = "login-false";
				b_yzm = false;
			}
		}
	}
	function OnInput(event) {
		//alert ("The new content: " + event.target.value);
		var value = event.target.value;
		yzmBlur(value);
	}
	// Internet Explorer
	function OnPropChanged(event) {
		console.info(event)
		if (event.propertyName.toLowerCase() == "value") {
			var value = event.srcElement.value;
			yzmBlur(value);
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
		}else if(b_yzm){
			return true;
		}else {
			alert("hahhahaa");
			return false;			
		}
	}
</script>

</html>
    