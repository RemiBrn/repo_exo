package org.example.exercice_student.dao;

import org.example.exercice_student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student, Long> {
    List<Student> findAllByLastname(String search);
    List<Student> findAllByLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(String firstname, String lastname);
}
