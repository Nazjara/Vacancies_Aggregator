var main = function() {
    $(".btn-default").click(function() {
        var city = $("#city").val();
        var vacancy = $("#vacancy").val();
        if(city === "")
        {
            $("#city_null").text("Please enter the city");
        }
        else
        {
            $("#city_null").text("");
        }
        if(vacancy === "")
        {
            $("#vacancy_null").text("Please enter the vacancy");
        }
        else
        {
            $("#vacancy_null").text("");
        }
        if(city !== "" && vacancy !== "")
        {
            $("body").append("<img id='loadImg' src='images/loader.gif'/>").append("<div id='overlay'></div>");
            var docHeight = $(document).height();
            var imgObj = $("#loadImg");
            var div_overlay = $("#overlay");
            div_overlay.height(docHeight);
            div_overlay.show();
            imgObj.css({"top": (($(window).height() - imgObj.outerHeight()) / 2) + $(window).scrollTop() + "px"});
            imgObj.css({"left": (($(window).width() - imgObj.outerWidth()) / 2) + $(window).scrollLeft() + "px"});
            imgObj.css({"position": "absolute"});
            imgObj.show();
            return true;
        }
        else
            return false;
    });
};

$(document).ready(main);