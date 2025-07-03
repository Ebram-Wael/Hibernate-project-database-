package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="departments_gen", sequenceName="departments_seq", allocationSize = 1)
public class Department implements Serializable {
    private static final long serialVersionUID = -915428707036605461L;
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="departments_gen")
    @Id
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "manager_id")
    private Integer managerId;
    @Column(name = "location_id")
    private Integer locationId;


}
