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
<h1>Все ученики класса - ${myClass.mnemonicCode}</h1>

<table>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Пол</th>
    </tr>
    <c:forEach var="student" items="${myClass.students}">

        <tr>
            <td>${student.lastName}</td>
            <td>${student.firstName}</td>
            <td>${student.patronymic}</td>
            <td>${student.stringDateBirth()}</td>
            <td>${student.gender}</td>
        </tr>
    </c:forEach>
</table>
<br><br>
<a href="/classes/">Назад</a>
</body>
</html>
