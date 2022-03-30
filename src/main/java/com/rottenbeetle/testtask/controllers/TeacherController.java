package com.rottenbeetle.testtask.controllers;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.TeacherRepository;
import com.rottenbeetle.testtask.service.TeacherService;
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
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public String showTeachers(Model model) {
        String keyword = null;
        return findPaginated(1,"id","desc",keyword,model);
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
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "fillingTeacher";
    }
    @GetMapping("/deleteTeacher")
    public String deleteClass(@RequestParam("teacherId") Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers/";
    }

    // /page/1?sortField=name&sortDir=acs
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                Model model){
        int pageSize = 10;
        Page<Teacher> page = teacherService.findPaginated(pageNo,pageSize,sortField,sortDir,keyword);
        List<Teacher> teacherList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword",keyword);

        model.addAttribute("teachers", teacherList);
        return "showTeacher";
    }
}
