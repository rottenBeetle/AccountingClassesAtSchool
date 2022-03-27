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
    <title>Show classes</title>
</head>
<body>
<table>
    <tr>
        <th>Год обучения</th>
        <th>Мнемокод</th>
        <th>Учитель</th>
        <th>Ученики</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="myClass" items="${classes}">

        <c:url var="getStudentsInClassById" value="/classes/getStudentsInClassById">
            <c:param name="classId" value="${myClass.id}"/>
        </c:url>
        <c:url var="updateButton" value="/classes/updateClass">
            <c:param name="classId" value="${myClass.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/classes/deleteClass">
            <c:param name="classId" value="${myClass.id}"/>
        </c:url>

        <tr>
            <td>${myClass.yearOfStudy}</td>
            <td>${myClass.mnemonicCode}</td>
            <td>${myClass.teacher.fullName}</td>
            <td><input type = "button" value="Посмотреть" onclick="window.location.href = '${getStudentsInClassById}'" ></td>
            <td><input type="button" value="Изменить" onclick="window.location.href = '${updateButton}'">
                <input type="button" value="Удалить" onclick="window.location.href = '${deleteButton}'"></td>
        </tr>
    </c:forEach>
</table>
<br>
<input type = "button" value="Добавить класс" onclick="window.location.href = 'addClass'" />
<br><br>
<a href="/">Назад</a>
</body>
</html>
