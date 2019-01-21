sap.ui.define([
    "sap/ui/core/UIComponent",
    "pe/idc/web/test/springmvc/controller/dialog/TestDialog"
], function (UIComponent, TestDialog) {
    "use strict";
    return UIComponent.extend("pe.idc.web.test.springmvc.Component", {
        metadata: {
            manifest: "json"
        },
        init: function () {
            // call the init function of the parent
            UIComponent.prototype.init.apply(this, arguments);
            // create the views based on the url/hash
            this.getRouter().initialize();
            this.testDialog = new TestDialog();
        }
    });
});