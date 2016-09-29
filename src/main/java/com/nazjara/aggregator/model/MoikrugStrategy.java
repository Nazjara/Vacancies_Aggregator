package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//this strategy doesn't work as anticipated
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=%s+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "https://moikrug.ru/vacancies?q=java+%D0%BA%D0%B8%D0%B5%D0%B2";

    @Override
    public List<Vacancy> getVacancies(String city, String vacancy)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int i = 0;
        try
        {
            while(true)
            {
                Document doc = getDocument(vacancy,city,i++);
                Elements e = doc.getElementsByClass("job");
                if (e.size()!=0)
                {
                    for (Element element : e)
                    {
                        Vacancy vacancy_1 = new Vacancy();
                        vacancy_1.setSalary(element.getElementsByAttributeValue("class","salary").text());
                        vacancy_1.setSiteName(doc.title());
                        vacancy_1.setCompanyName(element.getElementsByAttributeValue("class","company_name").select("a[href]").text());
                        vacancy_1.setTitle(element.getElementsByAttributeValue("class","title").text());
                        vacancy_1.setUrl(element.getElementsByClass("title").first().child(0).attr("abs:href"));
                        vacancy_1.setCity(element.getElementsByAttributeValue("class","location").text());
                        vacancies.add(vacancy_1);
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
