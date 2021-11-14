<%--
  Created by IntelliJ IDEA.
  User: matteocali
  Date: 03/04/21
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="m" items="${messageList}">
    <c:choose>
        <c:when test="${m.isError}">
            <br/>
            <p><b>ERROR:</b></p>
            <ul class="message-list">
                <li><span class="li-title">error code:</span> <c:out value="${m.errorCode}"/></li>
                <li><span class="li-title">message:</span> <c:out value="${m.message}"/></li>
                <li><span class="li-title">details:</span> <c:out value="${m.errorDetails}"/></li>
            </ul>
        </c:when>

        <c:otherwise>
            <!--<p><span class="li-title">Results: </span><c:out value="${m.message}"/></p>-->
        </c:otherwise>
    </c:choose>
</c:forEach>