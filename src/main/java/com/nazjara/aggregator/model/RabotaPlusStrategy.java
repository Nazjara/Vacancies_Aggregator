package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RabotaPlusStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://rabotaplus.ua/vacancy/search/%s/city/%s/page-%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "http://rabotaplus.ua/vacancy/search/java/city/kiev";

    @Override
    public List<Vacancy> getVacancies(String city, String vacancy)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int i = 1;
        boolean color = true;
        try
        {
            while(true)
            {
                Document doc = getDocument(city,vacancy,i++);
                Elements e = doc.getElementsByClass("js-item_list");
                if (e.size()!=0)
                {
                    for (Element element : e)
                    {
                        Vacancy vacancy_1 = new Vacancy();
                        vacancy_1.setSalary(element.getElementsByClass("b-vacancy__top__pay").text());
                        vacancy_1.setSiteName(doc.title());
                        vacancy_1.setCompanyName(element.getElementsByClass("b-vacancy__tech").select("a").text());
                        vacancy_1.setTitle(element.getElementsByClass("b-vacancy__top__title").text().replaceAll("\\(.*\\)",""));
                        vacancy_1.setUrl(element.getElementsByClass("b-vacancy__top__title").attr("href"));
                        vacancy_1.setCity(element.getElementsByClass("b-vacancy__tech__item-city").text().replaceAll("ещё \\d ",""));
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
            e.printStackTrace();
        }
        return vacancies;
    }

    private Document getDocument(String city, String vacancy, int page) throws IOException
    {
        String s = String.format(URL_FORMAT, URLEncoder.encode(vacancy,"UTF-8"),URLEncoder.encode(city,"UTF-8"),page);
        return Jsoup.connect(s).userAgent(userAgent).referrer(referrer).get();
    }
}
