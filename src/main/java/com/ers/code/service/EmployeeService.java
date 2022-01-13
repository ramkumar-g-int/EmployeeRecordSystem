package com.ers.code.service;

import com.ers.code.model.Employee;
import com.ers.code.repository.EmployeeRepository;
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
