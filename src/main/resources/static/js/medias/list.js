$(function() {
    
    var baseUri = 'medias';
    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val(),
            title: $("#modal-title").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        align : "center",
        width : 20
    }, {
        title : "媒体名",
        field : "name",
        align : "center"
    }, {
        title : "图片",
        field : "image",
        align : "center",
        formatter : function(value) {
            if(value) {
                return "<img style='width:30px;height:30px;' src='"+ value + "' />";
            }
            return "";
        },
        width : 40
    }, {
        title : "链接",
        field : "url",
        align : "center"
    }, {
        title : "负责人",
        field : "principal",
        align : "center"
    }, {
        title : "手机",
        field : "phone",
        align : "center"
    }, {
        title : "申请时间",
        field : "applyTime",
        align : "center",
        formatter : function(value) {
            var date = new Date();
            date.setTime(value * 1000);
            return date.format("yyyy-MM-dd");
        }
    }, {
        title : "操作",
        field : "status",
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a href='javascript:void(0)' onclick='edit(" + row.id + ")'>编辑</a>&nbsp;" +
                "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
            return html;
        },
        width: 90
    } ];

    $("#autotable").baseTable("/api/medias/list", columns, getOtherCondition);

    $("#queryBtn").click($("#autotable").baseTable.query);
    
    $("#modal-table").baseModal(baseUri, $("#autotable").baseTable);

});
