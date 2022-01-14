package com.ers.code.model;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "EmployeeRoleMapping")
@Data
public class EmployeeRoleMapping implements Serializable
{
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "emp_role_id", referencedColumnName = "id")
  private Employee employee;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "role_master_id", referencedColumnName = "id")
  private RoleMaster roleMaster;

  @Column(name = "EffectiveDate")
  private Date effectiveDate;

}
