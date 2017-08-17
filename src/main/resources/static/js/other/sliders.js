/**
 * Created by jiaoyuxuan on 2017/8/8.
 */
$(function() {
    var baseUri = 'sliders'; 
    var curFiles = [], fileNames = [], imgTypes = [ 'jpg', 'png', 'jpeg' ];
    var docTypes = [ 'doc', 'docx', 'xls', 'xlsx', 'xlsm', 'ppt', 'pptx', 'txt' ];
    var titleMax = 20, hasTitle = true;
    var abstrMax = 200, hasAbstr = true;
    var contentMax = 1000, hasContent = false;
    var hasStatus = false, hasTarget = false;
    var fileNumMax = 1, hasFile = true;
    var fileNumMin = 1;
    var onlyImage = true, onlyFile = false;
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
                    window.wxc.xcConfirm("数量不能超过" + fileNumMax + "！",
                            window.wxc.xcConfirm.typeEnum.info);
                    break;
                }
                var infos = name.split('.');
                var type = infos[infos.length - 1];
                var status = $('input[name=status]:checked');
                
                if (onlyImage && imgTypes.indexOf(type) == -1) { // 当前只支持图片上传
                    window.wxc.xcConfirm("只支持图片上传！（jpg、jpeg、png）",
                            window.wxc.xcConfirm.typeEnum.info);
                    continue;
                } else if (onlyFile && docTypes.indexOf(type) == -1) { // 当前只支持图片上传
                    window.wxc.xcConfirm("只支持文件上传！（doc , docx , xls , xlsx , xlsm , ppt , pptx , txt ）",
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
            var title = hasTitle && $('input[name=title]').val();
            var abstr = hasAbstr && $('input[name=abstr]').val();
            var target = hasTarget && $('input[name=target]:checked').val();
            var status = hasStatus && $('input[name=status]').val();
            // var content = $('#content').val();
            var content = hasContent && window.editor.html();

            var abstr = $('input[name=abstr]').val();

            if ((hasTitle && title.length == 0) 
                    || ( hasAbstr && abstr.length == 0) 
                    || ( hasTarget && !target ) 
                    || ( hasStatus && !status )
                    || ( hasContent && content.length == 0)) {
                window.wxc.xcConfirm("请补充完整信息！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if ((hasTitle && title.length > titleMax) || (hasAbstr && abstr.length > abstrMax)
                    || (hasContent && parseInt($('.count').text()) > contentMax)) {
                window.wxc.xcConfirm("标题或内容字数超限！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if (curFiles.length < fileNumMin || curFiles.length > 10) {
                window.wxc.xcConfirm("图片必须上传！",
                        window.wxc.xcConfirm.typeEnum.info);
            } else if ( onlyImage && $('#files').children().length != 0) {
                 window.wxc.xcConfirm("只支持图片发布！",
                 window.wxc.xcConfirm.typeEnum.info);
             }
            else {
                // 多选
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
                formData.append('url', abstr);

                $.each(curFiles, function(index, file, array) {
                    formData.append('files[]', file);
                });

                $(".mask").show();
                $.ajax({
                            url : '/api/' + baseUri + '/upload',
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
                                                            window.location.href = "/backend/others";
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