package com.ers.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeRecordSystemMainApp
{
  private static final Logger LOG = LoggerFactory.getLogger(EmployeeRecordSystemMainApp.class);

  public static void main(String[] args)
  {
    SpringApplication.run(EmployeeRecordSystemMainApp.class, args);
  }
}
