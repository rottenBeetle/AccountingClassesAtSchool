<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.03.2022
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show students</title>
</head>
<body>
<table>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Пол</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="student" items="${students}">

        <c:url var="updateButton" value="/students/updateStudent">
            <c:param name="studentId" value="${student.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/students/deleteStudent">
            <c:param name="studentId" value="${student.id}"/>
        </c:url>
        <tr>
            <td>${student.lastName}</td>
            <td>${student.firstName}</td>
            <td>${student.patronymic}</td>
            <td>${student.stringDateBirth()}</td>
            <td>${student.gender}</td>
            <td><input type="button" value="Изменить" onclick="window.location.href = '${updateButton}'">
                <input type="button" value="Удалить" onclick="window.location.href = '${deleteButton}'"></td>
        </tr>
    </c:forEach>
</table>
<br>
<input type="button" value="Добавить ученика" onclick="window.location.href = 'addStudent'"/>
<br><br>
<a href="/">Назад</a>
</body>
</html>
