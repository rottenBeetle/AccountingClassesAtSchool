<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Добавление класса</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<br><br>
<h2 align="center">Добавление класса</h2>

<form:form action="saveClass" modelAttribute="myClass" method="post">
    <form:hidden path="id"/>
    <div class="container mt-5">
        <div class="form-group">

            <label for="yearOfStudy">Год обучения</label>
            <form:select class="form-control" id="yearOfStudy" name="yearOfStudy" required="required" path="yearOfStudy">
                <option value="${2023}">2023</option>
                <option selected="selected" value="${2022}">2022</option>
                <option value="${2021}">2021</option>
                <option value="${2020}">2020</option>
                <option value="${2019}">2019</option>
                <option value="${2018}">2018</option>
                <option value="${2017}">2017</option>
                <option value="${2016}">2016</option>
                <option value="${2015}">2015</option>
                <option value="${2014}">2014</option>
                <option value="${2013}">2013</option>
                <option value="${2012}">2012</option>
                <option value="${2011}">2011</option>
                <option value="${2010}">2010</option>
                <option value="${2009}">2009</option>
                <option value="${2008}">2008</option>
            </form:select>
            <br><br>

            <label for="mnemonicCode">Мнемокод</label>
            <form:input class="form-control" id="mnemonicCode" required="required" path="mnemonicCode"
                        placeholder="9A" pattern="\d{1,2}[A-Za-zА-Яа-я]{1}"/>
            <br><br>

            <label for="teacher">Учитель</label>
            <select class="form-control" id="teacher" required="required" name="teacherId">
                <c:if test="${not empty teachers}">
                    <option value="${myClass.teacher.id}">${myClass.teacher.fullName}</option>
                </c:if>
                <c:forEach var="teacher" items="${teachers}">
                    <c:choose>
                        <c:when test="${empty teachers}">
                            <option disabled>На данный момент учителей нету</option>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${myClass.teacher.id != teacher.id}">
                                <option value="${teacher.id}">${teacher.fullName}</option>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <br><br>
            <label for="students">Ученики</label>
            <select multiple class="form-control" id="students" required="required" size="6" name="studentId">
                <c:choose>
                    <c:when test="${empty students}">
                        <option disabled>Отсутствуют ученики</option>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="student" items="${students}">
                            <c:choose>
                                <c:when test="${myClass.students.contains(student)}">
                                    <option selected="selected" value="${student.id}">${student.fullName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${student.id}">${student.fullName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
        <br><br>
        <br>
        <input class="btn btn-outline-primary" type="submit" value="Добавить">&nbsp;&nbsp;
        <a href="/classes/">Назад</a>
        <br>
        <br>
    </div>
</form:form>
</body>
</html>
