sap.ui.define([
    "pe/idc/web/test/springmvc/controller/BaseController",
    'sap/m/MessageToast',
    'sap/ui/Device'
], function (BaseController, MessageToast, Device) {
    "use strict";
    return BaseController.extend("pe.idc.web.test.springmvc.controller.Login", {

        onInit: function () {
            this.customInit();
            var forgotLink = this.byId("forgotLink");
            forgotLink.setHref(SERVER_URL + "/pages/public/registration");
        },
        actLogin: function () {
            var homePage = SERVER_URL + "/pages/home";
            window.location.replace(homePage);
        }
    });
});