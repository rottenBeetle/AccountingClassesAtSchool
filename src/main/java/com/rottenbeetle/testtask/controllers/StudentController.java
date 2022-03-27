package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Student;
import com.rottenbeetle.testtask.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String showStudents(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "showStudent";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "fillingStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student,
                              @RequestParam("dateBirth") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateBirth){
        student.setDateBirth(dateBirth);
        studentService.saveStudent(student);
        return "redirect:/students/";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam Long id,Model model){
        Student student = studentService.getStudent(id);
        model.addAttribute("student",student);
        return "fillingStudent";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")Long id){
        studentService.deleteStudent(id);
        return "redirect:/students/";
    }
}
