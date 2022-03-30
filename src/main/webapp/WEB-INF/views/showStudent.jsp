<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Ученики</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<br><br>
<h2 align="center">Ученики</h2>
<div class="container mt-5">
    <input type="button" value="Добавить ученика" onclick="window.location.href = '/students/addStudent'"/>
    <br><br>
    <div class="col-sm-3">
        <form action="/students/page/1">
            <input type="hidden" name="sortField" value="${sortField}">
            <input type="hidden" name="sortDir" value="${sortDir}">
            <div class="input-group">
                <input id="keyword" name="keyword" value="${keyword}" class="form-control rounded"
                       placeholder="Search" aria-label="Search" aria-describedby="search-addon" required/>
                <input type="submit" class="btn btn-outline-primary" value="Поиск"/>
                <input type="button" class="btn btn-outline-primary" value="Очистить" id="btnClear"
                       onclick="window.location.href = '/students/'"/>
            </div>
        </form>
    </div>
    <br><br>

    <table id="selectedColumn" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th class="th-sm">
                <a href="/students/page/${currentPage}?sortField=lastName&sortDir=${reverseSortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Фамилия</a>
            </th>
            <th class="th-sm">
                <a href="/students/page/${currentPage}?sortField=firstName&sortDir=${reverseSortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Имя</a>
            </th>
            <th class="th-sm">
                <a href="/students/page/${currentPage}?sortField=patronymic&sortDir=${reverseSortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Отчество</a>
            </th>
            <th class="th-sm">
                <a href="/students/page/${currentPage}?sortField=dateBirth&sortDir=${reverseSortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Дата
                    рождения</a>
            </th>
            <th class="th-sm">
                <a href="/students/page/${currentPage}?sortField=gender&sortDir=${reverseSortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Пол</a>
            </th>
            <th class="th-sm">Операции
            </th>
        </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>


    <%--Пагинация--%>
    <c:if test="${totalPages > 1}">
        <ul class="pagination">
            <li class="page-item">
                <c:choose>
                    <c:when test="${currentPage < totalPages}">
                        <a class="page-link"
                           href="/students/page/${currentPage + 1}?sortField=${sortField}&sortDir=${sortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Следующая</a>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${currentPage < totalPages}">
                            <a class="page-link">Следующая</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </li>

            <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                <li class="page-item">
                    <c:choose>
                        <c:when test="${currentPage != loop.index}">
                            <a class="page-link"
                               href="/students/page/${loop.index}?sortField=${sortField}&sortDir=${sortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">${loop.index}</a>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${currentPage != loop.index}">
                                <a class="page-link">${loop.index}</a> &nbsp; &nbsp;
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>

            <li class="page-item">
                <c:choose>
                    <c:when test="${currentPage < totalPages}">
                        <a class="page-link"
                           href="/students/page/${totalPages}?sortField=${sortField}&sortDir=${sortDir}${keyword != null ? "&keyword=".concat(keyword) : ''}">Последняя</a>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${currentPage < totalPages}">
                            <a class="page-link">Следующая</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </c:if>
    <a href="/">Назад</a>
</div>
</body>
</html>
