package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.StudentRepository;
import com.rottenbeetle.testtask.repo.TeacherRepository;
import com.rottenbeetle.testtask.service.ClassService;
import com.rottenbeetle.testtask.service.StudentService;
import com.rottenbeetle.testtask.service.TeacherService;
import org.springframework.data.domain.Page;
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
        String keyword = null;
        return findPaginated(1,"id","desc",keyword,model);
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
            aClass.addStudentsToClass(studentService.getStudentById(id));
        }
        Teacher teacher = teacherService.getTeacherById(teacherId);
        aClass.setTeacher(teacher);
        classService.saveClass(aClass);
        return "redirect:/classes/";
    }
    @GetMapping("/updateClass")
    public String updateClass(@RequestParam("classId") Long classId, Model model) {
        Class myClass = classService.getClassById(classId);
        model.addAttribute("myClass", myClass);
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("students",studentService.getAllStudents());
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
        int pageSize = 10;
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