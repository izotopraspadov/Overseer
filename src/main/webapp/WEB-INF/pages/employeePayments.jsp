<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="payments" scope="request" type="java.util.List"/>--%>
<html>
<head>
    <title>Платежи по сотруднику</title>
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
<div class="center">

    <c:if test="${empty payments}">
        <h1 class="h1" align="center">{Пусто}</h1>
    </c:if>

    <c:if test="${!empty payments}">
        <table class="tg">
            <tr>
                <th width="80">Дата</th>
                <th width="120">Тип контрагента</th>
                <th width="120">Контрагент</th>
                <th width="120">Операция</th>
                <th width="120">Безналичный расчёт</th>
                <th width="120">Начисление</th>
                <th width="120">Комментарий</th>
            </tr>
            <c:forEach items="${payments}" var="payment">
                <jsp:useBean id="payment" class="edu.guap.enclave.model.EmployeePayment"/>
                <tr>
                    <td>${payment.date}</td>
                    <td>${payment.typeCounterparty.description}</td>
                    <c:choose><c:when test="${payment.companyCounterparty != null}">
                        <td>${payment.companyCounterparty.title}</td>
                    </c:when><c:otherwise>
                        <td>${payment.employee.fullName}</td>
                    </c:otherwise></c:choose>
                    <td>${payment.transaction}</td>
                    <c:choose><c:when test="${payment.cashless}">
                        <td>да</td>
                    </c:when><c:otherwise>
                        <td>нет</td>
                    </c:otherwise></c:choose>
                    <c:choose><c:when test="${payment.charge}">
                        <td>да</td>
                    </c:when><c:otherwise>
                        <td>нет</td>
                    </c:otherwise></c:choose>
                    <td>${payment.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
