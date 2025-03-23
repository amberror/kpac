<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>Error</h1>
    <p>It's only after we've lost everything, that we're free to do anything. @Fight club</p>
    <c:if test="${not empty message}">
        Error : ${message}
    </c:if>
</body>
</html>
