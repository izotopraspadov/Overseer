<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Финансы</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>
<c:if test="${!empty orderedObjectPayments}">
    <table class="tg">
        <tr>
            <th width="80">Дата</th>
            <th width="120">Заказчик</th>
            <th width="120">Наша компания</th>
            <th width="120">Операция</th>
            <th width="120">Безнал</th>
            <th width="120">Комментарий</th>
        </tr>
        <c:forEach items="${orderedObjectPayments}" var="payment">
            <tr>
                <td>${payment.date}</td>
                <td>${payment.company.title}</td>
                <td>${payment.ourCompany.title}</td>
                <td>${payment.transaction}</td>
                <td>${payment.cashless}</td>
                <td>${payment.comment}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${!empty employeePayments}">
    <table class="tg">
        <tr>
            <th width="80">Дата</th>
            <th width="120">Контрагент</th>
            <th width="120">Операция</th>
            <th width="120">Безнал</th>
            <th width="120">Начисление</th>
            <th width="120">Комментарий</th>
        </tr>
        <c:forEach items="${employeePayments}" var="payment">
            <tr>
                <td>${payment.date}</td>
                <td>${payment.typeCounterparty}</td>
                <td>${payment.transaction}</td>
                <td>${payment.cashless}</td>
                <td>${payment.charge}</td>
                <td>${payment.comment}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
