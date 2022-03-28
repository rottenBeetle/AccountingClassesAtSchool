package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.ClassRepository;
import com.rottenbeetle.testtask.repo.StudentRepository;
import com.rottenbeetle.testtask.repo.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassController {

    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public ClassController(ClassRepository classRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String showClasses(Model model) {
        model.addAttribute("classes",classRepository.findAll());
        return "showClasses";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        model.addAttribute("myClass",new Class());
        model.addAttribute("teachers",teacherRepository.findAll());
        model.addAttribute("students",studentRepository.findAll());
        return "fillingClass";
    }

    @PostMapping("/saveClass")
    public String saveClass(@ModelAttribute("myClass") Class aClass, @RequestParam ("teacherId") Long teacherId, @RequestParam ("studentId") List<Long> studentId) {
        for (Long id: studentId) {
            aClass.addStudentsToClass(studentRepository.findById(id).get());
        }
        Teacher teacher = teacherRepository.findById(teacherId).get();
        aClass.setTeacher(teacher);
        classRepository.save(aClass);
        return "redirect:/classes/";
    }
    @GetMapping("/updateClass")
    public String updateClass(@RequestParam("classId") Long classId, Model model) {
        Class myClass = classRepository.findById(classId).get();
        model.addAttribute("myClass", myClass);
        model.addAttribute("teachers",teacherRepository.findAll());
        model.addAttribute("students",studentRepository.findAll());
        return "fillingClass";
    }
    @GetMapping("/deleteClass")
    public String deleteClass(@RequestParam("classId") Long id) {
        classRepository.deleteById(id);
        return "redirect:/classes/";
    }

    @GetMapping("/getStudentsInClassById")
    public String getStudentsInClassById(@RequestParam("classId") Long id,Model model){
        model.addAttribute("myClass",classRepository.findById(id).get());
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