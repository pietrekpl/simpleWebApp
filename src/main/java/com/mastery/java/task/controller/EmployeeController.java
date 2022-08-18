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

import java.util.List;


@RestController
@RequestMapping("/employees")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "employees", description = "Add / Get / Update / Delete employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Returns all employees or filter employees based on firstName or lastName param",
            description = "When no `parameter` is applied or no `firstName`/`lastName` is found, list of all employees is being returned",
            responses = {@ApiResponse(description = "Success response", responseCode = "200",
                    content = @Content(examples = @ExampleObject(summary = "Example of output:", value = """
                            [
                              {
                                  "employeeId": 1,
                                   "firstName": "Joe",
                                   "lastName": "Doe",
                                   "departmentId": 7,
                                    "jobTitle": "Teacher"
                                 },
                              {
                                  "employeeId": 2,
                                  "firstName": "Michael",
                                  "lastName": "Dowson",
                                  "departmentId": 5,
                                  "jobTitle": "Engineer"
                              }
                            ]
                            """))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(hidden = true)))},
            parameters = {@Parameter(name = "firstName", description = "Employee first name (Optional)"),
                    @Parameter(name = "lastName", description = "Employee last name (Optional)")}
    )
    public List<Employee> getFilteredEmployeesOrAllEmployees(@RequestParam(value = "firstName", required = false) String firstName,
                                                             @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.filterEmployeesByFirstNameOrLastName(firstName, lastName);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Get Employee by ID",
            description = "Gets single employee by ID",
            responses = {@ApiResponse(description = "Success response", responseCode = "200", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(description = "Employee with requested ID not found", responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(hidden = true)))})
    public Employee getEmployee(@Parameter(name = "id", description = "Employee ID", required = true, example = "1" ) @PathVariable("id") Long id) {
        log.info("Method getEmployee() takes id = {}", id);
        return employeeService.getEmployeeById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Add new employee", description = "Add new Employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The request body is expected to provide the new employee." +
                    " The `employeeId` field is omitted, because of automatic assignment.", content = @Content(
                    examples = @ExampleObject(description = "Provides an example `employee` request body to save:",
                            value = """
                                      {
                                           "firstName": "Joe",
                                           "lastName": "Doe",
                                           "departmentId": 7,
                                            "jobTitle": "Teacher"
                                         }
                                    """
                    )
            )), responses = {@ApiResponse(description = "Successfully added", responseCode = "201"),
            @ApiResponse(description = "Internal Server Error", responseCode = "500")})
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.save(employee);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Update employee ",
            description = "Update employee by providing existing `employeeID` and then update in request body",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The request body is expected to update employee." +
                    " The `employeeId` field is omitted, it is already requested in path",
                    content = @Content(
                            examples = @ExampleObject(description = "Provides an example `employee` request body to update:",
                                    value = """
                                              {
                                                   "firstName": "James",
                                                   "lastName": "Bond",
                                                   "departmentId": 7,
                                                    "jobTitle": "Agent"
                                                 }
                                            """
                            )
                    )), responses = {@ApiResponse(description = "Successfully updated", responseCode = "200"),
            @ApiResponse(description = "Employee with requested ID not found", responseCode = "404"),
            @ApiResponse(description = "Internal Server Error", responseCode = "500")})
    public void updateEmployee(@RequestBody Employee employee, @Parameter(name = "id", description = "Employee ID", required = true, example = "1") @PathVariable("id") Long id) {
        log.info("Method updateEmployee() takes id = {}", id);
        employeeService.updateEmployee(employee, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee by provided ID",
            description = "Delete single employee by provided ID",
            responses = {@ApiResponse(description = "Successfully deleted", responseCode = "204"),
                    @ApiResponse(description = "Employee with requested ID not found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")})
    public void deleteEmployee(@Parameter(name = "id", description = "Employee ID", required = true, example = "1") @PathVariable("id") Long id) {
        log.info("Method deleteEmployee() takes id = {}", id);
        employeeService.deleteEmployeeById(id);
    }

}




