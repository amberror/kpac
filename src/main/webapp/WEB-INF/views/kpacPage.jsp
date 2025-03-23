<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    <title>Manage K-PACs</title>
</head>
<body>
<h2>List of K-PACs</h2>

<%@ include file="/WEB-INF/views/includes/validationErrorMessages.jsp" %>

<form id="kpacForm" action="${pageContext.request.contextPath}/kpacs/add" method="POST">
    <label for="title">Title: </label>
    <input type="text" id="title" name="title" pattern="[A-Za-z0-9 ]+" required><br>
    <label for="description">Description: </label>
    <input type="text" id="description" name="description" pattern="[A-Za-z0-9 ]+" required><br>
    <input type="submit" value="Add K-PAC">
</form>

<%--For deleting via form example purpose--%>
<form id="deleteForm" action="${pageContext.request.contextPath}/kpacs/delete" method="POST" style="display: none;">
    <input type="hidden" name="id" id="kpacId">
</form>

<div id="kpacGrid"></div>

<script>
    const KPAC_DATA = ${kpacListJson};
</script>

<script src="${pageContext.request.contextPath}/static/js/kpacPage.js" type="text/javascript"></script>

</body>
</html>