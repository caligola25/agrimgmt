<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
  <li> <a href="<c:url value="/show-catalogue"/>"> Show catalogue </a> </li>
  <li> <a href="<c:url value="/protected/jsp/warehouse_worker/update-item-status.jsp"/>">Manage status of item </a> </li>
  <li> <a href="<c:url value="/protected/warehouse_worker/show-item-stored"/>">Show list of stored items </a> </li>
  <li> <a href="<c:url value="/protected/jsp/warehouse_worker/supplying.jsp"/>">Supplying of raw materials </a> </li>
</ul>