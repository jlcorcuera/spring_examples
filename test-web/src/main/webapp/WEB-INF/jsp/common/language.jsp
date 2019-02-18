<%@page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
    function changeLanguage(){
        var languageSelect = document.getElementById('languageSelect');
        var languageSelected = languageSelect.value;
        var url = "${requestScope['javax.servlet.forward.request_uri']}?lang=" + languageSelected;
        window.location = url;
    }
</script>            
<%
    String currentLanguage = LocaleContextHolder.getLocale().getLanguage();
%>
<select id="languageSelect" onchange="changeLanguage()">
    <option value="en" <%= "en".equalsIgnoreCase(currentLanguage) ? "selected":"" %>><spring:message code="english.label"/></option>
    <option value="es" <%= "es".equalsIgnoreCase(currentLanguage) ? "selected":"" %>><spring:message code="spanish.label"/></option>
</select>