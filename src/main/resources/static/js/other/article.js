/**
 * Created by jiaoyuxuan on 2017/8/8.
 */
$(function() {
    var contentMax = 20000;
    var oldContent;
    var events = {
        count_textarea : function() {
            var num = $(this).val().length;
            var count = $(".count");
            if (num > contentMax) {
                count.css('color', 'red');
            } else {
                count.css('color', 'black');
            }
        },
        publish : function() {
            var id = $('#title').val();
            var content = window.editor.html();

            if (id == -1) {
                window.wxc.xcConfirm("请选择文章标题！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if (oldContent == content) {
                window.wxc.xcConfirm("你并没有做任何修改！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if (title.length == 0 || parent.length == 0 || content.length == 0) {
                window.wxc.xcConfirm("请补充完整信息！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if ( parseInt($('.count').text()) > contentMax) {
                window.wxc.xcConfirm("内容字数超限！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else {
                var formData = new FormData();
                formData.append('id', id);
                formData.append('content', content);
                $.ajax({
                            url : '/api/articles/editContent',
                            type : 'post',
                            data : formData,
                            processData : false,
                            contentType : false,
                            success : function(data) {
                                $(".mask").hide();
                                if (data.code == 200) {
                                    window.wxc.xcConfirm(
                                                    "修改成功！",
                                                    window.wxc.xcConfirm.typeEnum.success);
                                } else {
                                    window.wxc.xcConfirm(
                                                    "修改失败！",
                                                    window.wxc.xcConfirm.typeEnum.error);
                                }
                            },
                            error : function(err) {
                                $(".mask").hide();
                                window.wxc.xcConfirm("修改失败！",
                                        window.wxc.xcConfirm.typeEnum.error);
                            }
                        });

            }
        },
        update_title: function() {
            var parent = $("#parent").val();
            if(parent == -1) {
                return;
            }
            var params = {parent: parent};
            $.post("/api/articles/getTitles", params, function (data) {
                var html = '<option value="-1" selected="selected">--选择标题--</option>';
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    html += '<option value="' + item.id + '">' + item.title + '</option>'
                }
                $("#title").html(html).trigger("chosen:updated");
            });
        },
        update_content: function() {
            var title = $("#title").val();
            if(title == -1) {
                return;
            }
            var params = {id: title};
            $.post("/api/articles/getContent", params, function (data) {
                oldContent = data.content;
                window.editor.html(data.content);
            });
        }
    };

    function event_bind() {
        $("#parent").chosen({
            no_results_text: "没有匹配项"
        }).change(events.update_title);
        $("#title").chosen({
            no_results_text: "没有匹配项"
        }).change(events.update_content);
        $("#content").keyup(events.count_textarea);
        $("#publish").click(events.publish);
    }

    function init() {
        event_bind();
    }

    init();
});