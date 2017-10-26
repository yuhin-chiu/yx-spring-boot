/**
 * Created by yuxuanjiao on 2017/08/05.
 */
$(function() {
    var events = {
        inner_page_jump : function() {
            var cur = $(this);
            if (cur.hasClass("sliders")) {
                location.href = "/backend/others/sliders";
            } else if (cur.hasClass("help")) {
                location.href = "/backend/others/help";
            } else if (cur.hasClass("list")) {
                location.href = "/backend/others/list";
            } else if (cur.hasClass("homepage")) {
                location.href = "/backend/others/homepage";
            } else if (cur.hasClass("article")) {
                location.href = "/backend/others/article";
            }
        },
        select_menu : function() {
            var menu = $("#body-content").attr("menu");
            $(".tag-list ." + menu).addClass("selected")
        }
    };

    function event_bind() {
        $(".tag-list a span").click(events.inner_page_jump);
    }

    function init() {
        events.select_menu();
        event_bind();
    }

    init();
});