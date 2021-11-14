<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="results">
    <c:choose>
        <c:when test="${message.isError}">
            <p><b>ERROR</b></p>
            <ul class="message-list">
                <li><span class="li-title">error code:</span> <c:out value="${message.errorCode}"/></li>
                <li><span class="li-title">message:</span> <c:out value="${message.message}"/></li>
                <li><span class="li-title">details:</span> <c:out value="${message.errorDetails}"/></li>
            </ul>
        </c:when>

        <c:otherwise>
            <p><span class="li-title">Results: </span><c:out value="${message.message}"/></p>
        </c:otherwise>
    </c:choose>
</div>