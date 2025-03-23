<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    <title>K-PAC SETs</title>
</head>
<body>
<h2>List of K-PAC SETs</h2>

<form id="kpacSetForm" action="${pageContext.request.contextPath}/sets/add" method="POST">
    <label for="title">Title: </label>
    <input type="text" id="title" name="title" pattern="[A-Za-z0-9 ]+" required><br>
    <fieldset id="kpacList">
        <legend>Choose available packs :</legend>
        <c:forEach var="kpac" items="${kpacListJson}">
            <c:if test="${not empty kpac.id}">
                <div>
                    <input type="checkbox" id="pack_${kpac.id}" name="kpac"/>
                    <label for="pack_${kpac.id}">${kpac.title}</label>
                </div>
            </c:if>
        </c:forEach>
    </fieldset>
    <input type="submit" value="Add K-PAC SET">
</form>

<div id="kpacSetGrid"></div>

<script>
    const KPAC_SET_DATA = ${kpacSetListJson};
</script>

<script src="${pageContext.request.contextPath}/static/js/kpacSetPage.js" type="text/javascript"></script>

</body>
</html>
