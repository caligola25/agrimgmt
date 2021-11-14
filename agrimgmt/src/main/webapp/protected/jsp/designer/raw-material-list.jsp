<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Raw Material list</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	</head>

  <body>
  <div id="container">

      <c:import url="/jsp/header.jsp"/>
      <c:import url="/jsp/sidebar.jsp"/>

      <div id="content">


          <h1>Raw Material list</h1>

          <c:import url="/jsp/include/show-message.jsp"/>

          <c:if test="${!message.isError}">

              <table class="table table-responsive">
                  <thead>
                  <tr>
                      <th>Id</th><th>Name</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${materials}" var="mat">
                      <tr>
                          <td><c:out value="${mat.materialId}"/></td>
                          <td><c:out value="${mat.materialName}"/></td>
                      </tr>
                  </c:forEach>
                  </tbody>
              </table>
          </c:if>

          <br>

          <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
      </div>
      <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
  </div>
  <c:import url="/html/footer.html"/>
  <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
  </body>
</html>