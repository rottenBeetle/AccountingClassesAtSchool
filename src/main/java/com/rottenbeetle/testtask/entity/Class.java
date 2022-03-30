package com.rottenbeetle.testtask.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "yearOfStudy")
    private int yearOfStudy;
    @Column(name = "mnemonicCode")
    private String mnemonicCode;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private List<Student> students;

    public Class() {
    }

    public Class(int yearOfStudy, String mnemonicCode, Teacher teacher, List<Student> students) {
        this.yearOfStudy = yearOfStudy;
        this.mnemonicCode = mnemonicCode;
        this.teacher = teacher;
        this.students = students;
    }

    public void addStudentsToClass(Student student){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(student);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getSizeStudents(){
        return students.size();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
