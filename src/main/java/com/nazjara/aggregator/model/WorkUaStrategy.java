package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class WorkUaStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://www.work.ua/jobs-%s-%s/?days=125&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "http://www.work.ua/jobs-%D0%BA%D0%B8%D0%B5%D0%B2-java/";

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
                Elements e = doc.getElementsByClass("job-link");
                if (e.size()!=0)
                {
                    for (Element element : e)
                    {
                        Vacancy vacancy_1 = new Vacancy();
                        vacancy_1.setSalary(element.getElementsByTag("h2").first().select("span.nowrap").text());
                        vacancy_1.setSiteName(doc.title());
                        vacancy_1.setCompanyName(element.select("h2").first().nextElementSibling().child(0).text());
                        vacancy_1.setTitle(element.getElementsByTag("h2").select("a").text());
                        vacancy_1.setUrl(element.getElementsByTag("a").attr("abs:href"));
                        vacancy_1.setCity(element.select("h2").first().nextElementSibling().children().select(".text-muted").first().nextElementSibling().text().split("\\u00a0")[0]);
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
        String s = String.format(URL_FORMAT,URLEncoder.encode(city,"UTF-8"),URLEncoder.encode(vacancy,"UTF-8"),page);
        return Jsoup.connect(s).userAgent(userAgent).referrer(referrer).get();
    }
}
