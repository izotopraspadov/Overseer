<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="objects" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Объекты</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
    <script language="javascript" type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.js"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<script>
    $(function () {
        $('#category').change(function () {
            var selection = $(this).val();
            switch (selection) {
                case "contract_is_need":
                case "contract_exists":
                case "underway":
                    $("#bools").show();
                    $("#data").hide();
                    $("#date").hide();
                    break;
                case "title":
                case "expected_payment":
                case "order_type":
                case "sum":
                case "payment_order":
                case "number_of_lines":
                    $("#bools").hide();
                    $("#data").show();
                    $("#date").hide();
                    break;
                case "planned_start_date":
                case "actual_start_date":
                case "planned_end_date":
                case "actual_end_date":
                    $("#bools").hide();
                    $("#data").hide()
                    $("#date").show();
                    break;
                case "company":
                case "group":
                case "manager":
                    $("#bools").hide();
                    $("#data").hide();
                    $("#date").hide();
                    break;
            }
        });
    });
</script>
<table class="find">
    <form action="/enclave/objects/token" method="POST">
        <tr>
            <th>
                <p>Поиск: </p>
            </th>
            <th>
                <div id="data">
                    <input type="text" name="data"/>
                </div>
            </th>
            <th>
                <div id="date" style="display:none;">
                    <input type="date" name="date"/>
                </div>
            </th>
            <th>
                <div id="bools" style="display:none;">
                    <select name="bool">
                        <option value="true" type="text" name="flag">Да</option>
                        <option value="false" type="text" name="flag">Нет</option>
                    </select>
                </div>
            </th>
            <th>
                <select name="category" id="category">
                    <option value="title" type="text" name="select">По названию</option>
                    <option value="expected_payment" type="text" name="select">Оплата должна быть</option>
                    <option value="order_type" type="text" name="select">Тип</option>
                    <option value="contract_is_need" type="text" name="select">Договор нужен</option>
                    <option value="contract_exists" type="text" name="select">Договор есть</option>
                    <option value="planned_start_date" type="text" name="select">По план. дате начала</option>
                    <option value="actual_start_date" type="text" name="select">По факт. дате начала</option>
                    <option value="planned_end_date" type="text" name="select">По план. дате завершения</option>
                    <option value="actual_end_date" type="text" name="select">По факт. дате завершения</option>
                    <option value="sum" type="text" name="select">По сумме</option>
                    <option value="payment_order" type="text" name="select">По порядку оплаты</option>
                    <option value="number_of_lines" type="text" name="select">По кол-ву строк</option>
                    <option value="underway" type="text" name="select">В работе</option>
                    <option value="company" type="text" name="select">По заказчику</option>
                    <option value="group" type="text" name="select">По группе</option>
                    <option value="manager" type="text" name="select">По менеджеру</option>
                </select>
            </th>
            <th>
                <input type="submit" value="Найти"/>
            </th>
    </form>
    </tr>
</table>
<%--style="position:absolute; left:+870px;"--%>
<br>
<br>
<br>
<br>
<div class="center">
    <c:if test="${empty objects}">
        <h1 class="h1" align="center">{Пусто}</h1>
    </c:if>

    <c:if test="${!empty objects}">
        <table class="tg">
            <tr>
                <th>Название</th>
                <th>Заказчик</th>
                <th>Оплата по безналу</th>
                <th>Договор нужен</th>
                <th>Договор есть</th>
                <th>План. дата начала</th>
                <th>Факт. дата начала</th>
                <th>План. дата завершения</th>
                <th>Факт. дата завершения</th>
                <th>Сумма</th>
                <th>Оплата должна быть</th>
                <th>Порядок оплаты</th>
                <th>Тип</th>
                <th>Кол-во строк</th>
                <th>Группа</th>
                <th>Менеджер по работе с клиентами</th>
                <th>В работе</th>
                <th>Платежи</th>
                <th>План. время</th>
                <th>Факт. время</th>
                <th>Задачи</th>
            </tr>
            <c:forEach items="${objects}" var="object">
                <jsp:useBean id="object" class="edu.guap.enclave.model.OrderedObject"/>
                <tr>
                    <td>${object.title}</td>
                    <td>${object.company.title}</td>
                    <c:choose><c:when test="${object.cashless}">
                        <td>да</td>
                    </c:when><c:otherwise>
                        <td>нет</td>
                    </c:otherwise></c:choose>
                    <c:choose><c:when test="${object.contractIsNeed}">
                        <td>да</td>
                    </c:when><c:otherwise>
                        <td>нет</td>
                    </c:otherwise></c:choose>
                    <c:choose><c:when test="${object.contractExists}">
                        <td>да</td>
                    </c:when><c:otherwise>
                        <td>нет</td>
                    </c:otherwise></c:choose>
                    <td>${object.plannedStartDate}</td>
                    <td>${object.actualStartDate}</td>
                    <td>${object.plannedEndDate}</td>
                    <td>${object.actualEndDate}</td>
                    <td>${object.sum}</td>
                    <td>${object.expectedPayment}</td>
                    <td>${object.paymentOrder}</td>
                    <td>${object.orderType.title}</td>
                    <td>${object.numberOfLines}</td>
                    <td>${object.group.title}</td>
                    <td>${object.manager.fullName}</td>
                    <c:choose><c:when test="${object.underway}">
                        <td>да</td>
                    </c:when><c:otherwise>
                        <td>нет</td>
                    </c:otherwise></c:choose>
                    <td><a href="/enclave/finance/object/${object.id}">[Перейти]</a></td>
                    <td><a href="/enclave/time/planned/object/${object.id}">[Перейти]</a></td>
                    <td><a href="/enclave/time/actual/object/${object.id}">[Перейти]</a></td>
                    <td><a href="/enclave/tasks/object/${object.id}">[Перейти]</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    ‌
</div>
</body>
</html>