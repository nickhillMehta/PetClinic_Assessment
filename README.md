# PetClininc_Assessment : Some Tips for executing this project
This is sample Testng project in order to run automated tests on Petclinic application.

In order to run this project there are few pre requirements which are as follows:
1)Build and Host the Petclinic application on your local system by following the instructions found at :
https://github.com/spring-projects/spring-petclinic

2)Make sure application is running at : http://localhost:8080/

3)Download this project on your local environment via Github.
git clone git clone https://github.com/nickhillMehta/PetClinic_Assessment.git

4)This testcases are working against in-memory database (HSQLDB) which gets populated at startup with data when you build the petclnic  application.So Make sure that when you build the petclinic application, you see there is relevant data in the application by navigating across the application.
----------------------------------------------------------------------------------------------------------------------------------------------
Running the project:
** Execute TestNG.xml from the IDE like IntelliJ and you should see 7 main Testcases.but as they have different data provider instances, so in all 21 test cases should be executed.
When tests are completed,appropriate PDF reports will be generated in teh project folder.Also more detailed report can be found in Reports folder of the project where it is installed on your local machine.You can locate it in  {Projectfolder}/Reports/emailable-report.html

Note:: Make sure before you run the tests , enable default Reporters and in Listeners set Customised_Report as listeners.
Also set the output directory as : {Projectfolder}\Reports
{Projectfolder}:This is project folder where you have cloned the github project
----------------------------------------------------------------------------------------------------------------------------------------------
** Tests are built in such a way that they will execute any number of times meaning test data is handled in such a way.

**For documentation on POMs and contents please refer Assessment_documentation.doc

