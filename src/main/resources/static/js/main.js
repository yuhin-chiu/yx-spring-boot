/**
 * Created by shulinli on 2017/4/17.
 */
$(function () {
    var events = {
        init_page: function () {
            var type = $("body").attr("page");
            $(".header-content .header-main ." + type).addClass("selected");
        },
        jump: function () {
            if ($(this).hasClass("selected")) {
                return;
            }
            var type = $(this).text();
            if (type == "展会资讯") {
                location.href = "/backend/news";
            } else if (type == "同期活动") {
                location.href = "/backend/activities";
            } else if (type == "展商推荐") {
                location.href = "/backend/companies";
            } else if (type == "合作媒体") {
                location.href = "/backend/medias";
            } else if (type == "历届回顾") {
                location.href = "/backend/histories";
            } else if (type == "申请管理") {
                location.href = "/backend/applies";
            } else if (type == "下载管理") {
                location.href = "/backend/downloads";
            } else if(type == "其他"){
                return;
                location.href = "/backend/other";
            }
            
        }
    };

    function bind_event() {
        $(".header-content .header-main div span").click(events.jump);
    }

    function init() {
        events.init_page();
        bind_event();
    }

    init();
});