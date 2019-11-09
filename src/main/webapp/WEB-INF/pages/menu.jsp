<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div style="float: left;">
    <a href="/enclave/employees/${100024}">Личная информация</a>
    <br>
    <a href="/enclave/companies/switch/${'cmp'}">Компании</a>
    <br>
    <a href="/enclave/employees/switch/${'cmp'}">Сотрудники</a>
    <br>
    <a href="/enclave/objects">Объекты</a>
    <br>
    <a href="/enclave/finance">Финансы</a>
    <br>
    <a href="/enclave/finance">Выход</a>
</div>
