# WorkInProgress
This is sample Testng project in order to run automated tests on Petclinic application.

In order to run this project there are few pre requirements which are as follows:
1)Build and Host the Petclinic application on your local system by following the instructions found at :
https://github.com/spring-projects/spring-petclinic

2)Make sure application is running at : http://localhost:8080/

3)Download this project on your local environment via any IDE for example Eclipse or IntelliJ which has TestNG support.

4) Execute TestNG.xml from the IDE and you should see 7 main Testcases.but as they have different data provider isntances, so in all 21 test cases should be executed.

5) This testcases are working against in-memory database (HSQLDB) which gets populated at startup with data when you build the petclnic  application.

6) Tests are built in such a way that they will execute any number of times.

