<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
    <li><a href="<c:url value="/protected/jsp/designer/add-process-form.jsp"/>">Add new process</a></li>
    <li><a href="<c:url value="/protected/jsp/designer/search-process.jsp"/>">View processes to build a product</a></li>
    <li><a href="<c:url value="/protected/designer/process-list"/>">View all processes and Modify estimated time</a></li>
    <li><a href="<c:url value="/protected/designer/product-list"/>">View all products and Change availability</a></li>
    <li><a href="<c:url value="/protected/designer/raw-material-list"/>">View all raw materials</a></li>
</ul>