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
        String vacancy = request.getParameter("vacancy");
        String city = request.getParameter("city");
        Provider hhProvider = new Provider(new HHStrategy());
        Provider rabotaPlusProvider = new Provider(new RabotaPlusStrategy());
        Provider workProvider = new Provider(new WorkUaStrategy());
        //Provider provider4 = new Provider(new MoikrugStrategy());
        Provider[]providers = new Provider[3];
        providers[0]= hhProvider;
        providers[1]= rabotaPlusProvider;
        providers[2]= workProvider;
        HtmlService service = new HtmlService(providers);
        Controller controller = new Controller(service);
        request.setAttribute("hhVacancyList",controller.onCityAndVacancySelectHHProvider(city,vacancy));
        request.setAttribute("rabotaPlusVacancyList",controller.onCityAndVacancySelectRabotaPlusProvider(city,vacancy));
        request.setAttribute("workVacancyList",controller.onCityAndVacancySelectWorkProvider(city,vacancy));
        request.getRequestDispatcher("/vacancies.jsp").forward(request, response);
    }

}
