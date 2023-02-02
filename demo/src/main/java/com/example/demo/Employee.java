package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
   private Integer Id;
    @Column(name="EmpName")
    private String EmpName;
    @Column(name="EmpDept")
   private String EmpDept;
}
