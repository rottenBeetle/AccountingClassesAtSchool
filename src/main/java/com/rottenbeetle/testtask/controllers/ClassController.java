package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.StudentRepository;
import com.rottenbeetle.testtask.repo.TeacherRepository;
import com.rottenbeetle.testtask.service.ClassService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public ClassController(ClassService classService, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.classService = classService;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String showClasses(Model model) {
        String keyword = null;
        return findPaginated(1,"yearOfStudy","desc",keyword,model);
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
        classService.saveClass(aClass);
        return "redirect:/classes/";
    }
    @GetMapping("/updateClass")
    public String updateClass(@RequestParam("classId") Long classId, Model model) {
        Class myClass = classService.getClassById(classId);
        model.addAttribute("myClass", myClass);
        model.addAttribute("teachers",teacherRepository.findAll());
        model.addAttribute("students",studentRepository.findAll());
        return "fillingClass";
    }
    @GetMapping("/deleteClass")
    public String deleteClass(@RequestParam("classId") Long id) {
        classService.deleteClassById(id);
        return "redirect:/classes/";
    }

    @GetMapping("/getStudentsInClassById")
    public String getStudentsInClassById(@RequestParam("classId") Long id,Model model){
        model.addAttribute("myClass",classService.getClassById(id));
        return "showStudentsInClass";
    }

    // /page/1?sortField=name&sortDir=acs
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                Model model){
        int pageSize = 3;
        Page<Class> page = classService.findPaginated(pageNo,pageSize,sortField,sortDir,keyword);
        List<Class> classList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword",keyword);

        model.addAttribute("classes", classList);
        return "showClasses";
    }
}