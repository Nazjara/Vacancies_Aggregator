package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;
import java.util.List;

public class Provider
{
    private Strategy strategy;

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public List<Vacancy> getVacancies(String city, String vacancy)
    {
        return strategy.getVacancies(city,vacancy);
    }
}
