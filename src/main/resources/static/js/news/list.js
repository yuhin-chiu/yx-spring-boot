$(function() {

    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val(),
            query: $("#title").val()
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
        align : "center",
        formatter : (value) => {
            switch(value) {
                case 0: return "展会资讯";
                case 1: return "展商新闻";
                case 2: return "行业资讯";
                default : return "";
            }
        }
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
                return value;
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
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
            if(value == 0) {
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
                $.post("/api/news/edit/"+rowid, {status: -1}, (data) => {
                    if(data.code == 200) {
                        window.wxc.xcConfirm("删除成功！", window.wxc.xcConfirm.typeEnum.success, {
                            onOk: function(v) {
                                window.location.href = "/backend/news/list";
                            }
                        });
                    } else {
                        console.log("error");
                    }
                });
            });
        });
    }
    $("#autotable").baseTable("/api/news/list", columns, getOtherCondition, callback);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#queryBtn").click($("#autotable").baseTable.query);
});
