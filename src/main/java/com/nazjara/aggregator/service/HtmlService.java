package com.nazjara.aggregator.service;

import com.nazjara.aggregator.model.Provider;
import com.nazjara.aggregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class HtmlService
{
    private Provider[] providers;

    public HtmlService(Provider[] providers)
    {
        if(providers.length==0)
            throw new IllegalArgumentException();
        this.providers = providers;
    }

    public List<Vacancy> selectCityAndVacancyWorkProvider(String city, String vacancy)
    {
        List<Vacancy> list = new ArrayList<>();
        list.addAll(providers[0].getVacancies(city,vacancy));
        return list;
    }

    public List<Vacancy> selectCityAndVacancyRabotaProvider(String city, String vacancy) {
        List<Vacancy> list = new ArrayList<>();
        list.addAll(providers[1].getVacancies(city,vacancy));
        return list;
    }

    public List<Vacancy> selectCityAndVacancyHHProvider(String city, String vacancy)
    {
        List<Vacancy> list = new ArrayList<>();
        list.addAll(providers[2].getVacancies(city,vacancy));
        return list;
    }

    public List<Vacancy> selectCityAndVacancyRabotaPlusProvider(String city, String vacancy)
    {
        List<Vacancy> list = new ArrayList<>();
        list.addAll(providers[3].getVacancies(city,vacancy));
        return list;
    }
}
