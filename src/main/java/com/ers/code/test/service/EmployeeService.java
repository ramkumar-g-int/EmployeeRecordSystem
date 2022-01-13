package com.ers.code.test.service;

import com.ers.code.test.model.Employee;
import com.ers.code.test.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
  @Autowired
  EmployeeRepository employeeRepository;

  public List<Employee> getEmployees()
  {
    return employeeRepository.findAll();
  }

  public void updateEmployee(Employee employee)
  {
    employeeRepository.save(employee);
  }

  public void deleteEmployee(Integer id)
  {
    employeeRepository.deleteById(id);
  }

  public void createEmployee(Employee employee)
  {
    employeeRepository.save(employee);
  }
}
