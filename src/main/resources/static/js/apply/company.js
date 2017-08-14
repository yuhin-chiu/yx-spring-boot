$(function() {
    var baseUri = 'apply/company';

    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val(),
            status: $("#status").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        width : 20,
        align : "center"
    }, {
        title : "公司名称",
        field : "name",
        width : 200,
        align : "center"
    }, {
        title : "负责人",
        field : "principal",
        width : 90,
        align : "center"
    }, {
        title : "手机",
        field : "phone",
        width : 100,
        align : "center"
    }, {
        title : "办公电话",
        field : "tele",
        width : 150,
        align : "center"
    }, {
        title : "公司地址",
        field : "address",
        align : "center",
        width : 200
    }, {
        title : "邮箱",
        field : "mail",
        align : "center"
    }, {
        title : "申请时间",
        field : "applyTime",
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
                html += "<a class='no' href='javascript:void(0)' rowid=" + row.id + ">已处理</a>"
            } else {
                html += "<a class='yes' style='color:red;' href='javascript:void(0)' rowid=" + row.id + ">未处理</a>"
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
                                window.location.href = "/backend/applies/company";
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
                        window.wxc.xcConfirm("处理成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/applies/company";
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
                        window.wxc.xcConfirm("取消成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/applies/company";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
        });
    }
    $("#autotable").baseTable("/api/apply/company/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
    
    $("#status").chosen({
        no_results_text: "没有匹配项"
    }).change($("#autotable").baseTable.query);
});
