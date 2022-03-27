<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление класса</title>
</head>
<body>
<h2>Добавление класса</h2>

<form:form action="saveClass" modelAttribute="myClass" method="post">
    <form:hidden path="id"/>
    Год обучения: <form:input required="required" path="yearOfStudy"/>
    <br><br>
    Мнемокод: <form:input required="required" path="mnemonicCode"/>
    <br><br>

    Учитель:
    <label>
        <select required="required" name="teacherId">
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
    </label>
    <br><br>

    <label>
        Ученики:
        <select required="required" size="6" multiple name="studentId">
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
    </label>

    <br><br>
    <br><br>
    <input type="submit" value="Добавить">
</form:form>
</body>
</html>
