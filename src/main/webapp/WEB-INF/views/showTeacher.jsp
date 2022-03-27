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
    <title>Show teachers</title>
</head>
<body>

<table>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Пол</th>
        <th>Основной предмет</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="teacher" items="${teachers}">

        <c:url var="updateButton" value="/teachers/updateTeacher">
            <c:param name="teacherId" value="${teacher.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/teachers/deleteTeacher">
            <c:param name="teacherId" value="${teacher.id}"/>
        </c:url>
        <tr>
            <td>${teacher.lastName}</td>
            <td>${teacher.firstName}</td>
            <td>${teacher.patronymic}</td>
            <td>${teacher.stringDateBirth()}</td>
            <td>${teacher.gender}</td>
            <td>${teacher.mainSubject}</td>
            <td><input type="button" value="Изменить" onclick="window.location.href = '${updateButton}'">
                <input type="button" value="Удалить" onclick="window.location.href = '${deleteButton}'"></td>
        </tr>
    </c:forEach>
</table>
<br>
<input type="button" value="Добавить учителя" onclick="window.location.href = 'addTeacher'"/>
<br><br>
<a href="/">Назад</a>
</body>
</html>
