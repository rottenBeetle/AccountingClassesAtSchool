package com.rottenbeetle.testtask.entity;

import org.hibernate.annotations.Generated;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "patronymic")
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateBirth")
    private Date dateBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "mainSubject")
    private String mainSubject;
    @OneToOne(mappedBy = "teacher",cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private Class classTeacher;

    public Teacher() {
    }

    public Teacher(String lastName, String firstName, String patronymic, Date dateBirth, Gender gender, String mainSubject, Class classTeacher) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.mainSubject = mainSubject;
        this.classTeacher = classTeacher;
    }

    public Class getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Class classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getFullName(){
        return lastName + " " + firstName + " " + patronymic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateBirth() throws ParseException {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(String mainSubject) {
        this.mainSubject = mainSubject;
    }

    public String stringDateBirth(){
        String date = dateBirth.toString().substring(0,10);
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(lastName, teacher.lastName) && Objects.equals(firstName, teacher.firstName) && Objects.equals(patronymic, teacher.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, patronymic);
    }


}
