package com.nazjara.aggregator.web;

import com.nazjara.aggregator.service.HtmlService;
import com.nazjara.aggregator.vo.Vacancy;

import java.util.List;

class Controller
{
    private HtmlService service;

    Controller(HtmlService service)
    {
        if(service==null)
            throw new IllegalArgumentException();
        this.service = service;
    }

    List<Vacancy> onCityAndVacancySelectHHProvider(String city, String vacancy)
    {
        return service.selectCityAndVacancyHHProvider(city,vacancy);
    }

    List<Vacancy> onCityAndVacancySelectRabotaPlusProvider(String city, String vacancy)
    {
        return service.selectCityAndVacancyRabotaPlusProvider(city,vacancy);
    }

    List<Vacancy> onCityAndVacancySelectWorkProvider(String city, String vacancy)
    {
        return service.selectCityAndVacancyWorkProvider(city,vacancy);
    }

    List<Vacancy> onCityAndVacancySelectRabotaProvider(String city, String vacancy) {
        return service.selectCityAndVacancyRabotaProvider(city,vacancy);
    }
}
