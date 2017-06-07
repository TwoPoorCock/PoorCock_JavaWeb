
var sPanel = document.getElementById("SchoolList");
var gPanel = document.getElementById("GradeList");
var cPanel = document.getElementById("ClassList");
var schoolId = -1;
var gradeId = - 1;
var classId = -1;
function closePanel(obj){
  var panelname = obj.id.substring(4,obj.id.length);
  var panel = document.getElementById(panelname);
  panel.setAttribute("style","display:none");
}
function disPlayOnePanel(idx){
  if (idx == 1 ) {
    sPanel.setAttribute("style","display:")
    gPanel.setAttribute("style","display:none");
    cPanel.setAttribute("style","display:none");
  } else if (idx == 2) {
    sPanel.setAttribute("style","display:none")
    gPanel.setAttribute("style","display:");
    cPanel.setAttribute("style","display:none");
  }else if (idx == 3) {

    sPanel.setAttribute("style","display:none")
    gPanel.setAttribute("style","display:none");
    cPanel.setAttribute("style","display:");

  }
}
function findAllSchool(){
  disPlayOnePanel(1);
  $('#schoolTable').dataTable().fnDestroy();
  var schoolTable = $('#schoolTable').DataTable({
    bServerSide:true,
    ajax: {
      url: "admin/school/getAllSchoolsPage.htm",
      dataSrc: 'rows'
    },
    "columns": [
      { "data": "id" },
      { "data": "schoolName" },
      { "data": "provinceName" },
      { "data": "cityName" },
      { "data": "areaName" },
      { "data": "address" }
    ],
  });

  $('#schoolTable tbody').on( 'click', 'tr', function () {
    if ( $(this).hasClass('selected') ) {
      $(this).removeClass('selected');

    }
    else {
      schoolTable.$('tr.selected').removeClass('selected');
      $(this).addClass('selected');
      var showSchool = document.getElementById("showSchool");
      var schoolIdInput = document.getElementById("schoolId");
      var showGrade = document.getElementById("showGrade");
      var showClass = document.getElementById("showClass");
      showGrade.setAttribute("value","");
      showClass.setAttribute("value","");
      classId=-1;
      console.log(schoolTable.$('tr.selected'));
      schoolId = schoolTable.$('tr.selected')[0].cells[0].innerText.trim();
      var schoolName = schoolTable.$('tr.selected')[0].cells[1].innerText.trim();
      showSchool.setAttribute("value",schoolName);

    }
  });
}

function findGrade(){
  disPlayOnePanel(2);
  $('#gradeTable').dataTable().fnDestroy();
  var gradeTable = $('#gradeTable').DataTable({
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
      { "data": "gradeName" }
    ],
  });

  $('#gradeTable tbody').on( 'click', 'tr', function () {
    if ( $(this).hasClass('selected') ) {
      $(this).removeClass('selected');

    }
    else {
      gradeTable.$('tr.selected').removeClass('selected');
      $(this).addClass('selected');
      var showGrade = document.getElementById("showGrade");
      var showClass = document.getElementById("showClass");
      showClass.setAttribute("value","");
      gradeId = gradeTable.$('tr.selected')[0].cells[0].innerText.trim();
      var gradeName = gradeTable.$('tr.selected')[0].cells[1].innerText.trim();
      showGrade.setAttribute("value",gradeName);
    }
  });


}
function findClass(){
  disPlayOnePanel(3);
  $('#classTable').dataTable().fnDestroy();
  var classTable = $('#classTable').DataTable({
    bServerSide:true,
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
      { "data": "className" }
    ],
  });

  $('#classTable tbody').on( 'click', 'tr', function () {
    if ( $(this).hasClass('selected') ) {
      $(this).removeClass('selected');

    }
    else {
      classTable.$('tr.selected').removeClass('selected');
      $(this).addClass('selected');
      var showClass = document.getElementById("showClass");

      classId = classTable.$('tr.selected')[0].cells[0].innerText.trim();
      var className = classTable.$('tr.selected')[0].cells[1].innerText.trim();
      showClass.setAttribute("value",className);
    }
  });
}

function submits(){
  var status = $('#status').val()
  var communityType=$("#communityType").val();
  var classIdInput = document.getElementById("classId");
  classIdInput.setAttribute("value",classId);
  var iclassId=$("#classId").val();
  if (null == status || ""==status) {
    $.alert({title: "SB,状态没选",content: '----正义的伙伴!'});
    return;
  }
  if (null == communityType || ""==communityType) {
    $.alert({title: "我SB,圈子类型是空的",content: '----正义的伙伴!'});
    return;
  }
  if (null == iclassId || ""==iclassId || -1 == iclassId)  {
    $.alert({title: "SB,没有现在班级",content: '----正义的伙伴!'});
    return;
  }
  $.ajax({
			    type: "post",
			    url: "admin/community/addCommunity.htm",
			    data:$("#addForm").serialize(),
			    dataType: 'json',
			    success: function(result) {
			        if (result.status == 1) {
                $.alert({
                  title: '创建成功',
                  content: '\t\t\t----正义的伙伴!',
                  confirm: function(){
                    window.close();
                  }
                });
			        } else {
			            $.alert({title: "SB,创建失败",content: '----正义的伙伴!'});
			        }
			    }
			});
}
