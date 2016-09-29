package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=%s+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "http://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";

    @Override
    public List<Vacancy> getVacancies(String city, String vacancy)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int i = 0;
        boolean color = true;
        try
        {
            while(true)
            {
                Document doc = getDocument(vacancy,city,i++);
                Elements e = doc.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy");
                if (e.size()!=0)
                {
                    for (Element element : e)
                    {
                        Vacancy vacancy_1 = new Vacancy();
                        vacancy_1.setSalary(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-compensation").text());
                        vacancy_1.setSiteName(doc.title());
                        vacancy_1.setCompanyName(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-employer").text());
                        vacancy_1.setTitle(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title").text());
                        vacancy_1.setUrl(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title").attr("href"));
                        vacancy_1.setCity(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-address").text());
                        vacancy_1.setColor(color);
                        vacancies.add(vacancy_1);
                        color = !color;
                    }
                }
                else
                    break;
            }
        }
        catch (IOException e)
        {
            //doNothing
        }
        return vacancies;
    }

    private Document getDocument(String vacancy, String city, int page) throws IOException
    {
        String s = String.format(URL_FORMAT, vacancy,city,page);
        return Jsoup.connect(s).userAgent(userAgent).referrer(referrer).get();
    }
}
