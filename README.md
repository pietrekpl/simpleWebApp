### Practical task

Application to manage employees.

Application made with Postgres Database, Java with SpringBoot framework

In order to use app properly use RestClient for example Postman with following :

List of all employees : GET
http://localhost:8080/simplewebapp/employees
 It Return List of all employees

Add New Employee : POST
http://localhost:8080/simplewebapp/employees
Example in posted body : 
{
    "employee_id" : 3,
    "first_name" : "Joe",
    "last_name" : "Doe",
    "deparment_id" : 1,
    "job_title" : "QA"
}

Getting single employee : GET
http://localhost:8080/simplewebapp/employees/X
where X stand for employee ID
When ID is not availaible, it returns 404 Not Found response.

Updating data of employee : PUT
http://localhost:8080/simplewebapp/employees/X
where X stand for employee ID
In body you may change some data :
{
    "employee_id" : 3,
    "first_name" : "Joe",
    "last_name" : "Doe",
    "deparment_id" : 1,
    "job_title" : "QA"
}

Deleting an employee  : 
Method : DELETE
http://localhost:8080/simplewebapp/employees/X
where x stand for employee ID

Filter Employees by firstName :
http://localhost:8080/simplewebapp/employees/firstName?firstName=X
where X stand for Employee firstName. Only few letter of firstName is required to get desired firstName.

Filter Employees by lastName :
http://localhost:8080/simplewebapp/employees/lastName?lastName=X
where X stand for Employee lastName. Only few letter of lastName is required to get desired lastName.
        
        
  

