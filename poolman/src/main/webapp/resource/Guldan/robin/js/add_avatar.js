var isIE = /msie/i.test(navigator.userAgent) && !window.opera;

function avatarUpload(obj,build){
 	var fileId = $(obj).attr("id");
    var index =fileId.substring(4,fileId.length);
	var file = $("#"+fileId).val();
	//获取欲上传的文件路径
	var allowExtention = ".jpg,.bmp,.gif,.png"; //允许上传文件的后缀名
    var extention = file.substring(file.lastIndexOf(".") + 1).toLowerCase();	 	
    if (allowExtention.indexOf(extention) <0) {
	    $(obj).val("");
	    $.alert({title: "仅支持" + allowExtention + "为后缀名的文件!",content: '----正义的伙伴!'});
	    return ;
	}

	//上传的文件的大小判断
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
	    return ;
	} 

	// updload file 
	var avatarURL = "";
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
	    	avatarURL = data.absoluteSrc;
	    	build(avatarURL)  	 
	    	},
		error: function(data) {
	    	$.alert({title: "上传失败",content: '----正义的伙伴!'});

	    	 
		}
	});


}
var imgIndex = -1 ; 
function buildUserAvatar(url){
	var userAvatarImg = document.getElementById("userAvatarImg"+imgIndex)
	userAvatarImg.setAttribute("src", url);
}
function ajaxFileUpload(obj) {
	var fileId = $(obj).attr("id");
	imgIndex = fileId.substring(10,fileId.length);
	avatarUpload(obj,buildUserAvatar);

}