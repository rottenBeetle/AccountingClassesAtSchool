package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.service.ClassService;
import com.rottenbeetle.testtask.service.StudentService;
import com.rottenbeetle.testtask.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public ClassController(ClassService classService, TeacherService teacherService, StudentService studentService) {
        this.classService = classService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String showClasses(Model model) {
        model.addAttribute("classes",classService.getAllClasses());
        return "showClasses";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        model.addAttribute("myClass",new Class());
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("students",studentService.getAllStudents());
        return "fillingClass";
    }

    @PostMapping("/saveClass")
    public String saveClass(@ModelAttribute("myClass") Class aClass, @RequestParam ("teacherId") Long teacherId, @RequestParam ("studentId") List<Long> studentId) {
        for (Long id: studentId) {
            aClass.addStudentsToClass(studentService.getStudent(id));
        }
        Teacher teacher = teacherService.getTeacher(teacherId);
        aClass.setTeacher(teacher);
        classService.saveClass(aClass);
        return "redirect:/classes/";
    }
    @GetMapping("/updateClass")
    public String updateClass(@RequestParam("classId") Long classId, Model model) {
        Class myClass = classService.getClass(classId);
        model.addAttribute("myClass", myClass);
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("students",studentService.getAllStudents());
        return "fillingClass";
    }
    @GetMapping("/deleteClass")
    public String deleteClass(@RequestParam("classId") Long id) {
        classService.deleteClass(id);
        return "redirect:/classes/";
    }

    @GetMapping("/getStudentsInClassById")
    public String getStudentsInClassById(@RequestParam("classId") Long id,Model model){
        model.addAttribute("myClass",classService.getClass(id));
        return "showStudentsInClass";
    }
    @GetMapping("/sortByYearOfStudy")
    public String sortByYearOfStudy(){
        return "showClasses";
    }

    @GetMapping("/sortByMnemonicCode")
    public String sortByMnemonicCode(){
        return "showClasses";
    }

    @GetMapping("/sortByTeacher")
    public String sortByTeacher(){
        return "showClasses";
    }


}