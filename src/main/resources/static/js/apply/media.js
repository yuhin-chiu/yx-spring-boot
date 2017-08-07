$(function() {

    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        width : "20",
        align : "center"
    }, {
        title : "媒体名称",
        field : "name",
        width : "15",
        align : "center"
    }, {
        title : "负责人",
        field : "principal",
        width : "10",
        align : "center"
    }, {
        title : "电话",
        field : "phone",
        width : "20",
        align : "center"
    }, {
        title : "邮箱",
        field : "mail",
        width : "20",
        align : "center"
    }, {
        title : "申请时间",
        field : "applyTime",
        width : "20",
        align : "center",
        formatter : function(value) {
            var date = new Date();
            date.setTime(value * 1000);
            return date.format("yyyy-MM-dd");
        }
    }, {
        title : "操作",
        field : "status",
        width : "20",
        align : "center",
        formatter : function(value) {
            return value;
        }
    } ];

    function callback(data) {
        $("#num").text(data.total);
    }
    $("#autotable").baseTable("/api/apply/media/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});
