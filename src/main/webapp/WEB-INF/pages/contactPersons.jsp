<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="persons" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Конт персоны</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>
<br>
<br>
<br>
<br>
<c:if test="${!empty persons}">
    <table class="tg">
        <tr>
            <th width="80">ФИО</th>
            <th width="120">Номера телефонов</th>
            <th width="120">Почтовые адреса</th>
        </tr>
        <c:forEach items="${persons}" var="person">
            <jsp:useBean id="person" class="edu.guap.enclave.model.ContactPerson"/>
            <tr>
                <td>${person.fullName}</td>
                <td>
                    <c:forEach items="${person.phones}" var="phone">
                        ${phone.number};
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${person.emails}" var="email">
                        ${email.email};
                    </c:forEach>
                </td>
                <td><a href="/enclave/companies/contactperson/${person.id}">[Выбрать]</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
