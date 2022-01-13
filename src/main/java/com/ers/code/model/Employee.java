package com.ers.code.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Data
public class Employee implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "FirstName")
  private String firstName;

  @Column(name = "MiddleName")
  private String middleName;

  @Column(name = "LastName")
  private String lastName;

  @Column(name = "DateOfJoining")
  private String dateOfJoining;

  @Column(name = "DateOfExit")
  private String dateOfExit;

  @Column(name = "Status")
  private String status;

}
