# Vacancies Aggregator

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/baa11c26a1714dd39251e65ff5731980)](https://www.codacy.com/app/nazjara/Vacancies_Aggregator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Nazjara/Vacancies_Aggregator&amp;utm_campaign=Badge_Grade)

Simple java web application, that can be used for aggregation different vacations from the most popular ukrainian websites. All what you need is to input desired city and vacancy, and you will get total list with vacations in convenient format.

Used tools:

Java SE 1.8.0  
Java EE Servlets 3.1.0  
Maven 3.3.9  
Tomcat 9.0.0    
JSoup 1.9.2  
JSTL 1.2  
JQuery 2.2.4  
CSS Bootstrap 3.3.6 

How to install using Maven and Tomcat:

1. Open command line in project directory and enter "mvn package"  
2. Find .war file in project's 'target' directory and move it to Tomcat directory 'webapps'  
3. Run Tomcat, open your browser and enter 'http://localhost:8080/Vacancies%20Aggregator/' 
4. Enjoy!  

P.S. Application was created and tested using browser Google Chrome. Correct working using other browsers is not proved.

P.P.S. Application is using standard sites' searching mechanisms, which are different for each of them. If search results from some site are inappropriate, please try to use another spelling for parameters (application supports both Cyrillic and Latin characters).
