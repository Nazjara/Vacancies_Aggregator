package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;

import java.util.List;


interface Strategy
{
    List<Vacancy> getVacancies(String city, String vacancy);
}
