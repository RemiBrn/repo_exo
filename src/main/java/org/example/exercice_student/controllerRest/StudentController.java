package org.example.exercice_student.controllerRest;


import org.example.exercice_student.entity.Student;
import org.example.exercice_student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student/")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent (@Validated @RequestBody Student student){
        studentService.createStudent(student);
        return new ResponseEntity< >(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent (){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable("id") long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student Deleted");
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent (@RequestBody Student student){
        studentService.updateStudent(student.getId(),student);
        return ResponseEntity.ok(student);
    }
}
