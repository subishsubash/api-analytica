<h1>API Analytic</h1>
<h2>What it does</h2>

<p>API Analytica is an intelligent system that enables the bank to monitor their incoming API traffic by providing the graphical representation and the suitable recommendations, In order to improve the performance of API response time. It allow the bank to view requested API’s in various timelines and its average response time and make the decisions accordingly. By making use of the system, bank can identify the most transactions happened and do the relevant data analytics.</p>

----
<h2>Functional Flow</h2>

<p>1. Analytica will capture and store all the required request details like API name, Requested timestamp and Response time.</p>
<p>2. Analytica provides the captured API details in various timelines and that helps to view the graphical representation of transaction details on daily, weekly, monthly and yearly basis. Whenever transactions happens, Analytica will analyze the data and provides the recommendations to the System Administrators and the stakeholders.</p>

----
<h2>API Exposed from API Analtica</h2>

| Method | Endpoint | Description |  
|:-----------|:-----------|:-----------|  
| POST | /analytica-1.0/api/v1.0/entry | API will be called from a target war, to let system about the incoming request |  
| GET | /analytica-1.0/api/v1.0/graphdata/{timeline} | Fetch the API details for given timeline.<br>Eg. 1m, 2019, 5m |
| GET | /analytica-1.0/v0.1/api/graphdatahourly?from=yyyy-mm-dd&to=yyyy-mm-dd | Fetch the API hits hourly details for given timeline |  
| GET | /analytica-1.0/v0.1/api/graphdatadays/{timeline} | Fetch the API hits daily details for given timeline|

----
<h2>Languages & Frameworks</h2> 

1. Backend
- Java
- Spring Boot
- MySql
2. FrontEnd
- Javascrpits
- HighCharts

----
<h2>Deployment</h2>

1. Install MySql server and let the server run in port 3306, ensure the username & password as root.
2. Execute the [SQL](https://github.com/subishsubash/api-analytica/blob/main/SQL) to create database and tables.
3. Build the maven project 
````
mvn clean install
````
4. Deploy the war in tomcat server 
````
http://localhost:8080/analytica-1.0/
````
5. Import [Postman collection](https://github.com/subishsubash/api-analytica/blob/main/API-Analytica.postman_collection.json)



