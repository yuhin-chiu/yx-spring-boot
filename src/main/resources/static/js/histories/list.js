$(function() {
    var baseUri = 'history';

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
        field : "name",
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
        title : "创建时间",
        field : "createTime",
        width : 100,
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
            var html = "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
            if(value == 1) {
                html += "<a class='no' href='javascript:void(0)' rowid=" + row.id + ">取消</a>"
            } else {
                html += "<a class='yes' href='javascript:void(0)' rowid=" + row.id + ">展示</a>"
            }
            return html;
        },
        width : 110
    } ];

    function callback(data) {
        $("#num").text(data.total);
        $(".remove").each((i) => {
            $($(".remove")[i]).click(() => {
                var rowid = $($(".remove")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: -1}, (data) => {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("删除成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/histories";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
            $($(".yes")[i]).click(() => {
                var rowid = $($(".yes")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: 1}, (data) => {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("展示成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/histories";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
            $($(".no")[i]).click(() => {
                var rowid = $($(".no")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: 0}, (data) => {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("取消展示成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/histories";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
        });
    }
    $("#autotable").baseTable("/api/history/listAll", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});
