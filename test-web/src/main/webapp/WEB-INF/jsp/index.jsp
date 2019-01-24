<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">                
        <title>Spring MVC Test</title>
        <link rel="stylesheet" type="text/css" href="${serverUrl}/css/style.css">
        <script
            id="sap-ui-bootstrap"
            src="/sapui/resources/sap-ui-core.js"
            data-sap-ui-theme="sap_bluecrystal"
            data-sap-ui-libs="sap.m, sap.tnt, sap.ui.commons"
            data-sap-ui-compatVersion="edge"
            data-sap-ui-preload="async"
            data-sap-ui-language="es"
            data-sap-ui-xx-bindingSyntax="complex"            
            data-sap-ui-resourceroots='{
            "pe.idc.web.test.springmvc": "${serverUrl}/",
            "webapp": "${serverUrl}/"
            }'
            >
        </script>
        <script type="text/javascript">
            //<![CDATA[
            //path of the server
            var SERVER_URL = "${serverUrl}";
            sap.ui.getCore().attachInit(function () {
                sap.ui.getCore().getConfiguration().setLanguage("es");
                sap.ui.xmlview("loginView", "pe.idc.web.test.springmvc.view.Login").placeAt("content");
            });
            //]]>
        </script>         
    </head>
    <body class="sapUiBody" id="content">
    </body>

</html>