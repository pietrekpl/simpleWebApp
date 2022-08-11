### Practical task

Application to manage employees.

Application made with Postgres Database, Java with SpringBoot framework

In order to use app properly use RestClient for example Postman with following :

List of all employees : GET
http://localhost:8080/simplewebapp/employees
 It returns list of all employees

Add New Employee : POST
http://localhost:8080/simplewebapp/employees
Example in posted body :
{
"departmentId": 3,
"firstName": "Joe",
"jobTitle": "Developer",
"lastName": "Doe"
}
employeeId will be incremented automatically

Getting single employee : GET
http://localhost:8080/simplewebapp/employees/X
where X stand for employee ID


Updating data of employee : PUT
http://localhost:8080/simplewebapp/employees
In body you may change some data of existing employee :
{
"employeeId": 2,
"departmentId": 3,
"firstName": "Joe",
"jobTitle": "QA",
"lastName": "Doe"
}
In case employee is not present, a new one will be created

Deleting an employee  : 
Method : DELETE
http://localhost:8080/simplewebapp/employees/X
where x stand for employee ID

Filter Employees by firstName, lastName or both :
http://localhost:8080/simplewebapp/employees/?firstName=X
where firstName or lastName is queried param and X stand for searched value
In case when firstName or lastName is not found the list of employees will be returned

        
  

