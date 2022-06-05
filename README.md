### Practical task

Application to manage employees.

Application made with Postgres Database, Java with SpringBoot framework

In order to use app properly use RestClient for example Postman with following :

List of all employees : GET
http://localhost:8080/simplewebapp/employee/all

Add New Employee : POST
http://localhost:8080/simplewebapp/employee/add
Example in posted body : 
{
    "employee_id" : 3,
    "first_name" : "Joe",
    "last_name" : "Doe",
    "deparment_id" : 1,
    "job_title" : "QA"
}

Getting single employee : GET
http://localhost:8080/simplewebapp/employee/X
where X stand for employee ID

Updating data of employee : PUT
http://localhost:8080/simplewebapp/employee/update/X
where X stand for employee ID
In body you may change some data :
{
    "employee_id" : 3,
    "first_name" : "Joe",
    "last_name" : "Doe",
    "deparment_id" : 1,
    "job_title" : "QA"
}

Deleting an employee : 
http://localhost:8080/simplewebapp/employee/delete/X
where x stand for employee ID

        
        
  

