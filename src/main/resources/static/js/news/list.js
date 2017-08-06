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
        title : "标题",
        field : "title",
        align : "center"
    }, {
        title : "作者",
        field : "author",
        align : "center"
    }, {
        title : "所属栏目",
        field : "parent",
        align : "center"
    }, {
        title : "内容",
        field : "content",
        align : "center",
        formatter : function(value) {
            if(value.length <= 30) {
                return value
            }
            return value.substring(0, 30);
        }
    }, {
        title : "发布时间",
        field : "createTime",
        align : "center",
        formatter : function(value) {
            var date = new Date();
            date.setTime(value * 1000);
            return date.format("yyyy-MM-dd");
        }
    }, {
        title : "操作",
        field : "status",
        align : "center"
    } ];

    function callback(data) {
        $("#num").text(data.total);
    }
    $("#autotable").baseTable("/api/news/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});