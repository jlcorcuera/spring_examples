sap.ui.define([
    "pe/idc/web/test/springmvc/controller/BaseController",
    'sap/m/MessageToast',
    'sap/ui/Device'
], function (BaseController, MessageToast, Device) {
    "use strict";
    return BaseController.extend("pe.idc.web.test.springmvc.controller.Login", {

        onInit: function () {
            this.customInit();
        },
        actLogin: function () {
            sap.ui.require([
                "sap/ui/core/ComponentContainer"
            ], function (ComponentContainer) {
                sap.ui.component({
                    async: true,
                    name: "pe.idc.web.test.springmvc"
                }).then(function (yourComponent) {
                    new ComponentContainer({
                        component: yourComponent,
                        settings: {
                            id: "springmvc-app"
                        }
                    }).placeAt("content", "only");
                });
            });
            alert('entro');
        }
    });
});