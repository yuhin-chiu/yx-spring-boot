$(function() {

    var baseUri = 'activities';
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
        title : "摘要",
        field : "abstr",
        align : "center"
    }, {
        title : "图片",
        field : "url",
        align : "center",
        formatter : function(value) {
            if(value) {
                return "<img style='width:30px;height:30px;' src='"+ value + "' />";
            }
            return "";
           
        }
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
        field : "createTimeStr",
        align : "center"
    }, {
        title : "操作",
        field : "status",
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a href='javascript:void(0)' onclick='edit(" + row.id + ")'>编辑</a>&nbsp;"
                + "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
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
        $(".remove").each(function(i) {
            $($(".remove")[i]).click(function() {
                var rowid = $($(".remove")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: -1}, function(data) {
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
            $($(".yes")[i]).click(function() {
                var rowid = $($(".yes")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: 1}, function(data) {
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
            $($(".no")[i]).click(function() {
                var rowid = $($(".no")[i]).attr("rowid");
                $.post("/api/" + baseUri + "/edit/"+rowid, {status: 0}, function(data) {
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
    
    $("#modal-table").baseModal(baseUri, $("#autotable").baseTable);

});
