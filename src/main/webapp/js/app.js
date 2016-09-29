/**
 * Created by Nazar on 30.05.2016.
 */

var main = function() {
    $(".btn").click(function() {
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
        return !!(city !== "" && vacancy !== "");
    });
};

$(document).ready(main);