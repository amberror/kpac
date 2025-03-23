<!-- Display Global Errors -->
<c:if test="${not empty validationErrors}">
  <div class="validation-error-container">
    <ul>
      <c:forEach var="error" items="${validationErrors}">
        <li>${error.defaultMessage}</li>
      </c:forEach>
    </ul>
  </div>
</c:if>
