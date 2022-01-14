package com.ers.code.controller;

import com.ers.code.exception.EmployeeNotFoundException;
import com.ers.code.model.Employee;
import com.ers.code.service.EmployeeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employeerecordsystem")
@Api(value = "employerecordsystem")
public class EmployeeController
{
  @Autowired
  EmployeeService employeeService;
  private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

  @ApiOperation(value = "View a list of employees", response = Iterable.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  }
  )
  @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity getEmployees() throws EmployeeNotFoundException
  {
    logger.info("## Enter : Retrieve Employee ##");
    List<Employee> employeeList = employeeService.getEmployees();
    logger.info("## Exit : Retrieve Employee ##");
    return (ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION)
            .body(employeeList));
  }

  @ApiOperation(value = "Create new employee")
  @RequestMapping(value = "/createemployee", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity createEmployee(@RequestBody String json) throws IOException, EmployeeNotFoundException
  {
    try
    {
      logger.info("## Enter : Create Employee ##");

      ObjectMapper mapper = new ObjectMapper();
      final JsonNode data = mapper.readTree(json);

      String firstName = data.get("FirstName").asText();
      String middleName = data.get("MiddleName").asText();
      String lastName = data.get("LastName").asText();
      String dateOfJoining = data.get("DateOfJoining").asText();
      String dateOfExit = data.get("DateOfExit").asText();
      String status = data.get("Status").asText();

      Employee employee = new Employee();
      employee.setFirstName(firstName);
      employee.setMiddleName(middleName);
      employee.setLastName(lastName);
      employee.setDateOfJoining(dateOfJoining);
      employee.setDateOfExit(dateOfExit);
      employee.setStatus(status);

      employeeService.createEmployee(employee);

    }
    catch (Exception e)
    {
      return ResponseEntity.status(404).body(null);
    }
    logger.info("## Exit : Create Employee ##");
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION)
            .body("Employee record is created successfully ");
  }

  @ApiOperation(value = "Update Employee")
  @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
  public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody String json) throws EmployeeNotFoundException
  {
    try
    {
      logger.info("## Enter : Update Employee ##");
      ObjectMapper mapper = new ObjectMapper();
      final JsonNode data = mapper.readTree(json);
      String firstName = data.get("FirstName").asText();
      String status = data.get("Status").asText();
      List<Employee> employeeList = employeeService.getEmployees();
      for (Employee employee : employeeList)
      {
        if (id == employee.getId())
        {
          Employee emp = employee;
          emp.setFirstName(firstName);
          emp.setStatus(status);
          employeeService.updateEmployee(emp);
          break;
        }
      }
    }
    catch (Exception e)
    {
      return ResponseEntity.status(404).body("Failed to update Employee record !!");
    }
    logger.info("## Exit : Update Employee ##");
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION)
            .body("EmployeeID " + id + " is updated successfully !!!");
  }


  @ApiOperation(value = "Delete Employee")
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)

  public ResponseEntity deleteEmployee(@PathVariable Integer id) throws EmployeeNotFoundException
  {
    try
    {
      logger.info("## Enter : Delete Employee ##");
      employeeService.deleteEmployee(id);
    }
    catch (DataIntegrityViolationException de)
    {
      logger.info("## Exit : Delete Employee ##");
      return ResponseEntity.unprocessableEntity()
              .header(HttpHeaders.CONTENT_DISPOSITION)
              .body("Unable to Delete EmployeeID " + id + " due to" + de.getMessage());
    }
    catch (Exception e)
    {
      return ResponseEntity.status(404).body(null);
    }
    logger.info("## Exit : Delete Employee ##");
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION)
            .body("EmployeeID " + id + " is deleted successfully ");
  }
}
