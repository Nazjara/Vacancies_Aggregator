package com.nazjara.aggregator.web;

import com.nazjara.aggregator.model.*;
import com.nazjara.aggregator.service.HtmlService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AggregatorServlet extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        String vacancy = request.getParameter("vacancy");
        String city = request.getParameter("city");
        Provider workProvider = new Provider(new WorkUaStrategy());
        Provider rabotaProvider = new Provider(new RabotaStrategy());
        Provider hhProvider = new Provider(new HHStrategy());
        Provider rabotaPlusProvider = new Provider(new RabotaPlusStrategy());
        //Provider provider5 = new Provider(new MoikrugStrategy());
        Provider[]providers = new Provider[4];
        providers[0]= workProvider;
        providers[1]= rabotaProvider;
        providers[2]= hhProvider;
        providers[3]= rabotaPlusProvider;
        HtmlService service = new HtmlService(providers);
        Controller controller = new Controller(service);
        request.setAttribute("workVacancyList",controller.onCityAndVacancySelectWorkProvider(city,vacancy));
        request.setAttribute("rabotaVacancyList",controller.onCityAndVacancySelectRabotaProvider(city,vacancy));
        request.setAttribute("hhVacancyList",controller.onCityAndVacancySelectHHProvider(city,vacancy));
        request.setAttribute("rabotaPlusVacancyList",controller.onCityAndVacancySelectRabotaPlusProvider(city,vacancy));
        request.getRequestDispatcher("/vacancies.jsp").forward(request, response);
    }

}
