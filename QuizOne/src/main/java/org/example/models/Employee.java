package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employees", schema = "system")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="employees_gen", sequenceName="hr.employees_seq", allocationSize = 1)
public class Employee implements Serializable {

  private static final long serialVersionUID = -915428707036605461L;

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="employees_gen")
  @Column(name = "employee_id")
  private Integer employeeId;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  private String email;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "hire_date")
  private Date hireDate;
  @Column(name = "job_id")
  private String jobId;
  @Column(name = "salary")
  private Integer salary;
  @Column(name = "commission_pct")
  private Double commissionPct;
  @Column(name = "manager_id")
  private Integer managerId;
  @Column(name = "department_id")
  private Integer departmentId;

}
