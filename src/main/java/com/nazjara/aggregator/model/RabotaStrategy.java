package com.nazjara.aggregator.model;

import com.nazjara.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RabotaStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://rabota.ua/jobsearch/vacancy_list?regionId=%d&keyWords=%s&pg=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "http://rabota.ua/jobsearch/vacancy_list?regionId=2&keyWords=Java%20developer";
    private static final String PATH_TO_PROPERTIES = "/cities.properties";
    private static final String nullChecking = "0";


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
                if(nullChecking.equals(doc.getElementsByClass("rua-p-c-black").first().text()))
                {
                    break;
                }
                Elements e = doc.getElementsByClass("v");
                if (e.size()!=0)
                {
                    for (Element element : e)
                    {
                        Vacancy vacancy_1 = new Vacancy();
                        vacancy_1.setSalary(element.getElementsByTag("b").text());
                        vacancy_1.setSiteName(doc.title());
                        vacancy_1.setCompanyName(element.getElementsByClass("rua-p-c-default").text());
                        vacancy_1.setTitle(element.getElementsByClass("t").text());
                        vacancy_1.setUrl("https://rabota.ua" + element.getElementsByClass("t").attr("href"));
                        vacancy_1.setCity(element.getElementsByClass("s").text().split("•")[1]);
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
        InputStream fileInputStream = getClass().getResourceAsStream(PATH_TO_PROPERTIES);
        Properties prop = new Properties();
        prop.load(fileInputStream);
        String templateCity = city.toLowerCase().replace(" ","_").replace("-","_");
        String cityIndex = prop.getProperty(templateCity,"0");
        String s = String.format(URL_FORMAT, Integer.valueOf(cityIndex),URLEncoder.encode(vacancy,"UTF-8"),page);
        return Jsoup.connect(s).userAgent(userAgent).referrer(referrer).get();
    }
}
