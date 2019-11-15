<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="orderedObjectPayments" scope="request" type="java.util.List"/>
<jsp:useBean id="employeePayments" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Финансы</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>

<table class="find">
    <tr>
        <form action="/enclave/finance//date" method="POST">
            <th><p>Поиск: </p></th>
            <th>
                <div id="date">
                    <input type="date" name="date"/>
                </div>
            </th>
            <th>
                <input type="submit" value="Найти"/>
            </th>
        </form>
    </tr>
</table>

<div class="center">
    <p>Платежи по объектам:</p>
    <c:if test="${empty orderedObjectPayments}">
        <h1 class="h1" align="center">{Пусто}</h1>
    </c:if>

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
    <p>Платежи по сотрудникам:</p>
    <c:if test="${empty employeePayments}">
        <h1 class="h1" align="center">{Пусто}</h1>
    </c:if>
    <c:if test="${!empty employeePayments}">
        <table class="tg">
            <tr>
                <th width="80">Дата</th>
                <th width="120">Тип контрагента</th>
                <th width="120">Контрагент</th>
                <th width="120">Операция</th>
                <th width="120">Безнал</th>
                <th width="120">Начисление</th>
                <th width="120">Комментарий</th>
            </tr>

            <c:forEach items="${employeePayments}" var="payment">
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
<br>
<br>
<br>
<br>
</body>
</html>
