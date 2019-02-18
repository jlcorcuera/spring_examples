<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring MVC Test</title>
    </head>
    <body>
        <%@include file="common/language.jsp" %>
        <br/>        
        <div align="center">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h2><spring:message code='welcome.label'/> USER: ${pageContext.request.userPrincipal.name} | 
                    <a href="<c:url value="/logout" />" > Logout</a></h2>  
                </c:if>				
        </div>
    </body>
</html>