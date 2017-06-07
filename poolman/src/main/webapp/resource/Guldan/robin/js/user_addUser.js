
var checkPhone=function(value){
	var isSucc = true

		if (value!="") {
			if(!/^(13|15|18)\d{9}$/i.test(value)){
				$.alert({title: "手机号码格式不正确",content: '----正义的伙伴!'});
				isSucc = false;
			} 
			$.ajax({
			    type: "post",
			    url: "admin/user/checkPhone.htm",
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
		}else{
			$.alert({title: "手机号为空",content: '----正义的伙伴!'});
			isSucc = false;    
		}
	return isSucc;
}

var checkStudentId =function (value){
	var isSucc = true
	if (value!="") {
		$.ajax({
			    type: "post",
			    url: "admin/user/checkStudentId.htm",
			    data:{
			    	studentId:value
			    },
			    async: false,
			    dataType: 'json',
			    success: function(result) {
			        if (result.status == 0) {
			            $.alert({title: "学号已经被用过了",content: '----正义的伙伴!'});
			            isSucc = false;    
			        }
			    }
		});
	}else{
		$.alert({title: "学号为空",content: '----正义的伙伴!'});
		isSucc = false;    
	}
	return isSucc ;
}


function updateUser(data){

	$.ajax({
		type: "post",
		url: "admin/user/addUserDetail.htm",
		data:data,
		dataType: 'json',
		success: function(result) {
			if (result.status == 1) {
            	$.alert({title: "保存成功",content: '----正义的伙伴!'});
        	} else {
             	$.alert({title: "保存失败！",content: '----正义的伙伴!'});
        	}
		},
		error: function(data) {
			$.alert({title: "创建用户失败",content: '----正义的伙伴!'});
		}
	});
}

function addUserInfo(obj,idx,role){

	var myTimeline = document.getElementById("myTimeline");
	// build li
	var newLI = document.createElement("li");

	if (idx % 2 != 0 ) {
		newLI.setAttribute("class","timeline-inverted");
	}
	newLI.setAttribute("id","userLi"+idx);

	// build badge
	var badgeDiv  = document.createElement("div");
	var saveInfoDiv = document.createElement("div");
	var badgeFa = document.createElement("i");

	badgeDiv.setAttribute("class","timeline-badge warning");
	badgeDiv.setAttribute("id","userbadge"+idx);
	saveInfoDiv.setAttribute("onclick","saveUserByBtn(this)");
	saveInfoDiv.setAttribute("id","saveUserInfo"+idx);
	badgeFa.setAttribute("class","fa fa-check");
	newLI.appendChild(badgeDiv);
	badgeDiv.appendChild(saveInfoDiv);
	saveInfoDiv.appendChild(badgeFa);

	// add some ajax param
	var userRole = document.createElement("input");
	userRole.setAttribute("type","hidden");
	userRole.setAttribute("id","userRole" + idx);
	userRole.setAttribute("name","name" + idx);
	userRole.setAttribute("value",role);
	newLI.appendChild(userRole);






	var timeline_panel = document.createElement("div");
	timeline_panel.setAttribute("class","timeline-panel");


	//build head
	var headDiv = document.createElement("div");
	var headP = document.createElement("p");
	var headSmall = document.createElement("small");
	var headI = document.createElement("i");
	var roleLogo  ;
	if (role == 0 ) {
		roleLogo = document.createTextNode("老师");
	}else if (role == 1) {
		roleLogo = document.createTextNode("学生");
	}

	headDiv.setAttribute("class","timeline-heading");
	headSmall.setAttribute("class","text-muted");
	headSmall.setAttribute("id","roleLogo"+idx);
	headI.setAttribute("class","fa fa-clock-o");
	timeline_panel.appendChild(headDiv);
	headDiv.appendChild(headP);
	headP.appendChild(headI);
	headP.appendChild(headSmall);
	//headSmall.appendChild(headI);
	headSmall.appendChild(roleLogo);




	//build body
	var timeline_body = document.createElement("div");
	timeline_body.setAttribute("class","timeline-panel");

	newLI.appendChild(timeline_panel);
	timeline_panel.appendChild(timeline_body)


	// build user name
	var userNameFormDiv = document.createElement("div");
	userNameFormDiv.setAttribute("class","form-group input-group");
	var span0 = document.createElement("span");
	span0.setAttribute("class","input-group-addon");
	var faI0 = document.createElement("i");
	faI0.setAttribute("class","fa fa-user fa-fw");
	var userNameInput = document.createElement("input");
	userNameInput.setAttribute("id","userName"+idx);
	userNameInput.setAttribute("type","text");
	userNameInput.setAttribute("class","form-control");
	userNameInput.setAttribute("placeholder","Username");

	timeline_body.appendChild(userNameFormDiv);
	userNameFormDiv.appendChild(span0);
	span0.appendChild(faI0);
	userNameFormDiv.appendChild(userNameInput);

	// add user phone
	var phoneFromDiv = document.createElement("div");
	var span1 = document.createElement("span");
	var faI1 = document.createElement("i");
	var phoneinput = document.createElement("input");
	phoneFromDiv.setAttribute("class","form-group input-group");
	span1.setAttribute("class","input-group-addon");
	faI1.setAttribute("class","fa fa-phone-square fa-fw");
	phoneinput.setAttribute("class","form-control");
	phoneinput.setAttribute("id","userPhone"+idx);
	phoneinput.setAttribute("type","text");
	phoneinput.setAttribute("placeholder","Phone");
	timeline_body.appendChild(phoneFromDiv);
	phoneFromDiv.appendChild(span1);
	span1.appendChild(faI1);
	phoneFromDiv.appendChild(phoneinput);


	// add student id
	var studentFromDiv = document.createElement("div");
	var span2 = document.createElement("span");
	var faI2 = document.createElement("i");
	var studentinput = document.createElement("input");
	studentFromDiv.setAttribute("class","form-group input-group");
	studentFromDiv.setAttribute("id","stdDiv"+idx);
	if (role == 0 ) {
		studentFromDiv.setAttribute("style","display: none;");

	}else{
		studentFromDiv.setAttribute("style","");
	}
	span2.setAttribute("class","input-group-addon");
	faI2.setAttribute("class","fa fa-power-off  fa-fw");
	studentinput.setAttribute("class","form-control");
	studentinput.setAttribute("id","studentId"+idx);
	studentinput.setAttribute("type","text");
	studentinput.setAttribute("placeholder","Student id ");
	timeline_body.appendChild(studentFromDiv);
	studentFromDiv.appendChild(span2);
	span2.appendChild(faI2);
	studentFromDiv.appendChild(studentinput);

	// add gender
	var userGenderFromDiv = document.createElement("div");
	var faI3 = document.createElement("i");
	var label0 = document.createElement("label");
	var userGenderInput0 = document.createElement("input");
	var label1 = document.createElement("label");
	var userGenderInput1 = document.createElement("input");
	var manText = document.createTextNode("男");
	var womanText = document.createTextNode("女");

	userGenderFromDiv.setAttribute("class","form-group");
	userGenderFromDiv.setAttribute("id","userGender"+idx);
	faI3.setAttribute("class","fa fa-child fa-fw");
	label0.setAttribute("class","radio-inline");
	label1.setAttribute("class","radio-inline");
	userGenderInput0.setAttribute("type","radio");
	userGenderInput0.setAttribute("name","gender"+idx);
	userGenderInput0.setAttribute("id","gender0");
	userGenderInput0.setAttribute("value","男");
	userGenderInput0.setAttribute("checked","1");


	userGenderInput1.setAttribute("type","radio");
	userGenderInput1.setAttribute("name","gender"+idx);
	userGenderInput1.setAttribute("id","gender0");
	userGenderInput1.setAttribute("value","女");

	timeline_body.appendChild(userGenderFromDiv);
	userGenderFromDiv.appendChild(faI3);
	userGenderFromDiv.appendChild(label0);
	label0.appendChild(userGenderInput0);
	userGenderFromDiv.appendChild(label1);
	label1.appendChild(userGenderInput1);
	label0.appendChild(manText);
	label1.appendChild(womanText);


	//add image upload
	var imgDiv = document.createElement("div");
	var img = document.createElement("img");
	var imgP = document.createElement("p");
	var imgLable = document.createElement("label");
	var imginput = document.createElement("input");
	var upText = document.createTextNode("上传头像");

	imgDiv.setAttribute("class","form-group");
	img.setAttribute("id" ,"userAvatarImg"+idx);
	img.setAttribute("src","resource/images/zanwutupian.jpg");
	img.setAttribute("width","100px");
	img.setAttribute("height","100px");
	imgLable.setAttribute("class","btn btn-outline btn-success btn-file");
	imginput.setAttribute("type","file");
	imginput.setAttribute("name","file");
	imginput.setAttribute("style","display: none;");
	imginput.setAttribute("id","userAvatar"+idx);
	imginput.setAttribute("onchange","ajaxFileUpload(this)");

	timeline_body.appendChild(imgDiv)
	imgDiv.appendChild(img);
	imgDiv.appendChild(imgP);
	imgDiv.appendChild(imgLable);
	imgLable.appendChild(upText);
	imgLable.appendChild(imginput);

	if (idx==0) {
		 myTimeline.appendChild(newLI);
	}else{
		var oldLi = document.getElementById("userLi"+(idx-1))
		myTimeline.insertBefore(newLI,oldLi);
	}


	//obj.appendChild(newLI);

}

function saveUserByBtn(obj){

	var fileId = $(obj).attr("id");
	var userIndex = fileId.substring(12,fileId.length);


	// check userName
	var iuserName = $("#userName" + userIndex).val();

	if (null == iuserName || iuserName == "") {
		$.alert({title: "用户名为空",content: '----正义的伙伴!'});
		return;
	}

	//var iuserName=new Array();
	//iuserName.push(userName);
	//data[userName]=[iuserName];


	// check gender
	//$('#tieziTypeDiv input:radio:checked').val()
	var gender = $("#userGender" + userIndex+" input:radio:checked").val();
	if (null == gender || gender == "") {
		$.alert({title: "用户性别不能为空",content: '----正义的伙伴!'});
		return;
	}




	// user role
	var userRole = $("#userRole" + userIndex).val();
	// check phone
	var phone = $("#userPhone" + userIndex).val();
	if (userRole == 0 ) {
		if ( !checkPhone(phone)) { return }
	}
	

	// schoolId
	var schoolId=$("#schoolId" ).val();

	// touxiangUrls
	var img = $("#userAvatarImg" + userIndex).attr('src');
	if (img.indexOf("zanwutupian.jpg") > 0 ) {
		img = "";
	}

	// gradeId
	var gradeId=$("#gradeId" ).val();

	// classId
	var classId=$("#classId" ).val();

	// studentId
	var studentStudentId="";
	if (userRole == "1") {
		studentStudentId = $("#studentId" + userIndex).val();
		if (! checkStudentId(studentStudentId)) {return ;}

	}

	// userFlag
	var userRole = $("#userRole" + userIndex).val();


	//communityId
	var communityId= $("#communityId").val();

	// update database

	$.ajax({
		type: "post",
		url: "admin/user/addUserDetail.htm",
		data:{
			userName:iuserName,
			genders:gender,
			phones:phone,
			userRole:userRole,
			schoolId:schoolId,
			touxiangUrls:img,
			gradeId:gradeId,
			classId:classId,
			studentStudentId:studentStudentId,
			communityId:communityId,
			birthdays:""
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 1) {
            	$.alert({title: "保存成功",content: '----正义的伙伴!'});
        	} else {
             	$.alert({title: "保存失败！",content: '----正义的伙伴!'});
        	}
		},
		error: function(data) {
			$.alert({title: "创建用户失败",content: '----正义的伙伴!'});
		}
	});


	// change page
	var badge = document.getElementById("userbadge"+userIndex);
	badge.setAttribute("class","timeline-badge success");
	var saveInfoDiv = document.getElementById("saveUserInfo"+userIndex);
	saveInfoDiv.setAttribute("onclick","");


	addUserInfo(obj,parseInt(userIndex)+1,userRole);

}
