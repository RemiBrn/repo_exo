//package org.example.exercice_student.controller;
//
//import jakarta.validation.Valid;
//import org.example.exercice_student.entity.Student;
//import org.example.exercice_student.service.AuthService;
//import org.example.exercice_student.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//@Controller
//public class StudentController {
//    private String location = "src/main/resources/static/image";
//    private final StudentService studentService;
//    private final AuthService authService;
//
//    @Autowired
//    public StudentController(StudentService studentService, AuthService authService) {
//        this.studentService = studentService;
//        this.authService = authService;
//    }
//
//    @RequestMapping("/")
//    public String home() {
//        if (authService.isLogged()) {
//            return "home";
//        }
//        return "redirect:/login";
//    }
//
//    @RequestMapping("/formulaire")
//    public String formAddStudent(Model model) {
//        if (authService.isLogged()) {
//            model.addAttribute("student", new Student());
//            return "form";
//        }
//        return "redirect:/login";
//    }
//
//    @PostMapping("/add")
//    public String addStudent(@Valid @ModelAttribute("student") Student student,
//                             BindingResult bindingResult,
//                             @RequestParam("image") MultipartFile image) throws IOException {
//        if (authService.isLogged()) {
//            if (bindingResult.hasErrors()) {
//                return "form";
//            } else {
//                if (student.getId() != null) {
//                    studentService.updateStudent(student.getId(), student);
//                } else {
//                    Path destination = Paths.get(location).resolve(image.getOriginalFilename()).toAbsolutePath();
//                    student.setPhoto(image.getOriginalFilename());
//                    InputStream inputStream = image.getInputStream();
//                    Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
//                    studentService.createStudent(student);
//                }
//                return "redirect:/students";
//            }
//        }
//        return "redirect:/login";
//    }
//
//    @RequestMapping("/students") // /students?search=Toto
//    public String showStudents(@RequestParam(name = "search", required = false) String search, Model model) {
//        if (authService.isLogged()) {
//            if (search == null) {
//                model.addAttribute("students", studentService.getAllStudents());
//            } else {
//                model.addAttribute("students", studentService.searchStudents(search));
//            }
//            return "list";
//        }
//        return "redirect:/login";
//    }
//
//    @RequestMapping("/student/{id}")
//    public String showStudent(@PathVariable("id") Long id, Model model) {
//        if (authService.isLogged()) {
//            model.addAttribute("student", studentService.getStudentById(id));
//            return "detail";
//        }
//        return "redirect:/login";
//    }
//
//    @RequestMapping("/delete")
//    public String delete(@RequestParam("studentId") Long id) {
//        if (authService.isLogged()) {
//            studentService.deleteStudent(id);
//            return "redirect:/students";
//        }
//        return "redirect:/login";
//    }
//
//    @RequestMapping("/update")
//    public String formUpdate(@RequestParam("studentId") Long id, Model model) {
//        if (authService.isLogged()) {
//            Student student = studentService.getStudentById(id);
//            model.addAttribute("student", student);
//            return "form";
//        }
//        return "redirect:/login";
//    }
//}
