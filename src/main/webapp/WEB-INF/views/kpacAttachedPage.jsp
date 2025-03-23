<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="/WEB-INF/views/includes/header.jsp" %>
  <title>K-PACs Attached</title>
</head>
<body>
<h2>K-PAC attached to SET with id ${kpacSetId}</h2>

<div id="kpacAttachedGrid"></div>

<script>
  const KPAC_ATTACHED_DATA = ${kpacListJson};
</script>

<script src="${pageContext.request.contextPath}/static/js/kpacAttached.js" type="text/javascript"></script>

</body>
</html>
