(function($){

	function removeALlNode(node){
            var children = node.childNodes;
            var childnum = children.length;
            for (var j = 0; j < childnum; j++) {
                node.removeChild(node.firstChild);
            }
	}
	function onProvinceChange(){


		var obj = $(this);
		
		if($(this).val() == null || $(this).val() == ""){
			$(this).next(":eq(0)").empty().append("<option value=''>--请选择--</option>");
			$(this).next(":eq(0)").next().empty().append("<option value=''>--请选择--</option>");
			return;
		}
		
		$.ajax({
			url : $("base")[0].href + "admin/area/getArea.htm",
			data:{
				level:2,
				parentCode:$(this).val()
			},
			dataType : 'json',
			async: false,
			success : function(result) {
				var citySelect = document.getElementById("city"); // 这个要优化
				var pSText = document.createTextNode("--请选择--");
				var pOption = document.createElement("option");
				removeALlNode(citySelect);
				citySelect.appendChild(pOption);
				citySelect.appendChild(pOption);
            	pOption.appendChild(pSText);

            	var areaSelect = document.getElementById("area"); // 这个要优化
				var pSText = document.createTextNode("--请选择--");
				var pOption = document.createElement("option");
				removeALlNode(areaSelect);
				areaSelect.appendChild(pOption);
				areaSelect.appendChild(pOption);
            	pOption.appendChild(pSText);



				//var citySelect = obj.next(":eq(0)"); 
				for(var i=0;i<result.areas.length;i++){
					var  pOption = document.createElement("option");
					var textNode  = document.createTextNode(result.areas[i].name);
					citySelect.appendChild(pOption);
					pOption.appendChild(textNode);
					pOption.setAttribute("value",result.areas[i].code)
	
				}



				
			}
		});
	}
	function onCityChange(){

		var obj = $(this);
		if($(this).val() == null || $(this).val() == ""){
			$(this).next(":eq(0)").empty().append("<option value=''>--请选择--</option>");
			return;
		}
		$.ajax({
			url : $("base")[0].href + "admin/area/getArea.htm",
			data:{
				level:3,
				parentCode:$(this).val()
			},
			dataType : 'json',
			async: false,
			success : function(result) {

				var areaSelect = document.getElementById("area"); // 这个要优化
				var pSText = document.createTextNode("--请选择--");
				var pOption = document.createElement("option");
				removeALlNode(areaSelect);
				areaSelect.appendChild(pOption);
				areaSelect.appendChild(pOption);
            	pOption.appendChild(pSText);

				//var citySelect = obj.next(":eq(0)"); 
				for(var i=0;i<result.areas.length;i++){
					var  pOption = document.createElement("option");
					var textNode  = document.createTextNode(result.areas[i].name);
					areaSelect.appendChild(pOption);
					pOption.appendChild(textNode);
					pOption.setAttribute("value",result.areas[i].code)
	
				}
			}
		});
	}
	
	$.extend({
		"hidePanel": function (panelId){
			$("#"+panelId).panel("panel").hide();
		},
		"getNewButton": function(id, opt, text, iconCls){
			return "<a id=\"" + id + "\" href=\"javascript:void(0);\" class=\"l-btn l-btn-plain\" onclick=\"" + opt 
			+ "\"><span class=\"l-btn-left\"><span class=\"l-btn-text " + iconCls + " l-btn-icon-left\">" + text + "</span></span></a>";
		},



		"getAddressSelect": function(parentId,style,nameArray,idArray,defaultValue){


            var parent = document.getElementById(parentId); 
                 	             
            var pDiv = document.createElement("div");
            var pSpan = document.createElement("span");
            var pI = document.createElement("i");
            var pText = document.createTextNode("省份");
            var pSText = document.createTextNode("--请选择--");
            var pSelect = document.createElement("select");
            var pOption = document.createElement("option");

            pDiv.setAttribute("class","form-group input-group");
            pSpan.setAttribute("class","input-group-addon");
            pI.setAttribute("class"," fa fa-plane");
            pSelect.setAttribute("class","form-control");
            pSelect.setAttribute("name",nameArray[0]);
            pSelect.setAttribute("id",idArray[0]);
            parent.appendChild(pDiv);
            pDiv.appendChild(pSpan);
            pDiv.appendChild(pSelect);
            pSpan.appendChild(pI);
            pSpan.appendChild(pText);
            pSelect.appendChild(pOption);
            pOption.appendChild(pSText);



            var cDiv = document.createElement("div");
            var cSpan = document.createElement("span");
            var cI = document.createElement("i");
            var cText = document.createTextNode("城市");
            var cSelect = document.createElement("select");
            var cOption = document.createElement("option");
            var cSText = document.createTextNode("--请选择--");

            cDiv.setAttribute("class","form-group input-group");
            cSpan.setAttribute("class","input-group-addon");
            cI.setAttribute("class"," fa  fa-eye");
            cSelect.setAttribute("class","form-control");
            cSelect.setAttribute("name",nameArray[1]);
            cSelect.setAttribute("id",idArray[1]);
            parent.appendChild(cDiv);
            cDiv.appendChild(cSpan);
            cDiv.appendChild(cSelect);
            cSpan.appendChild(cI);
            cSpan.appendChild(cText);
            cSelect.appendChild(cOption);
            cOption.appendChild(cSText);



            var dDiv = document.createElement("div");
            var dSpan = document.createElement("span");
            var dI = document.createElement("i");
            var dText = document.createTextNode("区域");
            var dSelect = document.createElement("select");
            var dOption = document.createElement("option");
            var dSText = document.createTextNode("--请选择--");
            dDiv.setAttribute("class","form-group input-group");
            dSpan.setAttribute("class","input-group-addon");
            dI.setAttribute("class"," fa fa-magnet");
            dSelect.setAttribute("class","form-control");
            dSelect.setAttribute("name",nameArray[2]);
            dSelect.setAttribute("id",idArray[2]);
            parent.appendChild(dDiv);
            dDiv.appendChild(dSpan);
            dDiv.appendChild(dSelect);
            dSpan.appendChild(dI);
            dSpan.appendChild(dText);
            dSelect.appendChild(dOption);
            dOption.appendChild(dSText);

			$.ajax({
				url : $("base")[0].href + "admin/area/getThreeLevelAll.htm?code="+defaultValue,
				dataType : 'json',
				async: false,
				success : function(result) {
					var provinces = result.data.provinces;
					var cities = result.data.cities;
					if(typeof(cities) == "undefined"){cities = [];}
					var districts = result.data.districts;
					if(typeof(districts) == "undefined"){districts = [];}
					for(var i=0;i<provinces.length;i++){
						$("#"+idArray[0]).append("<option value='"+provinces[i].code+"'>"+provinces[i].name+"</option>");
					}
					for(var i=0;i<cities.length;i++){
						$("#"+idArray[1]).append("<option value='"+cities[i].code+"'>"+cities[i].name+"</option>");
					}
					for(var i=0;i<districts.length;i++){
						$("#"+idArray[2]).append("<option value='"+districts[i].code+"'>"+districts[i].name+"</option>");
					}
					$("#"+idArray[0]).find("option[value='"+result.data.province+"']").prop("selected",true);
					$("#"+idArray[1]).find("option[value='"+result.data.city+"']").prop("selected",true);
					$("#"+idArray[2]).find("option[value='"+result.data.district+"']").prop("selected",true);
				}
			});
			$("#"+idArray[0]).change(onProvinceChange);
			$("#"+idArray[1]).change(onCityChange);
		},
		"getAddressSelect2": function(parentId,style,nameArray,idArray,defaultValue){



			var province = "<select name='"+nameArray[0]+"' id='"+idArray[0]+"' style='"+style+"'><option value=''>--请选择--</option></select>";
			var city = "<select name='"+nameArray[1]+"' id='"+idArray[1]+"' style='"+style+"'><option value=''>--请选择--</option></select>";
			$("#"+parentId).append(province).append(city);




			$.ajax({
				url : $("base")[0].href + "admin/area/getThreeLevelAll.htm?code="+defaultValue,
				dataType : 'json',
				async: false,
				success : function(result) {
					var provinces = result.data.provinces;
					var cities = result.data.cities;
					if(typeof(cities) == "undefined"){cities = [];}
					for(var i=0;i<provinces.length;i++){
						$("#"+idArray[0]).append("<option value='"+provinces[i].code+"'>"+provinces[i].name+"</option>");
					}
					for(var i=0;i<cities.length;i++){
						$("#"+idArray[1]).append("<option value='"+cities[i].code+"'>"+cities[i].name+"</option>");
					}
					$("#"+idArray[0]).find("option[value='"+result.data.province+"']").prop("selected",true);
					$("#"+idArray[1]).find("option[value='"+result.data.city+"']").prop("selected",true);
				}
			});
			$("#"+idArray[0]).change(onProvinceChange);
		}
	});
	
	
	
	
	
	
	var defaultGrid = {
		fit:true,
		fitColumns:false,
		striped:true,
		loadMsg:"正在加载，请稍后……",
		pagination:true,
		rownumbers:true,
		singleSelect:false,
		selectOnCheck: true,
		checkOnSelect: true,
		pageSize:10,
	    pageList:[5,10,20,50],
		onLoadSuccess:function(data){
			/*if (data.total == 0) {
			    var array = $(this).datagrid("options").columns[0];
			       for (var i = 0; i < array.length; i++) {
			        if ((typeof(array[i].hidden) == "undefined" || !array[i].hidden) && (typeof(array[i].checkbox) == "undefined" || !array[i].checkbox)) {
			            var key = array[i].field;
			            var row = {};
			            row[key] = "<div style='text-align:center;color:red'>没有查询到数据……</div>";
			            $(this).datagrid("appendRow", row).datagrid('mergeCells', {
			                index: 0,
			                field: key,
			                colspan: array.length
			            });
			            break;
			        }
			    }
			} else {
			}*/
		}
	};
	
	$.fn.extend({
		"grid": function(opts){
			var newOpts = $.extend(defaultGrid,opts);
			$(this).datagrid(newOpts);
		}
	});
})(jQuery);