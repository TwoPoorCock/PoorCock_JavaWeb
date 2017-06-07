var imgCount = function(){
	//var imgUpload = document.getElementById("imgUpload"); 
	var imgUl = document.getElementById("imgUpload");
	var imgLis = imgUl.getElementsByTagName("li");
	return imgLis.length -1;

}
function cpToClipboard(text){
	const input = document.createElement('input');
  	input.style.position = 'fixed';
  	input.style.opacity = 0;
  	input.value = text;
  	document.body.appendChild(input);
  	input.select();
  	document.execCommand('Copy');
  	document.body.removeChild(input);
}

var getAllUploadUrls = function(){

  var urls  = new Array();
  var imgUl = document.getElementById("imgUpload");
  var imgs = imgUl.getElementsByTagName("img");
  for (var i = 0; i < imgs.length; i++) {
  	var imgId = imgs[i].getAttribute("id");
  	if (imgId.indexOf("show") >=0) {
  		urls.push(imgs[i].getAttribute("src"));
  	}
  }
  return urls.join(","); 
}

function copyAllUrlToClipboard(){
	cpToClipboard(getAllUploadUrls());
}
function delImg(obj){
	var fileId = $(obj).attr("id");
    var index =fileId.substring(6,fileId.length);
    var imgUL = document.getElementById("imgUpload");
    var toDelLi = document.getElementById("ploadimgLi"+index);
    imgUL.removeChild(toDelLi);
    getAllUploadUrls();

}
function cpUrl(obj){
  var fileId = $(obj).attr("id");
  var index =fileId.substring(5,fileId.length);
  cpToClipboard($("#file"+index+"show").attr("src")); 

}
function addItem(idx,src){

	var li = document.createElement("li");
	var headDiv = document.createElement("div");
	var img = document.createElement("img");
	var small = document.createElement("small");
	var p = document.createElement("p");
	var button = document.createElement("button");
	var i_1 = document.createElement("i");
	var i_2 = document.createElement("i");
	var  btn = document.createElement("button");


	li.setAttribute("class","left clearfix");
	li.setAttribute("id","ploadimgLi" + idx);
	li.setAttribute("name","ploadimgLi" + idx);

	headDiv.setAttribute("class","header");
	img.setAttribute("id","file"+idx+"show");
	img.setAttribute("src",src);
	img.setAttribute("width","100px");
	img.setAttribute("height","100px");
	img.setAttribute("name","img"+idx);

	small.setAttribute("class","pull-right text-muted");
	button.setAttribute("class","btn btn-danger btn-circle");
	button.setAttribute("type","button");
	button.setAttribute("id","delBtn"+idx);
	button.setAttribute("onclick","delImg(this)");

	i_1.setAttribute("class","fa fa-times");
	btn.setAttribute("class","btn btn-info btn-circle");
	btn.setAttribute("type","button");
	btn.setAttribute("id","cpBtn"+idx);
	btn.setAttribute("onclick","cpUrl(this)");
	i_2.setAttribute('class','fa fa-copy');

	
	li.appendChild(headDiv);
	headDiv.appendChild(img);
	headDiv.appendChild(small);
	headDiv.appendChild(p);
	headDiv.appendChild(btn);

	small.appendChild(button);
	button.appendChild(i_1);

	btn.appendChild(i_2);


	var imgUL = document.getElementById("imgUpload");
	
	if (idx == 1 ) {
		imgUL.appendChild(li);
	}else{
		var oldLi = document.getElementById("ploadimgLi"+(idx-1));
		imgUL.insertBefore(li,oldLi);
	}

}


function ajaxFileUpload(obj) {
	
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        var fileId = $(obj).attr("id");
        var index =fileId.substring(4,fileId.length);
        var file = $("#"+fileId).val();
        var imgcounts = imgCount();

        //获取欲上传的文件路径  
        var allowExtention = ".jpg,.bmp,.gif,.png,.jpeg"; //允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
        var extention = file.substring(file.lastIndexOf(".") + 1).toLowerCase();        
        if (allowExtention.indexOf(extention) < 0) {
            $(obj).val("");
            $.alert({title: "仅支持" + allowExtention + "为后缀名的文件!",content: '----正义的伙伴!'});
            return;
        }

        var fileSize = 0;
        if (isIE && !obj.files) {
            var filePath = obj.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file1 = fileSystem.GetFile(filePath);
            fileSize = file1.Size;
        } else {
            fileSize = obj.files[0].size;
        }
        var maxSize=1024*2;
        var size = fileSize / 1024;
        if (size > maxSize) {
            $.alert({title: "当前文件大小" + size+ "KB, 超出最大限制 " +maxSize+ "KB",content: '----正义的伙伴!'});
        } else {
            $.ajaxFileUpload({
                url:'admin/uploadFile.htm',
                //需要链接到服务器地址
                secureuri: false,
                fileElementId: fileId,
                //文件选择框的id属性
                dataType: 'json',
                //服务器返回的格式，可以是json
                success: function(data) { //相当于java中try语句块的用法
                    data = data.replace(/<[^>].*?>/g, ""); //对返回的数据进行转换
                    data = eval("(" + data + ")"); //对返回的数据进行转换 
					addItem(imgcounts +1,data.absoluteSrc);                  
                },
                error: function(data) {
                     $.alert({title: "上传失败",content: '----正义的伙伴!'});
                }
            });
        }
}

var text = [
			"## 小鳄吃饭",
			"小鳄吃饭,你好",
			"",
			"健康饮食的不二选择！"
].join('\n');
var options = {
		file: {
			name: 'README.md',
			defaultContent: text
		},
			theme: { base: '/../../../resource/Guldan/EpicEditor/epiceditor/themes/base/epiceditor.css'
        	, preview: '/../../../resource/Guldan/EpicEditor/epiceditor/themes/preview/github.css'
        	, editor: '/../../../resource/Guldan/EpicEditor/epiceditor/themes/editor/epic-dark.css'
    	}
	};
var editor = new EpicEditor(options).load();

function buildEditorForMk(mkType){
	var flag = mkType-1;
	if (flag>2){
		var urls = getAllUploadUrls();
		$.ajax({
			url:'admin/softwareInfomation/getSoftwareInfomationContent.htm',
			//需要链接到服务器地址
			secureuri: false,
			data:{
				flag:flag,
				imgPaths:urls
			},
			type: "get",
			dataType: "json",
			async: true,
			success: function(data) { //相当于java中try语句块的用法
				if (data.status == 1) {
					var content = data.softwareInfo["content"];
					editor.importFile(flag+".md",content);

					var imgs=data.softwareInfo["imgs"];
					if (null != imgs) {
						for (var i = 0; i < imgs.length; i++) {
							addItem(i,imgs[i]);
						}
					}
				}
			}
		});
	}
	else {
		$.ajax({
			url:'admin/softwareInfomation/getSoftwareInfomationContent.htm',
			//需要链接到服务器地址
			secureuri: false,
			data:{
				flag:flag
			},
			type: "get",
			dataType: "json",
			async: true,
			success: function(data) { //相当于java中try语句块的用法
				if (data.status == 1) {
					var content = data.softwareInfo["content"];
					editor.importFile(flag+".md",content);
				}
			}
		});
	}
}

function buildEditor(discoveryId){
	$.ajax({
		url:'admin/discovery/getDiscoveryContent.htm',
		//需要链接到服务器地址
		secureuri: false,
		data:{
			discoveryId:discoveryId
		},
		type: "get",
		dataType: "json",
		async: true,
		success: function(data) { //相当于java中try语句块的用法
				
				if (data.status == 1) {

					var content = data.discovery["content"];
					editor.importFile(discoveryId+".md",content);

					var imgs=data.discovery["imgs"];
					if (null != imgs) {
						for (var i = 0; i < imgs.length; i++) {
							addItem(i,imgs[i]);  
						}
					}
				}
		}
	});
}
	
function saveToServerAjaxCall(html) {
	var urls = getAllUploadUrls();
	var content = editor.exportFile("README.md", "text");
	var description = editor.exportFile("README.md", "html");
	var title = $("#title").val();
	var status = $("#status").val();
	var adminId = $("#adminId").val();

	if (null == title  || title== "" ) {
		$.alert({title: "请输入标题，sb",content: '----正义的伙伴!'});
		return;
	}

	if (null == status  || status== "" ) {
		$.alert({title: "请选择状态，sb",content: '----正义的伙伴!'});
		return;
	}

	$.ajax({
		url:"admin/discovery/addMarkdownDiscovery.htm",
		type: "post",
		data: {
				title: title,
				status:status,
				imgPaths:urls,
				adminId:adminId,
				description:description,
				content:content
			},
		dataType: "json",
		async: false,
		success: function(data) {
		//删除失败
		if (data.status == '1') {
			$.alert({
				title: '恭喜你发布成功',
				content: '\t\t\t----正义的伙伴!',
				confirm: function(){
					//window.location.href='admin/discovery/toGetAllDiscoverysPage.htm';
					window.close();
				}

			});
			
		} else {
			$.alert({title: '发布失败,SB',content: '\t\t\t----正义的伙伴!',});
		}
		}
		});
}
// update
function updateDiscovery(){
	var urls = getAllUploadUrls();
	var discoveryId = $("#discoveryId").val();
	var content = editor.exportFile(discoveryId+".md", "text");
	var description = editor.exportFile(discoveryId+".md", "html");
	var title = $("#title").val();
	var status = $("#status").val();
	var adminId = $("#adminId").val();
	

	if (null == title  || title== "" ) {
		$.alert({title: "请输入标题，sb",content: '----正义的伙伴!'});
		return;
	}

	if (null == status  || status== "" ) {
		$.alert({title: "请选择状态，sb",content: '----正义的伙伴!'});
		return;
	}

	$.ajax({
		url:"admin/discovery/updateMarkdownDiscovery.htm",
		type: "post",
		data: {
				title: title,
				status:status,
				imgPaths:urls,
				adminId:adminId,
				description:description,
				id:discoveryId,
				content:content
			},
		dataType: "json",
		async: true,
		success: function(data) {
		//删除失败
		if (data.status == '1') {
			$.alert({
				title: '恭喜你更新成功',
				content: '\t\t\t----正义的伙伴!',
				confirm: function(){
					window.location.href='admin/discovery/toGetAllDiscoverysPage.htm';
				}
			});
			
		} else {
			$.alert({title: '更新失败,SB',content: '\t\t\t----正义的伙伴!',});
		}
		}
		});
}

function updateUserNotice(html){
	var urls = null;
	var id = $("#id").val();
	var flag = $("#flag").val();
	var adminId = $("#adminId").val();
	var content = editor.exportFile(flag+".md", "text");
	var description = editor.exportFile(flag+".md", "html");
	$.ajax({
			    type: "post",
			    url: "admin/softwareInfomation/editSoftwareInfomation.htm",
				data: {
					id:id,
					description:description,
					imgPaths:urls,
					flag:flag,
					adminId:adminId,
					content:content
				},
			    dataType: 'json',
			    success: function(result) {
			        if (result.status == 1) {
			            $.alert({title: '保存成功',content: '\t\t\t----正义的伙伴!',});	
			        } else {
			           $.alert({title: '保存失败',content: '\t\t\t----正义的伙伴!',});			        }
			    }
			});
}
	
function updateUserAbout(html){
	var urls = null;
	var id = $("#id").val();
	var flag = $("#flag").val();
	var adminId = $("#adminId").val();
	var content = editor.exportFile(flag+".md", "text");
	var description = editor.exportFile(flag+".md", "html");
	$.ajax({
		type: "post",
		url: "admin/softwareInfomation/editSoftwareInfomation.htm",
		data: {
			id:id,
			description:description,
			imgPaths:urls,
			flag:flag,
			adminId:adminId,
			content:content
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 1) {
				$.alert({title: '保存成功',content: '\t\t\t----正义的伙伴!',});
			} else {
				$.alert({title: '保存失败',content: '\t\t\t----正义的伙伴!',});
			}
		}
	});

}

function updateUseExplanationOne(html){
	var urls = getAllUploadUrls();
	var id = $("#id").val();
	var flag = $("#flag").val();
	var adminId = $("#adminId").val();
	var content = editor.exportFile(flag+".md", "text");
	var description = editor.exportFile(flag+".md", "html");

	$.ajax({
		type: "post",
		url: "admin/softwareInfomation/editSoftwareInfomation.htm",
		data: {
			id:id,
			description:description,
			imgPaths:urls,
			flag:flag,
			adminId:adminId,
			content:content
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 1) {
				$.alert({title: '保存成功',content: '\t\t\t----正义的伙伴!',});
			} else {
				$.alert({title: '保存失败,SB',content: '\t\t\t----正义的伙伴!',});
			}
		}
	});
}

function updateUseExplanationTwo(html){
	var urls = getAllUploadUrls();
	var id = $("#id").val();
	var flag = $("#flag").val();
	var adminId = $("#adminId").val();
	var content = editor.exportFile(flag+".md", "text");
	var description = editor.exportFile(flag+".md", "html");

	$.ajax({
		type: "post",
		url: "admin/softwareInfomation/editSoftwareInfomation.htm",
		data: {
			id:id,
			description:description,
			imgPaths:urls,
			flag:flag,
			adminId:adminId,
			content:content
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 1) {
				$.alert({title: '保存成功',content: '\t\t\t----正义的伙伴!',});
			} else {
				$.alert({title: '保存失败',content: '\t\t\t----正义的伙伴!',});
			}
		}
	});
}

function updateUseExplanationThree(){
	var urls = getAllUploadUrls();
	var id = $("#id").val();
	var flag = $("#flag").val();
	var adminId = $("#adminId").val();
	var content = editor.exportFile(flag+".md", "text");
	var description = editor.exportFile(flag+".md", "html");
	$.ajax({
		type: "post",
		url: "admin/softwareInfomation/editSoftwareInfomation.htm",
		data: {
			id:id,
			description:description,
			imgPaths:urls,
			flag:flag,
			adminId:adminId,
			content:content
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 1) {
				$.alert({title: '保存成功',content: '\t\t\t----正义的伙伴!',});
			} else {
				$.alert({title: '保存失败,SB',content: '\t\t\t----正义的伙伴!',});
			}
		}
	});
}

(function (window) {
	"use strict";
	
// Default content to display to EpicEditor
	var mkType = $("#mkType").val();
	var discoveryId = $("#discoveryId").val();


	var text  = "";
	if (mkType ==1 && null != discoveryId && discoveryId!= "" ) {
		buildEditor(discoveryId);
		$('button#updateDiscovery').click(function(){
    		updateDiscovery();
 	 	});

	}else if (mkType == 0 ) {
		$('button#savediscovery').click(function(){
    		saveToServerAjaxCall();

 	 	});
	}else if (mkType == 2) {
		buildEditorForMk(mkType);
		$('button#updateUserNotice').click(function(){
    		updateUserNotice();

 	 	});
	}else if (mkType == 3) {
		buildEditorForMk(mkType);
		$('button#updateUserAbout').click(function(){
    		updateUserAbout();

 	 	});
	}else if (mkType == 4) {
		buildEditorForMk(mkType);
		$('button#updateUseExplanationOne').click(function(){
			updateUseExplanationOne();

		});
	}else if (mkType == 5) {
		buildEditorForMk(mkType);
		$('button#updateUseExplanationTwo').click(function(){
			updateUseExplanationTwo();

		});
	}else if (mkType == 6) {
		buildEditorForMk(mkType);
		$('button#updateUseExplanationThree').click(function(){
			updateUseExplanationThree();

		});
	}

}(window));
//对活动的不可描述
(function (window) {
	"use strict";
// Default content to display to EpicEditor
	var mkType = $("#mkType").val();
	console.log("mkType="+mkType);
	var eventId = $("#eventId").val();
	console.log("eventID="+eventId);
	var text  = "";
	if (mkType == 7 && null != eventId && eventId!= "" ) {
		buildEditorForEvent(eventId);
		$('button#saveEventWithMk').click(function(){
			updateEventWithMK();
		});
		$('button#updateEventWithURL').click(function(){
			updateEventWithURL();
		});

	}else if (mkType == 8 ) {
		$('button#saveEventWithMk').click(function(){
			saveToServerAjaxCall_eventMk();
		});
		$('button#saveEventWithURL').click(function(){
			saveToServerAjaxCall_eventURL();
		});
	}

}(window));
//新建活动Mk
function saveToServerAjaxCall_eventMk(html) {
	var urls = getAllUploadUrls();
	var content = editor.exportFile("README.md", "text");
	var description = editor.exportFile("README.md", "html");
	var title = $("#title").val();
	var status = $("#status").val();
	var adminId = $("#adminId").val();
	var startTime = $("#startTime").val();
	var finishTime =$("#finishTime").val();
	var type = 0;

	if (null == title  || title== "" ) {
		$.alert({title: "请输入标题，sb",content: '----正义的伙伴!'});
		return;
	}

	if (null == status  || status== "" ) {
		$.alert({title: "请选择状态，sb",content: '----正义的伙伴!'});
		return;
	}

	if (null == startTime  || startTime== "" ) {
		$.alert({title: "请输入活动开始时间",content: '----正义的伙伴!'});
		return;
	}

	if (null == finishTime  || finishTime== "" ) {
		$.alert({title: "请输入活动结束时间",content: '----正义的伙伴!'});
		return;
	}

	$.ajax({
		url:"admin/event/addMarkdownEvent.htm",
		type: "post",
		data: {
			title: title,
			status:status,
			imgPaths:urls,
			adminId:adminId,
			description:description,
			content:content,
			startTime:startTime,
			finishTime:finishTime,
			type:type
		},
		dataType: "json",
		async: false,
		success: function(data) {
			//删除失败
			if (data.status == '1') {
				$.alert({
					title: '恭喜你发布成功',
					content: '\t\t\t----正义的伙伴!',
					confirm: function(){
						window.location.href='admin/event/toGetAllEventsPage.htm';
					}

				});

			} else {
				$.alert({title: '发布失败,SB',content: '\t\t\t----正义的伙伴!',});
			}
		}
	});
}
//新建活动URL
function saveToServerAjaxCall_eventURL() {
	var urls = getAllUploadUrls();
	var title = $("#titleURL").val();
	var status = $("#statusURL").val();
	var adminId = $("#adminId").val();
	var content = $("#eventURL").val();
	var startTime = $("#startTimeURL").val() ;// + " 00:00:00";
	var finishTime =$("#finishTimeURL").val(); // + " 00:00:00";

	var description = null;
	var type = 1;

	if (null == title  || title== "" ) {
		$.alert({title: "请输入标题，sb",content: '----正义的伙伴!'});
		return;
	}

	if (null == status  || status== "" ) {
		$.alert({title: "请选择状态，sb",content: '----正义的伙伴!'});
		return;
	}

	if (null == startTime  || startTime== "" ) {
		$.alert({title: "请输入活动开始时间",content: '----正义的伙伴!'});
		return;
	}

	if (null == finishTime  || finishTime== "" ) {
		$.alert({title: "请输入活动结束时间",content: '----正义的伙伴!'});
		return;
	}

	$.ajax({
		url:"admin/event/addMarkdownEvent.htm",
		type: "post",
		data: {
			title: title,
			status:status,
			imgPaths:urls,
			adminId:adminId,
			type:type,
			startTime:startTime,
			finishTime:finishTime,
			content:content,
			description:description
		},
		dataType: "json",
		async: false,
		success: function(data) {
			//删除失败
			if (data.status == '1') {
				$.alert({
					title: '恭喜你发布成功',
					content: '\t\t\t----正义的伙伴!',
					confirm: function(){
						//window.location.href='admin/event/toGetAllEventsPage.htm';
						window.close();
					}

				});

			} else {
				$.alert({title: '发布失败,SB',content: '\t\t\t----正义的伙伴!',});
			}
		}
	});
}

function buildEditorForEvent(eventId){
	$.ajax({
		url:'admin/event/getEventContent.htm',
		//需要链接到服务器地址
		secureuri: false,
		data:{
			eventId:eventId
		},
		type: "get",
		dataType: "json",
		async: true,
		success: function(data) { //相当于java中try语句块的用法

			if (data.status == 1) {

				var content = data.event["content"]
				editor.importFile(eventId+".md",content);

				var imgs=data.event["imgs"];
				if (null != imgs) {
					for (var i = 0; i < imgs.length; i++) {
						addItem(i,imgs[i]);
					}
				}
			}
		},
		error: function(data) {
			$.alert({title: "导入发现",content: '----正义的伙伴!'});
		}
	});
}
