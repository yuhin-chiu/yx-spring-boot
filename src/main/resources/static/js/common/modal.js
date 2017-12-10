/**
 * Created by yuxuanjiao on 2017/10/24.
 */
$(function () {
    var baseUri = "demo";
    var $this = {};
    var imgTypes = [ 'jpg', 'png', 'jpeg' ];
    
    var baseModal = function(url, table) {
        $this = $(this);
        baseUri = url;
        
        $("#modal-timeRange").daterangepicker({}, function(start, end, label) {
            table.query();
        });

        $("#modal-queryBtn").click(table.query);
        
        init();
    }
    
    var events = {
        edit: function (id) {
            events.clearField();
            $('#modal-id').val(id);
            $.get("/api/" + baseUri + "/getById", {id : id}, function(data) {
                events.setCondition(data);
            });
        },
        save: function () {
            if (!check()) {
                return;
            }

            var formData = events.getCondition();
            $.ajax({
                url : '/api/' + baseUri + '/insertOrUpdate',
                type : 'post',
                data : formData,
                processData : false,
                contentType : false,
                success : function(data) {
                    if (data.code == 200) {
                        $("#autotable").baseTable.query();
                        $('.modal').modal('hide');
                        window.wxc.xcConfirm("操作成功！", window.wxc.xcConfirm.typeEnum.success);
                    } else {
                        if(data.msg==undefined){
                            window.wxc.xcConfirm("错误！", window.wxc.xcConfirm.typeEnum.error);
                        }else{
                            window.wxc.xcConfirm(data.msg, window.wxc.xcConfirm.typeEnum.error);
                        }
                    }
                },
                error : function(err) {
                    $(".mask").hide();
                    window.wxc.xcConfirm("发布失败！",
                            window.wxc.xcConfirm.typeEnum.error);
                }
            });
        },
        clearField: function () {
            var val = $('#modal-author').val();
            $('.modal input[type="text"]').val('');
            $('.modal input[type="file"]').val('');
            $('#modal-author').val(val);
            $('.modal textarea').val('');
            if(window.editor) {
                window.editor.html('');
            }
        },
        getCondition: function () {
            var formData = new FormData();
            formData.append('id', $('#modal-id').val());
            formData.append('author', $('#modal-author').val());

            var childs = $('#modal-table').children().children().children("td").children();
            for(var i = 0; i < childs.length; i++) {
                var item = $(childs[i]);
                if(item.attr('type') == 'text' || item.attr('type') == 'textarea' || item.attr('type') == 'select' || item.attr('type') == 'hidden' ) {
                    formData.append(item.attr('key'), item.val());
                } else if(item.attr('type') == 'editor') {
                    formData.append(item.attr('key'), window.editor.html());
                } else if(item.attr('type') == 'file' || item.attr('type') == 'image') {
                    console.log(item.attr('key'));
                    formData.append(item.attr('key'), item[0].files[0]);
                } else if(item.is('div')) {
                    var child = item.children();
                    if(child.attr('type') == 'text' || child.attr('type') == 'textarea' || child.attr('type') == 'select' || child.attr('type') == 'hidden' ) {
                        formData.append(child.attr('key'), child.val());
                    } else if(child.attr('type') == 'file' || child.attr('type') == 'image') {
                        console.log(child.attr('key'));
                        formData.append(child.attr('key'), child[0].files[0]);
                    }
                } else {
                    console.log(item.attr('type'));
                }
            }
            return formData;
        },
        setCondition: function (data) {
            var childs = $('#modal-table').children().children().children("td").children();
            for(var i = 0; i < childs.length; i++) {
                var item = $(childs[i]);
                if(item.is('div')) {
                    var child = item.children();
                    if(child.attr('type') == 'text' || child.attr('type') == 'textarea' || child.attr('type') == 'hidden' ) {
                        child.val(data[child.attr('key')]);
                    }
                } else if(item.attr('type') == 'text' || item.attr('type') == 'textarea' || item.attr('type') == 'hidden') {
                    item.val(data[item.attr('key')]);
                } else if(item.attr('type') == 'editor') {
                    window.editor.html(data[item.attr('key')]);
                }
            }
            
            $('.modal').modal('show');
        }
    };

    function event_bind() {

        $("#modal-addBtn").click(events.clearField);

        $("#modal-saveBtn").click(events.save);
        edit = events.edit;

        $(".modal-select").chosen({
            disable_search: true
        });
        $("#modal-table .chosen-container").width('50%');

    }

    function check() {
        var childs = $('#modal-table').children().children().children("td").children();
        for(var i = 0; i < childs.length; i++) {
            var item = $(childs[i]);
            if(item.attr('type') == 'text' || item.attr('type') == 'textarea' || item.attr('type') == 'editor') {
                var content;
                if(item.attr('type') == 'editor') {
                    content = window.editor.html();
                } else {
                    content = item.val();
                }
                var minLength = item.attr('minLength');
                var maxLength = item.attr('maxLength'); 
                var title = item.attr('title');
                var count = content.length;
                if(count < minLength) {
                    alert(title + '不能为空！');
                    return false;
                } else if(count > maxLength) {
                    alert(title + '不能超过' + maxLength + '字,当前字数：' + count);
                    return false;
                }
            } else if(item.attr('type') == 'file' && !$('#modal-id').val()) {
                if(item[0].files.length == 0) {
                    alert('图片必须上传！');
                    return false;
                } else {
                    var name = item[0].files[0].name;
                    var infos = name.split('.');
                    var type = infos[infos.length - 1];
                    if (imgTypes.indexOf(type) == -1) { // 当前只支持图片上传
                        window.wxc.xcConfirm("只支持图片上传！（jpg、jpeg、png）",
                                window.wxc.xcConfirm.typeEnum.info);
                        return false;
                    }
                }
            }
        }

        var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;

        return true;
    }
    function init() {
        $.ajaxSetup({
            async : false
        });
        event_bind();
    }
    
    $.fn.baseModal = baseModal;
});