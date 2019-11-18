<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="regions" scope="request" type="java.util.List"/>--%>
<html>
<head>
    <title>Регионы</title>
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
<c:if test="${!empty regions}">
    <table class="tg">
        <tr>
            <th width="80">Название</th>
        </tr>
        <c:forEach items="${regions}" var="region">
            <jsp:useBean id="region" class="edu.guap.enclave.model.Region"/>
            <tr>
                <td>${region.title}</td>
                <c:if test="${swtch eq 'cmp'}">
                    <td><a href="/enclave/companies/region/${region.id}">[Выбрать]</a></td>
                </c:if>
                <c:if test="${swtch eq 'emp'}">
                    <td><a href="/enclave/employees/region/${region.id}">[Выбрать]</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
