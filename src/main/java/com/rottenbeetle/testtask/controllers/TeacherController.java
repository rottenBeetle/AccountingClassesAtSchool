package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/")
    public String showTeachers(Model model) {
        model.addAttribute("teachers",teacherRepository.findAll());
        return "showTeacher";
    }

    @GetMapping("/addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute("teacher",new Teacher());
        return "fillingTeacher";
    }

    @PostMapping("/saveTeacher")
    public String saveClass(@ModelAttribute("teacher") Teacher teacher,
                            @RequestParam("dateBirth") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateBirth) {
        teacher.setDateBirth(dateBirth);
        teacherRepository.save(teacher);
        return "redirect:/teachers/";
    }
    @GetMapping("/updateTeacher")
    public String updateTeacher(@RequestParam("teacherId") Long id, Model model) {
        Teacher teacher = teacherRepository.findById(id).get();
        model.addAttribute("teacher", teacher);
        return "fillingTeacher";
    }
    @GetMapping("/deleteTeacher")
    public String deleteClass(@RequestParam("teacherId") Long id) {
        teacherRepository.deleteById(id);
        return "redirect:/teachers/";
    }
}
