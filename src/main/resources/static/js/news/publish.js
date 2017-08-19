/**
 * Created by jiaoyuxuan on 2017/8/8.
 */
$(function() {
    var curFiles = [], fileNames = [], imgTypes = [ 'jpg', 'png', 'jpeg' ];
    var docTypes = [ 'doc', 'docx', 'xls', 'xlsx', 'xlsm', 'ppt', 'pptx' ];
    var titleMax = 50;
    var abstrMax = 100;
    var contentMax = 20000;
    var fileNumMax = 1;
    var fileNumMin = 1;
    var events = {
        count_char : function() {
            var num = $(this).val().length;
            var type = $(this).attr('name');
            var max = type == 'title' ? titleMax : abstrMax;

            $("#" + type + "Count").text(num);
            if (num > max) {
                $("#" + type + "Count").css('color', 'red');
            } else {
                $("#" + type + "Count").css('color', 'black');
            }
        },
        count_textarea : function() {
            var num = $(this).val().length;
            var count = $(".count");
            count.text(num);
            console.log(num + ": " + contentMax);
            if (num > contentMax) {
                count.css('color', 'red');
            } else {
                count.css('color', 'black');
            }
        },
        display_file_upload : function() {
            $("input[name=files]").click();
        },
        preview_file : function() {
            var files = $(this)[0].files;
            var imgCount = $("#imgs").children().length / 2;
            for (var i = 0; i < files.length; i++) {
                var name = files[i].name;
                if (fileNames.indexOf(name) != -1) {// 如果已经上传
                    continue;
                }
                if (curFiles.length >= fileNumMax) {
                    window.wxc.xcConfirm("图片数量不能超过" + fileNumMax + "！",
                            window.wxc.xcConfirm.typeEnum.info);
                    break;
                }
                var infos = name.split('.');
                var type = infos[infos.length - 1];
                var status = $('input[name=status]:checked');
                if (imgTypes.indexOf(type) == -1) { // 当前只支持图片上传
                    window.wxc.xcConfirm("只支持图片上传！（jpg、jpeg、png）",
                            window.wxc.xcConfirm.typeEnum.info);
                    continue;
                }
                var url = window.URL.createObjectURL(files[i]);

                if (imgTypes.indexOf(type) != -1) {
                    var img = $('<img src="'
                            + url
                            + '" title="点击右上角移除" class="img" style="width: 170px; height: 100px;"/>');
                    var remove = $('<div class="remove" fileName="'
                            + name
                            + '" style="width: 20px; height: 20px; display: inline-block; position: relative; left: -23px; bottom: 37px;">'
                            + '<img src="/img/delete.png" style="pointer-events: auto; width: 20px; height: 20px;"/></div>');

                    if (imgCount > 2) {
                        img.css('margin-top', '10px');
                        remove.css('bottom', '32px');
                    }
                    $("#imgs").append(img).append(remove);
                    imgCount++;
                    curFiles.push(files[i]);
                    fileNames.push(name);
                } else if (docTypes.indexOf(type) != -1) {
                    var a = $('<a href="#">'
                            + name
                            + '</a><a class="delete" fileName="'
                            + name
                            + '" style="margin-left: 5px"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true" style="color: dimgray"></span></a><br/>');
                    $("#files").append(a);
                    curFiles.push(files[i]);
                    fileNames.push(name);
                } else {
                    window.wxc.xcConfirm(
                            "只能上传word、ppt、excel及图片（jpg、jpeg、png）！",
                            window.wxc.xcConfirm.typeEnum.error);
                }
            }

            if ($('#files').children().length != 0
                    || $('#imgs').children().length != 0) {
                $('#upload').css('margin-top', '10px');
                if ($('#imgs').children().length != 0) {
                    $("#files").css('margin-top', '10px');
                }
            } else {
                $('#upload').css('margin-top', '0');
                $("#files").css('margin-top', '0');
            }

            $('.remove').click(function() {
                var fileName = $(this).attr('fileName');
                $(this).prev().remove();
                $(this).remove();
                curFiles = curFiles.filter(function(file) {
                    return file.name != fileName;
                });
                fileNames = fileNames.filter(function(name) {
                    return name != fileName;
                });
                imgs = $('#imgs').children();
                for (var i = 0; i < imgs.length; i++) {
                    if (i < 3) {
                        imgs[i].css('margin-top', '0');
                    } else {
                        break;
                    }
                }
                if (imgs.length == 0) {
                    $("#files").css('margin-top', '0');
                    if ($("#files").children().length == 0) {
                        $('#upload').css('margin-top', '0');
                    }
                }
            });

            $('.delete').mouseover(function() {
                $(this).children().css('color', 'red');
            }).mouseout(function() {
                $(this).children().css('color', 'dimgray');
            }).click(function() {
                var fileName = $(this).attr('fileName');
                $(this).prev().remove();
                $(this).next().remove();
                $(this).remove();
                curFiles = curFiles.filter(function(file) {
                    return file.name != fileName;
                });
                fileNames = fileNames.filter(function(name) {
                    return name != fileName;
                });
                if ($('#files').children().length == 0) {
                    $("#files").css('margin-top', '0');
                    if ($('#imgs').children().length == 0) {
                        $('#upload').css('margin-top', '0');
                    }
                }
            });

            $("input[name=files]").val("");

        },
        publish : function() {
            var title = $('input[name=title]').val();
            var abstr = $('input[name=abstr]').val();
            var target = $('input[name=target]:checked').val();
            var status = true;
            // var content = $('#content').val();
            var content = window.editor.html();

            var abstr = $('input[name=abstr]').val();

            if (title.length == 0 || abstr.length == 0 || !target || !status || content.length == 0) {
                window.wxc.xcConfirm("请补充完整信息！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if (title.length > titleMax || abstr.length > abstrMax
                    || parseInt($('.count').text()) > contentMax) {
                window.wxc.xcConfirm("标题或内容字数超限！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if (curFiles.length < fileNumMin || curFiles.length > 10) {
                window.wxc.xcConfirm("图片必须上传！",
                        window.wxc.xcConfirm.typeEnum.info);
            }
            // else if ((target.eq(0).val() == 1 || target.eq(1).val() == 1) &&
            // $('#files').children().length != 0) {
            // window.wxc.xcConfirm("APP只支持图片发布！",
            // window.wxc.xcConfirm.typeEnum.info);
            // }
            else {
                // var targetStr = '';
                // for (var i = 0; i < target.length; i++) {
                // targetStr += target.eq(i).val();
                // if (i != target.length - 1) {
                // targetStr += ',';
                // }
                // }
                var formData = new FormData();
                formData.append('title', title);
                formData.append('target', target);
                formData.append('content', content);
                formData.append('abstr', abstr);

                $.each(curFiles, function(index, file, array) {
                    formData.append('files[]', file);
                });

                $(".mask").show();
                $.ajax({
                            url : '/api/news/upload',
                            type : 'post',
                            data : formData,
                            processData : false,
                            contentType : false,
                            success : function(data) {
                                $(".mask").hide();
                                if (data.code == 200) {
                                    window.wxc.xcConfirm(
                                                    "发布成功！",
                                                    window.wxc.xcConfirm.typeEnum.success,
                                                    {
                                                        onOk : function(v) {
                                                            window.location.href = "/backend/news/list";
                                                        }
                                                    });
                                } else {
                                    window.wxc.xcConfirm(
                                                    "发布失败！",
                                                    window.wxc.xcConfirm.typeEnum.error);
                                }
                            },
                            error : function(err) {
                                $(".mask").hide();
                                window.wxc.xcConfirm("发布失败！",
                                        window.wxc.xcConfirm.typeEnum.error);
                            }
                        });

            }
        }
    };

    function event_bind() {
        $("input[name=title]").keyup(events.count_char);
        $("input[name=abstr]").keyup(events.count_char);
        $("#content").keyup(events.count_textarea);
        $("#upload").click(events.display_file_upload);
        $("input[name=files]").change(events.preview_file);
        $("#publish").click(events.publish);
    }

    function init() {
        event_bind();
    }

    init();
});