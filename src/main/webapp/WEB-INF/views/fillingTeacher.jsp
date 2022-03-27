<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление учителя</title>
</head>
<body>
<h2>Добавление учителя</h2>

<form:form action="saveTeacher" modelAttribute="teacher" method="post">
    <form:hidden path="id"/>
    Фамилия: <form:input required="required" path="lastName"/>
    <br><br>
    Имя: <form:input required="required" path="firstName"/>
    <br><br>
    Отчество:<form:input required="required" path="patronymic"/>
    <br><br>
    Дата рождения: <form:input required="required" name="dateBirth" type="date" path="dateBirth"/>
    <br><br>
    Пол:
    <form:select required="required" path="gender">
        <form:option value="MALE" label="Мужской"/>
        <form:option value="FEMALE" label="Женский"/>
        <form:option value="UNDEFINED" label="Не определен"/>
    </form:select>
    <br><br>
    Основной предмет:<form:input required="required" path="mainSubject"/>
    <br><br>
    <input type="submit" value="Добавить">
</form:form>
</body>
</html>
