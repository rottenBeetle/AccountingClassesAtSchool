<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Добавление ученика</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<br><br>
<h2 align="center">Добавление ученика</h2>

<form:form action="saveStudent" modelAttribute="student" method="post">
    <form:hidden path="id"/>
    <div class="container mt-5">
        <div class="form-group">
            <label for="lastName">Фамилия</label>
            <form:input class="form-control" id="lastName" required="required" path="lastName"/>
            <br><br>

            <label for="firstName">Имя</label>
            <form:input class="form-control" id="firstName" required="required" path="firstName"/>
            <br><br>

            <label for="patronymic">Отчество</label>
            <form:input class="form-control" id="patronymic" required="required" path="patronymic"/>
            <br><br>

            <label for="dateBirth">Дата рождения</label>
            <form:input class="form-control" id="dateBirth" type="date" required="required" path="dateBirth"/>
            <br><br>

            <label for="gender">Пол</label>
            <form:select class="form-control" id="gender" required="required" path="gender">
                <form:option value="MALE" label="Мужской"/>
                <form:option value="FEMALE" label="Женский"/>
                <form:option value="UNDEFINED" label="Не определен"/>
            </form:select>
            <br><br>
            <input class="btn btn-outline-primary" type="submit" value="Добавить">
        </div>
    </div>
</form:form>
</body>
</html>
