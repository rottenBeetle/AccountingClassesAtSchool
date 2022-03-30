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
            <form:input class="form-control" id="yearOfStudy" required="required" path="yearOfStudy"
                        placeholder="2022"/>
            <br><br>

            <label for="mnemonicCode">Мнемокод</label>
            <form:input class="form-control" id="mnemonicCode" required="required" path="mnemonicCode"
                        placeholder="9A"/>
            <br><br>

            <label for="teacher">Учитель</label>
            <select class="form-control" id="teacher" required="required" name="teacherId">
                <option value="${myClass.teacher.id}">${myClass.teacher.fullName}</option>
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
                        <option disabled>Отсутствуют ученики без класса</option>
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
        <br><br>
        <input class="btn btn-outline-primary" type="submit" value="Добавить">
    </div>
</form:form>
</body>
</html>
