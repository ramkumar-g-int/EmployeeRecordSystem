package com.ers.code.exception;

public class EmployeeNotFoundException extends RuntimeException
{
  public EmployeeNotFoundException(String message)
  {
    super(message);
  }

}
