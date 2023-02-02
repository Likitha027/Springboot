package com.example.demo;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/sample")
public class EmployeeController {
    @Autowired
    private EmployeeRepository cRepo;
    List<Employee> employees = new ArrayList<Employee>();

    @GetMapping("/one")
    public String ReturnValue() {
        return "hello";
    }

    @PostMapping("/Add")
    @ResponseBody
    public String AddEmployee(@RequestBody Employee employee) {
       // Employee e = new Employee(employee.Id,employee.EmpName, employee.EmpDept);
       // employees.add(e);
        return cRepo.save(employee)+"Employee added successfully";
    }
    @GetMapping("/all")
    public List<Employee>getAllEmployees(){
        return cRepo.findAll();
    }


    @GetMapping("/two/{Id}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer Id) {
        Employee employee=cRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+Id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer Id,@RequestBody Employee employee)
    {
        Employee updateEmployee=cRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+Id));
        updateEmployee.setId(employee.getId());
        updateEmployee.setEmpName(employee.getEmpName());
        updateEmployee.setEmpDept(employee.getEmpDept());
        cRepo.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }


    @DeleteMapping("/delete/{Id}")
    @ResponseBody
    public  ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer Id)
    {
        Employee deleteEmployee=cRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+Id));
        cRepo.delete(deleteEmployee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
//    @GetMapping("/Three/{id}")
//@RequestBody
//    public Employee getEmployeeId(@PathParam("Id") String Id)
//    {
//        Employee a=new Employee("Likitha",65,"IT");
//        return a;
//    }