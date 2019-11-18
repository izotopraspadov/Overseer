<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div style="float: left;">
    <a href="/employees">Личная информация</a>
    <br>
    <a href="/companies/switch/${'cmp'}">Компании</a>
    <br>
    <a href="/employees/switch/${'cmp'}">Сотрудники</a>
    <br>
    <a href="/objects">Объекты</a>
    <br>
    <a href="/finance">Финансы</a>
    <br>
    <a href="/perform_logout">Выход</a>
</div>
