package com.example.demo;
import com.sun.istack.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
@ResponseBody
public class StudentController {
    List<Student> students = new ArrayList<Student>();
    @GetMapping("/one")
    public String ReturnValue() {
        return "hello";
    }
    @PostMapping("/addStudent")
    public String addNewStudent(@RequestBody Student s1)
    {
        Student s = new Student(s1.Id, s1.Name);
        students.add(s);
        return "Student Added successfully";
    }
    @PutMapping(value="/updateStudent")
    public String updateStudentDetails(@RequestBody Student s1) throws Exception {
        for (Student student : students) {
            if (student.Id == s1.Id) {
                BeanUtils.copyProperties(s1, student);
            }
            return "Student details updated successfully";
        }
        return null;
    }
    @GetMapping("/Two")
    public List<Student>getAllStudent()
    {
     return students;
    }
    @GetMapping(value="/search/{Id}")
    @ResponseBody
    public Student getStudentById(@RequestParam int Id)
    {
        for (Student student: students)
        {
            if (Id==student.Id)
            {
                return student;
            }
        }
        return null;
    }
//    @GetMapping(value="/getStudent1/{name}")
//    public Student getStudentByName(@RequestParam String Name) throws Exception
//    {
//        for (Student student: students)
//        {
//            if (Name.equalsIgnoreCase(student.Name))
//            {
//                return student;
//            }
//        }
//        return null;
//    }


    @DeleteMapping(value="/deleteStudent/{Id}")
    public String deleteStudentById(@PathVariable int Id) throws Exception
    {
        for (Student student: students)
        {
            if (Id== student.Id)
            {
                students.remove(student);
                return student.Name+"Deleted Successfully";
            }
        }
        return null;
    }
}
