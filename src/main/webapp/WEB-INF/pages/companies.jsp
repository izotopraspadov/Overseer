<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="company" class="edu.guap.enclave.model.Company"/>
<html>
<head>
    <title>Компании</title>
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
                case "region":
                case "person":
                    $("#data").hide();
                    break;
                default:
                    $("#data").show();
            }
        });
    });
</script>

    <table class="find">
        <tr>
            <form action="/enclave/companies/token" method="POST">
                <th><p>Поиск: </p></th>
                <th>
                    <div id="data">
                        <input type="text" name="data"/>
                    </div>
                </th>
                <th>
                    <select name="category" id="category">
                        <option value="title" type="text" name="select">По названию</option>
                        <option value="type" type="text" name="select">По типу</option>
                        <option value="address" type="text" name="select">По адресу</option>
                        <option value="itn" type="text" name="select">По ИНН</option>
                        <option value="reliability" type="text" name="select">По степени надёжности</option>
                        <option value="region" type="text" name="select">По региону</option>
                        <option value="person" type="text" name="select">По контактному лицу</option>
                    </select>
                </th>
                <th>
                    <input type="submit" value="Найти"/>
                </th>
            </form>
        </tr>
    </table>


<div class="center">

    <br>

    <c:if test="${empty companies}">
        <h1 class="h1" align="center">{Пусто}</h1>
    </c:if>

    <c:if test="${!empty companies}">
        <table class="tg">
            <tr>
                <th width="80">Название</th>
                <th width="120">ИНН</th>
                <th width="120">Адрес</th>
                <th width="120">Степень надёжности</th>
                <th width="120">Тип</th>
                <th width="120">Название Группы в WhatsApp</th>
                <th width="120">Регион</th>
            </tr>
            <c:forEach items="${companies}" var="company">
                <tr>
                    <td>${company.title}</td>
                    <td>${company.itn}</td>
                    <td>${company.address}</td>
                    <td>${company.reliability.description}</td>
                    <td>${company.typeCompany.description}</td>
                    <td>${company.whatsAppGroupName}</td>
                    <td>${company.region.title}</td>
                    <c:if test="${swtch eq 'obj'}">
                        <td><a href="/enclave/objects/company/${company.id}">[Выбрать]</a></td>
                    </c:if>
                    <c:if test="${swtch eq 'emp'}">
                        <td><a href="/enclave/employees/company/${company.id}">[Выбрать]</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>