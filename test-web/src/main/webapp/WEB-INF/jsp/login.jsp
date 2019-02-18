<%@page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function reloadKaptcha() {
                document.getElementById("imgKaptcha").src = "<c:url value='/kaptcha.jpg'/>";
            }
        </script>            
    </head>    
    <body>
        <%@include file="common/language.jsp" %>
        <br/>
        <form id="loginForm" method="post" action="<c:url value='/login'/>">
            <h4><spring:message code="startSession.label"/></h4>
            <c:if test="${param.error}">
                <div>
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </div>
            </c:if>            
            <div>
                <input type="text" id="txtUsuario" name="username" placeholder="<spring:message code='userName.label'/>" required>
            </div>
            <div>
                <input type="password" id="txtContrasenia" name="password" placeholder="<spring:message code='password.label'/>" required>
            </div>
            <div>
                <div>
                    <img id="imgKaptcha" src="<c:url value='/kaptcha.jpg'/>"/>
                </div>
                <div>
                    <div>
                        <input id="txtCodigo" type="kaptcha" name="kaptcha" placeholder="<spring:message code='typeCode.label'/>" required>
                    </div>
                </div>
                <div>
                    <button onclick="reloadKaptcha();" type="button"><spring:message code='reloadImage.label'/></button>
                </div>
            </div>
            <div>
                <button type="submit"><spring:message code='login.label'/></button>
            </div>
        </form>    
    </body>
</html>