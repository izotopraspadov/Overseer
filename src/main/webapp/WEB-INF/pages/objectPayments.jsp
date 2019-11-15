<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="payments" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Платежи по объекту</title>
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
<c:if test="${!empty payments}">
    <table class="tg">
        <tr>
            <th width="80">Дата</th>
            <th width="120">Заказчик</th>
            <th width="120">Наша компания</th>
            <th width="120">Операция</th>
            <th width="120">Безнал</th>
            <th width="120">Комментарий</th>
        </tr>
        <c:forEach items="${payments}" var="payment">
            <tr>
                <td>${payment.date}</td>
                <td>${payment.company.title}</td>
                <td>${payment.ourCompany.title}</td>
                <td>${payment.transaction}</td>
                <c:choose><c:when test="${payment.cashless}">
                    <td>да</td>
                </c:when><c:otherwise>
                    <td>нет</td>
                </c:otherwise></c:choose>
                <td>${payment.comment}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
