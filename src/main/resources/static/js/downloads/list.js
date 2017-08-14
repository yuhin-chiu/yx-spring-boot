$(function() {

    var baseUri = 'downloads';
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
        align : "center",
        formatter : function(value, index) {
            return "<a href='" + index.url + "'>" + value + "</a>";
        }
    }, {
        title : "类型",
        field : "type",
        align : "center",
        formatter : function(value) {
            switch(value) {
                case 0: return "下载文件";
                case 1: return "酒店推荐";
                case 2: return "参观资料";
            }
            return "";
        }
    }, {
        title : "下载量",
        field : "downs",
        align : "center"
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
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
            if(value == 1) {
                html += "<a class='no' href='javascript:void(0)' rowid=" + row.id + ">关闭</a>"
            } else {
                html += "<a class='yes' href='javascript:void(0)' rowid=" + row.id + ">开启</a>"
            }
            return html;
        }
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
                                window.location.href = "/backend/" + baseUri + "/list";
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
                        window.wxc.xcConfirm("设置首页推荐成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/" + baseUri + "/list";
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
                        window.wxc.xcConfirm("删除首页推荐成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/" + baseUri + "/list";
                            }
                        });
                    } else {
                        console.log("出现异常，请重试！");
                    }
                });
            });
        });
    }
    $("#autotable").baseTable("/api/" + baseUri + "/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});