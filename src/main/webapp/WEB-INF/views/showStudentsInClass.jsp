<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Ученики - ${myClass.mnemonicCode}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<br><br>
<h2 align="center">Ученики класса - ${myClass.mnemonicCode}</h2>
<div class="container mt-5">
<table class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">Фамилия</th>
        <th class="th-sm">Имя</th>
        <th class="th-sm">Отчество</th>
        <th class="th-sm">Дата рождения</th>
        <th class="th-sm">Пол</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${myClass.students}">

        <tr>
            <td>${student.lastName}</td>
            <td>${student.firstName}</td>
            <td>${student.patronymic}</td>
            <td>${student.stringDateBirth()}</td>
            <td>${student.gender}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br><br>
<a href="/classes/">Назад</a>
</div>
</body>
</html>
