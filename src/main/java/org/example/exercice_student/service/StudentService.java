package org.example.exercice_student.service;

import org.example.exercice_student.dao.StudentRepository;
import org.example.exercice_student.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchStudents(String search){
        return studentRepository.findAllByLastname(search);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student updateStudent){
        Student studentExist = getStudentById(id);
        if(studentExist != null){
            studentRepository.save(updateStudent);
        }
        return studentExist;
    }
}
