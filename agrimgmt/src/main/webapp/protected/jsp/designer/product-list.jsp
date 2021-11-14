<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Product list</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <!--For font awesome-->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>
	</head>

  <body>
  <div id="container">

    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/sidebar.jsp"/>
      <div id="content">
          <h1>Product List</h1>

          <c:import url="/jsp/include/show-message.jsp"/>

          <c:if test="${!message.isError}">

          <div class="table-responsive">
              <table class="table">
                  <thead>
                  <tr>
                      <th>Id</th><th>Name</th><th>Price</th><th>Available</th><th>Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${products}" var="prod">
                      <tr>
                          <td><c:out value="${prod.productId}"/></td>
                          <td><c:out value="${prod.productName}"/></td>
                          <td><c:out value="${prod.price}"/></td>
                          <td><c:out value="${prod.available}"/></td>
                          <td>
                              <div class="button-container">
                              <form action="<c:url value="/protected/designer/change-product-status"/>" method="POST">
                                  <input type="hidden" value="${prod.productId}" name="productId">
                                  <input type="hidden" value="true" name="available">
                                  <button type="submit"><i class="far fa-check-circle fa-lg available"></i></button>
                              </form>
                              <form action="<c:url value="/protected/designer/change-product-status"/>" method="POST">
                                  <input type="hidden" value="${prod.productId}" name="productId">
                                  <input type="hidden" value="false" name="available">
                                  <button type="submit"><i class="far fa-times-circle fa-lg unavailable"></i></button>
                              </form>
                              </div>
                          </td>
                      </tr>
                  </c:forEach>
                  </tbody>
              </table>
          </div>
          </c:if>

          <br>

          <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
      </div>
      <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
  </div>
  <c:import url="/html/footer.html"/>
  <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
  </body>
</html>