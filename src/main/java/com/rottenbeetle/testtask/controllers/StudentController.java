package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Student;
import com.rottenbeetle.testtask.repo.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String showStudents(Model model){
        List<Student> students = studentRepository.findAll();
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
        studentRepository.save(student);
        return "redirect:/students/";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam Long id,Model model){
        Student student = studentRepository.findById(id).get();
        model.addAttribute("student",student);
        return "fillingStudent";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")Long id){
        studentRepository.deleteById(id);
        return "redirect:/students/";
    }
}
