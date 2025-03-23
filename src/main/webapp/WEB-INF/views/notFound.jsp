<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not Found</title>
</head>
<body>
    <h1>Not Found</h1>
    <p>People are always asking me if I know Tyler Durden. @Fight club</p>
    <c:if test="${not empty message}">
        Error : ${message}
    </c:if>
</body>
</html>
