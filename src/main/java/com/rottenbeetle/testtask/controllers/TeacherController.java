package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Gender;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.service.TeacherService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public String showTeachers(Model model) {
        model.addAttribute("teachers",teacherService.getAllTeachers());
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
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers/";
    }
    @GetMapping("/updateTeacher")
    public String updateTeacher(@RequestParam("teacherId") Long id, Model model) {
        Teacher teacher = teacherService.getTeacher(id);
        model.addAttribute("teacher", teacher);
        return "fillingTeacher";
    }
    @GetMapping("/deleteTeacher")
    public String deleteClass(@RequestParam("teacherId") Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers/";
    }
}
