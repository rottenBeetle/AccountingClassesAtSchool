package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Student;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.StudentRepository;
import com.rottenbeetle.testtask.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
        String keyword = null;
        return findPaginated(1,"id","desc",keyword,model);
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
    public String updateStudent(@RequestParam("studentId")Long id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "fillingStudent";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students/";
    }

    // /page/1?sortField=name&sortDir=acs
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                Model model){
        int pageSize = 10;
        Page<Student> page = studentService.findPaginated(pageNo,pageSize,sortField,sortDir,keyword);
        List<Student> studentList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword",keyword);

        model.addAttribute("students", studentList);
        return "showStudent";
    }
}
