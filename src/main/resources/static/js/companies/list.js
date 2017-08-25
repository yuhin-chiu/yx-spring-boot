$(function() {
    var baseUri = 'company';
    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        align : "center",
        width : 20
    }, {
        title : "公司名称",
        field : "name",
        align : "center",
        width : 200
    }, {
        title : "首页图片",
        field : "url",
        align : "center",
        width : 40,
        formatter : function(value) {
            if(value) {
                return "<img style='width:25px;height:25px;' src='"+ value + "' />";
            }
            return "";
        }
    }, {
        title : "负责人",
        field : "principal",
        align : "center",
        width : 90
    }, {
        title : "手机",
        field : "phone",
        align : "center",
        width : 100
    }, {
        title : "办公电话",
        field : "tele",
        align : "center",
        width : 150
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
        field : "createTimeStr",
        align : "center",
        width : 100
    }, {
        title : "操作",
        field : "status",
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
            if(value == 1) {
                html += "<a class='no' href='javascript:void(0)' rowid=" + row.id + ">关闭</a>"
            } else {
                html += "<a class='yes' href='javascript:void(0)' rowid=" + row.id + ">开启</a>"
            }
            return html;
        },
        width : 100
    } ];

    function callback(data) {
        $("#num").text(data.total);
        $(".remove").each(function(i) {
            $($(".remove")[i]).click(function() {
                var rowid = $($(".remove")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: -1}, function(data) {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("删除成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/companies/list";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
            $($(".yes")[i]).click(function() {
                var rowid = $($(".yes")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: 1}, function(data) {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("设置首页推荐成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/companies/list";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
            $($(".no")[i]).click(function() {
                var rowid = $($(".no")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: 0}, function(data) {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("删除首页推荐成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/companies/list";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
        });
    }
    $("#autotable").baseTable("/api/company/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});
