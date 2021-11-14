<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<label for="sidebar" class="open-sidebar">&#9776;</label>
<div class="sidebar-menu">
    <input id="sidebar" class="menu-activation" type="checkbox" />
    <div class="sidebar-content">
        <label for="sidebar" class="close-sidebar"><i class="far fa-times-circle"></i></label>
        <h1>Functionalities</h1>
        <div class="menu-list">
        <c:choose>
            <c:when test='${not empty sessionScope.employee}'>
                <!-- Administrator -->
                <c:if test="${ sessionScope.employee.role == 'Administrator'}">
                    <h2>Administrator</h2>
                    <c:import url="/protected/jsp/administrator/duty.jsp"/>
                    <h2>Accountant</h2>
                    <c:import url="/protected/jsp/accountant/duty.jsp"/>
                    <h2>Designer</h2>
                    <c:import url="/protected/jsp/designer/duty.jsp"/>
                    <h2>Production Planner</h2>
                    <c:import url="/protected/jsp/prod_planner/duty.jsp"/>
                    <h2>Warehouse worker</h2>
                    <c:import url="/protected/jsp/warehouse_worker/duty.jsp"/>
                </c:if>

                <!-- Designer -->
                <c:if test="${ sessionScope.employee.role == 'Designer'}">
                    <c:import url="/protected/jsp/designer/duty.jsp"/>
                </c:if>

                <!-- Accountant -->
                <c:if test="${ sessionScope.employee.role == 'Accountant'}">
                    <c:import url="/protected/jsp/accountant/duty.jsp"/>
                </c:if>

                <!-- Warehouse worker -->
                <c:if test="${ sessionScope.employee.role == 'Warehouse worker'}">
                    <c:import url="/protected/jsp/warehouse_worker/duty.jsp"/>
                </c:if>

                <!-- Production planner -->
                <c:if test="${ sessionScope.employee.role == 'Production planner'}">
                    <c:import url="/protected/jsp/prod_planner/duty.jsp"/>
                </c:if>

                <!-- Production line worker-->
                <c:if test="${ sessionScope.employee.role == 'Production line worker'}">
                    <c:import url="/protected/jsp/lineprod_worker/duty.jsp"/>
                </c:if>
            </c:when>
        </c:choose>
    </div>
</div>
</div>