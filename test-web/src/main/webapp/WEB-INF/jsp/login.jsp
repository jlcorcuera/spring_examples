<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form id="loginForm" method="post" action="<c:url value='/login'/>">
            <h4>Inicio de sesión</h4>
            <c:if test="${param.error}">
                <div>
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </div>
            </c:if>            
            <div>
                <input type="text" id="txtUsuario" name="username" placeholder="Correo electr&oacute;nico" required>
            </div>
            <div>
                <input type="password" id="txtContrasenia" name="password" placeholder="Contrase&ntilde;a" required>
            </div>
            <div>
                <div>
                    <img id="imgKaptcha" src="<c:url value='/kaptcha.jpg'/>"/>
                </div>
                <div>
                    <div>
                        <input id="txtCodigo" type="kaptcha" name="kaptcha" placeholder="Ingrese el c&oacute;digo" required>
                    </div>
                </div>
                <div>
                    <button onclick="reloadKaptcha();" type="button">Recargar la imagen</button>
                </div>
            </div>
            <div>
                <button type="submit">Ingresar</button>
            </div>
        </form>    
    </body>
</html>