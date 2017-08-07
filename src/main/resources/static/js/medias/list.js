$(function() {

    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        align : "center"
    }, {
        title : "媒体名",
        field : "name",
        align : "center"
    }, {
        title : "负责人",
        field : "principal",
        align : "center"
    }, {
        title : "图片",
        field : "image",
        align : "center"
    }, {
        title : "链接",
        field : "url",
        align : "center"
    }, {
        title : "手机",
        field : "phone",
        align : "center"
    }, {
        title : "办公电话",
        field : "tele",
        align : "center"
    }, {
        title : "邮箱",
        field : "mail",
        align : "center"
    }, {
        title : "地址",
        field : "address",
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
        formatter : function(value) {
            if(value == 1) {
                return "推荐";
            }
            return "不推荐";
        }
    } ];

    function callback(data) {
        $("#num").text(data.total);
    }
    $("#autotable").baseTable("/api/medias/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});
