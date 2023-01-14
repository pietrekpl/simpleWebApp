package com.mastery.java.task.controller;


import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/employees")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "employees", description = "Add / Get / Update / Delete employee")
@CrossOrigin(origins = "http://localhost:4200/", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})
public class EmployeeController {

    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Returns all employees or filter employees by firstName/lastName",
            description = "When no `parameter` is applied list of all employees is being returned," +
                    " if no `firstName`/`lastName` is found empty list of employees is being returned",
            responses = {@ApiResponse(description = "Success response", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(hidden = true)))},
            parameters = {@Parameter(name = "firstName", description = "Employee first name (Optional)"),
                    @Parameter(name = "lastName", description = "Employee last name (Optional)")}
    )
    public List<Employee> getFilteredEmployeesOrAllEmployees(@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
                                                             @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName) {
        log.info("Method getFilteredEmployeesOrAllEmployees()");
        return employeeService.filterEmployeesByFirstNameOrLastName(firstName, lastName);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Get Employee by ID",
            description = "Gets single employee by ID",
            responses = {@ApiResponse(description = "Success response", responseCode = "200", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(description = "Employee with requested ID not found", responseCode = "404", content = @Content(examples = {@ExampleObject("""
                                            {
                                "status": "NOT_FOUND",
                                "timestamp": "01/08/2022 09:00:00",
                                "message": "Employee with ID 444 not found"
                            }
                                          """)})),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(hidden = true)))})
    public Employee getEmployee(@Parameter(name = "id", description = "Employee ID", required = true, example = "1") @PathVariable("id") Long id) {
        log.info("Method getEmployee() takes id = {}", id);
        return employeeService.getEmployeeById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Add new employee", description = "Add new Employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The request body is expected to provide the new employee." +
                    " The `employeeId` field is omitted, because of automatic assignment. `dateOfBirth` is in format: dd.MM.yyyy separated by `.` ,where dd stands for day, mm for month and yyyy for year." +
                    " Be aware only employees having 18+ years will be successfully added. `gender` field can hold only 2 values : `male` or`female` ", content = @Content(
                    examples = @ExampleObject(description = "Provides an example `employee` request body to save:",
                            value = """
                                       {
                                                     "firstName": "Joe",
                                                     "lastName": "Doe",
                                                     "departmentId": 1,
                                                     "jobTitle": "Singer",
                                                     "dateOfBirth": "01.01.2000",
                                                     "gender": "male"
                                                 }
                                    """
                    )
            )), responses = {@ApiResponse(description = "Successfully added", responseCode = "201"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(examples = {@ExampleObject("""
                    {
                      "status": "BAD_REQUEST",
                      "timestamp": "23/08/2022 01:02:21",
                      "message": "[dateOfBirth : Only employees with age 18 and above are legible to store]"
                    }
                                  """)})),
            @ApiResponse(description = "Internal Server Error", responseCode = "500")})
    public void addEmployee(@Valid @RequestBody() Employee employee) {
        log.info("Method addEmployee() takes employee = {}", employee);
        employeeService.save(employee);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Update employee ",
            description = "Update employee by providing existing `employeeID` and then update in request body",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The request body is expected to update employee." +
                    " The `employeeId` field is omitted, it is already requested in path. " +
                    "`dateOfBirth` is in format: dd.MM.yyyy where dd stands for day, mm for month and yyyy for year separated by `.`" +
                    " Please be aware update won't be effective, if age of employee is less than 18. `gender` field can hold only 2 values : `male` or`female`",
                    content = @Content(
                            examples = @ExampleObject(description = "Provides an example `employee` request body to update:",
                                    value = """
                                              {
                                                     "firstName": "Joe",
                                                     "lastName": "Doe",
                                                     "departmentId": 1,
                                                     "jobTitle": "Singer",
                                                     "dateOfBirth": "01.01.2000",
                                                     "gender": "male"
                                                 }
                                            """
                            )
                    )), responses = {@ApiResponse(description = "Successfully updated", responseCode = "200"),
            @ApiResponse(description = "Employee with requested ID not found", responseCode = "404", content = @Content(examples = {@ExampleObject("""
                                    {
                        "status": "NOT_FOUND",
                        "timestamp": "01/08/2022 09:00:00",
                        "message": "Employee with ID 444 not found"
                    }
                                  """)})),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(examples = {@ExampleObject("""
                    {
                      "status": "BAD_REQUEST",
                      "timestamp": "23/08/2022 01:02:21",
                      "message": "[dateOfBirth : Only employees with age 18 and above are legible to store]"
                    }
                                  """)})),
            @ApiResponse(description = "Internal Server Error", responseCode = "500")})
    public void updateEmployee(@Valid @RequestBody Employee employee,
                               @Parameter(name = "id", description = "Employee ID", required = true, example = "1") @PathVariable("id") Long id) {
        log.info("Method updateEmployee() takes id = {}", id);
        employeeService.updateEmployee(employee, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee by provided ID",
            description = "Delete single employee by provided ID",
            responses = {@ApiResponse(description = "Successfully deleted", responseCode = "204"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")})
    public void deleteEmployee(@Parameter(name = "id", description = "Employee ID", required = true, example = "1") @PathVariable("id") Long id) {
        log.info("Method deleteEmployee() takes id = {}", id);
        employeeService.deleteEmployeeById(id);
    }
}




