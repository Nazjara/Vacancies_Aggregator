<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Vacancies</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<table>
    <div class="site_name"><a href="http:\\www.work.ua">work.ua</a></div>
    <tr>
        <th class="main">Title</th>
        <th class="main">City</th>
        <th class="main">Company Name</th>
        <th class="main">Salary</th>
    </tr>
    <c:if test="${empty workVacancyList}">
        <h4 style="text-align: center">Unfortunately no vacancies were found. Please try another set of parameters</h4>
    </c:if>
    <c:forEach items="${workVacancyList}" var="vacancy">
        <jsp:useBean id="vacancy" scope="page" type="com.nazjara.aggregator.vo.Vacancy"/>
        <tr class="${vacancy.color ? 'color' : ''}">
            <td class="title"><a href="${vacancy.url}">${vacancy.title}</a></td>
            <td class="city">${vacancy.city}</td>
            <td class="companyName">${vacancy.companyName}</td>
            <td class="salary">${vacancy.salary}</td>
        </tr>
    </c:forEach>
</table>
<table>
    <div class="site_name"><a href="http:\\www.rabota.ua">rabota.ua</a></div>
    <tr>
        <th class="main">Title</th>
        <th class="main">City</th>
        <th class="main">Company Name</th>
        <th class="main">Salary</th>
    </tr>
    <c:if test="${empty rabotaVacancyList}">
        <h4 style="text-align: center">Unfortunately no vacancies were found. Please try another set of parameters</h4>
    </c:if>
    <c:forEach items="${rabotaVacancyList}" var="vacancy1">
        <jsp:useBean id="vacancy1" scope="page" type="com.nazjara.aggregator.vo.Vacancy"/>
        <tr class="${vacancy1.color ? 'color' : ''}">
            <td class="title"><a href="${vacancy1.url}">${vacancy1.title}</a></td>
            <td class="city">${vacancy1.city}</td>
            <td class="companyName">${vacancy1.companyName}</td>
            <td class="salary">${vacancy1.salary}</td>
        </tr>
    </c:forEach>
</table>
<table>
    <div class="site_name"><a href="http:\\www.hh.ua">hh.ua</a></div>
    <tr>
        <th class="main">Title</th>
        <th class="main">City</th>
        <th class="main">Company Name</th>
        <th class="main">Salary</th>
    </tr>
    <c:if test="${empty hhVacancyList}">
        <h4 style="text-align: center">Unfortunately no vacancies were found. Please try another set of parameters</h4>
    </c:if>
    <c:forEach items="${hhVacancyList}" var="vacancy2">
        <jsp:useBean id="vacancy2" scope="page" type="com.nazjara.aggregator.vo.Vacancy"/>
                <tr class="${vacancy2.color ? 'color' : ''}">
                    <td class="title"><a href="${vacancy2.url}">${vacancy2.title}</a></td>
                    <td class="city">${vacancy2.city}</td>
                    <td class="companyName">${vacancy2.companyName}</td>
                    <td class="salary">${vacancy2.salary}</td>
                </tr>
    </c:forEach>
</table>
<table>
    <div class="site_name"><a href="http:\\www.rabotaplus.ua">rabotaplus.ua</a></div>
    <tr>
        <th class="main">Title</th>
        <th class="main">City</th>
        <th class="main">Company Name</th>
        <th class="main">Salary</th>
    </tr>
    <c:if test="${empty rabotaPlusVacancyList}">
        <h4 style="text-align: center">Unfortunately no vacancies were found. Please try another set of parameters</h4>
    </c:if>
    <c:forEach items="${rabotaPlusVacancyList}" var="vacancy3">
        <jsp:useBean id="vacancy3" scope="page" type="com.nazjara.aggregator.vo.Vacancy"/>
        <tr class="${vacancy3.color ? 'color' : ''}">
            <td class="title"><a href="${vacancy3.url}">${vacancy3.title}</a></td>
            <td class="city">${vacancy3.city}</td>
            <td class="companyName">${vacancy3.companyName}</td>
            <td class="salary">${vacancy3.salary}</td>
        </tr>
    </c:forEach>
</table>
<button id="button" class="btn-block" onclick="window.history.back()">Back to main page</button>
</body>
</html>
