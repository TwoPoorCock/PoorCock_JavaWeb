var gradeId = -1;
var schoolId = -1;
$(document).ready(function() {
 schoolId  = $('#schoolId').val();
  var rowcount = 0 ;

  var table = $('#dataTables').DataTable({
    bServerSide:true,
    ajax: {
      url: "admin/school/getAllGradesPage.htm",
      data:{
        schoolId:schoolId
      },
      dataSrc: 'rows'
    },
    "columns": [
      { "data": "id" },
      { "data": "gradeName" },
      { "data": "id" },
      { "data": "id" }
    ],
    "columnDefs": [
        { "name":"id","targets": 0},
        {
            "name":"gradeName",
            "targets": 1,
            "searchable": false,
            "render": function(data, type, row ) {
                return   '<a href="'+'admin/school/toEditGradePage.htm?gradeId='+row.id+'">'+data+'</a>';
            }
        },
        {
            "name":"id",
            "targets": 2,
            "searchable": false,
            "render": function(data, type, row ) {
                var count = row.gradeIdCount;
                if (count == null){ count = 0; }
                return    "<button type=\"button\" class=\"btn btn-outline btn-link\" id=\"grade"+data+"\" onclick=\"createClassPanel(this)\"><i class=\"fa fa-hand-o-right\"> </i>班级("+count+")</button>"
                //return    "<button type=\"button\"  id=\"grade"+data+"\" class=\"btn btn-outline btn-primary  btn-block\" onclick=\"createClassPanel(this)\">查看班级("+data+")</button>"
            }
        },
        {
            "name":"id",
            "targets": 3,
            "searchable": false,
            "render": function(data, type, row ) {
                return    "<button type=\"button\" class=\"btn btn-outline btn-link\" id=\"grade"+data+"\" onclick=\"delGrades(this)\"><i class=\"fa fa-times\"> </i>删除</button>"
                //return    "<button type=\"button\" id=\"grade"+data+"\" class=\"btn btn-outline btn-danger  btn-block\" onclick=\"delGrades(this)\">删除</button>"
            }
        }
      ],
    "fnDrawCallback": function (oSettings) {

      if (oSettings._iRecordsTotal == 0 ) {
        var gradePanel = document.getElementById("gradePanel");
        var noDataTips = document.getElementById("noDataTips");
        gradePanel.setAttribute("style","display:none");
        noDataTips.setAttribute("style","display:");
      } ;
   }
  });

    var num=0;
    var arr = new Array(num);
    $('#table tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            arr.splice(arr.indexOf($(this)[0].cells[0].innerText),1);
            $(this).removeClass('selected');
        }
        else {
//				table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            arr.push($(this)[0].cells[0].innerText);
        }
    } );

  $('#newGrades').click( function () {
    //parent.addTab("新增圈子", "<%=basePath%>admin/community/toAddCommunity.htm");
    window.open("admin/school/toAddGrade.htm?schoolId=${school.id}");
  });

  $('#delGrades').click( function () {
    if (isSelect(table)==0) {return;}
    var id = table.$('tr.selected')[0].cells[0].innerText;
    $.confirm({
      title: '此操作同时会删除该年级下的所有班级',
      content: '\n\t\t\t----正义的伙伴!',
      confirm: function(){
        $.ajax({
            type: 'GET',
            url: "admin/school/deleteGrade.htm?gradeId="+id,
            dataType: 'json',
            success: function(result) {
                if (result.status == 1) {

                $.alert({
                  title: '删除成功',
                  content: '\t\t\t----正义的伙伴!',
                  confirm: function(){
                    table.draw(false);
                  }
                });
                } else {
                    $.alert({title: '删除失败',content: '\t\t\t----正义的伙伴!'});
                }
            }
        });
      },
      cancel:function(){

      }
    });
  });
});


function delGrades(obj){
  var  gradeId = obj.id.substring(5,obj.id.length);
  var gredeTable = $('#dataTables').DataTable();
  $.confirm({
    title: '此操作同时删除该年级的所有班级',
    content: '\n\t\t\t----正义的伙伴!',
    confirm: function(){
      $.ajax({
          type: 'GET',
          url: "admin/school/deleteGrade.htm?gradeId="+gradeId,
          dataType: 'json',
          success: function(result) {
              if (result.status == 1) {

              $.alert({
                title: '删除成功',
                content: '\t\t\t----正义的伙伴!',
                confirm: function(){
                  gredeTable.draw(false);
                }
              });
              } else {
                  $.alert({title: '删除失败',content: '\t\t\t----正义的伙伴!'});
              }
          }
      });
    },
    cancel:function(){

    }
  });


}
function closePanel(obj){
  var panelname = obj.id.substring(4,obj.id.length);
  var panel = document.getElementById(panelname);
  panel.setAttribute("style","display:none");
}

function closeTips(obj){
  var name  = obj.id.substring(6,obj.id.length);
    console.log(name);
  var panel = document.getElementById(name);
  panel.setAttribute("style","display:none");
}

function createClassPanel(obj)
{
  var classPanel = document.getElementById("classPanel");
  var communityPanel = document.getElementById("communityPanel");
  communityPanel.setAttribute('style',"display:none");
  classPanel.setAttribute("style","display:");
  var  gradeId = obj.id.substring(5,obj.id.length);
  $('#classTable').dataTable().fnDestroy();
  var table = $('#classTable').DataTable({
    bServerSide:true,
    searching:true,
    ajax: {
      url: "admin/school/getAllClassesPage.htm",
      data:{
        schoolId:schoolId,
        gradeId:gradeId
      },
      dataSrc: 'rows'
    },
    "columns": [
        { "data": "id" },
        { "data": "className" },
        { "data": "id" },
        { "data": "id" },
    ],
    "columnDefs": [
        { "name":"id","targets": 0},
        {
            "name":"className",
            "targets": 1,
            "render": function(data, type, row ) {
              return   '<a href="'+'admin/school/toEditClassPage.htm?classId='+row.id+'">'+data+'</a>';
            }
        },
        {
            "name":"id",
            "targets": 2,
            "render": function(data, type, row ) {
                var count = row.classIdCount;
                if(count == null){ count = 0; }
                return    "<button type=\"button\" class=\"btn btn-outline btn-link\" id=\"class"+data+"\" onclick=\"createCommunityPanel(this)\"><i class=\"fa fa-hand-o-right\"> </i>圈子("+count+")</button>"
            }
        },
        {
            "name":"id",
            "targets": 3,
            "render": function(data, type, row ) {
                return    "<button type=\"button\" class=\"btn btn-outline btn-link\" id=\"class"+data+"\" onclick=\"delClass(this)\"><i class=\"fa fa-times\"> </i>删除</button>"

            }
        }
      ],
    "fnDrawCallback": function (oSettings) {
      var classPanel = document.getElementById("classDataDiv");
      var noDataTips = document.getElementById("noClassDataTips");
      var grade = document.getElementById("gradeId");
      if (oSettings._iRecordsTotal == 0 ) {

        classPanel.setAttribute("style","display:none")
        noDataTips.setAttribute("style","display:");
      }else{
        classPanel.setAttribute("style","display:")
        noDataTips.setAttribute("style","display:none");
      }
      grade.setAttribute("value",gradeId);

   }
  });

}

function delClass(obj){
  var  classId = obj.id.substring(5,obj.id.length);
  var classTable = $('#classTable').DataTable();
  $.confirm({
    title: '此操作同时删除该班级的所有圈子',
    content: '----正义的伙伴!',
    confirm: function(){
      $.ajax({
          type: 'GET',
          url: "admin/school/deleteClass.htm?classId="+classId,
          dataType: 'json',
          success: function(result) {
              if (result.status == 1) {

              $.alert({
                title: '删除成功',
                content: '\t\t\t----正义的伙伴!',
                confirm: function(){
                  classTable.draw(false);
                }
              });
              } else {
                  $.alert({title: '删除失败',content: '\t\t\t----正义的伙伴!'});
              }
          }
      });
    },
    cancel:function(){

    }
  });


}

function createCommunityPanel(obj){
  var communityPanel = document.getElementById("communityPanel");
  communityPanel.setAttribute("style","display:");

  var  classId = obj.id.substring(5,obj.id.length);
  $('#communityTable').dataTable().fnDestroy();
  var table = $('#communityTable').DataTable({
    bServerSide:true,
    searching:true,
    ajax: {
      url: "admin/school/getCommunityByClassPage.htm",
      data:{
        classId:classId
      },
      dataSrc: 'rows'
    },
    "columns": [
      { "data": "id" },
      { "data": "communityName" }
    ],
      
      
    "fnDrawCallback": function (oSettings) {
      var CommunityDataDiv = document.getElementById("CommunityDataDiv");
      var noCommunityDataTips = document.getElementById("noCommunityDataTips");

      if (oSettings._iRecordsTotal == 0 ) {
        CommunityDataDiv.setAttribute("style","display:none")
        noCommunityDataTips.setAttribute("style","display:");
      }else{
        CommunityDataDiv.setAttribute("style","display:")
        noCommunityDataTips.setAttribute("style","display:none");
      }

   }
  });

}

function  addGrade(){
  var newGradeName =  $("#gradeName" ).val();


  if (newGradeName== null || newGradeName == "" ) {
     $.alert({title: '年级名称,不能为空',content: '----正义的伙伴!'});
     return;
  }
  var gredeTable = $('#dataTables').DataTable();
  $.ajax({
          type: "post",
          url: "admin/school/addGrade.htm",
          data:$("#addradeForm").serialize(),
          dataType: 'json',
          success: function(result) {
              if (result.status == 1) {
                  $.alert({
                    title: '增加年级成功',
                    content: '\t\t\t----正义的伙伴!',
                    confirm: function(){
                      gredeTable.draw(false);
                  }
                });
              } else {
                  $.alert({title: '增加年级失败',content: '----正义的伙伴!'});
              }
          }
      });


}

function addClass(){
  var className =  $("#className").val();
  var gradeId =    $("#gradeId").val();

  if (className== null || className == "" ) {
     $.alert({title: '年级名称,不能为空',content: '----正义的伙伴!'});
     return;
  }
 var classTable = $('#classTable').DataTable();
  $.ajax({
          type: "post",
          url: "admin/school/addClass.htm",
          data:$("#addClassForm").serialize(),
          dataType: 'json',
          success: function(result) {
              if (result.status == 1) {
                  $.alert({
                    title: '增加班级成功',
                    content: '---正义的伙伴!',
                    confirm: function(){
                      classTable.draw(false);
                  }
                });
              } else {
                  $.alert({title: '增加班级失败',content: '----正义的伙伴!'});
              }
          }
      });
}

  function backPage(obj){
    window.history.back(-1);
  }
