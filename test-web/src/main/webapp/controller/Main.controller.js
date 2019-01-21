sap.ui.define([
    "pe/idc/web/test/springmvc/controller/BaseController",
    'sap/m/MessageToast',
    'sap/ui/Device'
], function (BaseController, MessageToast, Device) {
    "use strict";
    return BaseController.extend("pe.idc.web.test.springmvc.controller.Main", {

        _bExpanded: true,

        onInit: function () {

        },
        /**
         * Convenience method for accessing the router.
         * @public
         * @param {sap.ui.base.Event} oEvent The item select event
         */
        onItemSelect: function (oEvent) {
            var oItem = oEvent.getParameter('item');
            var sKey = oItem.getKey();
            this.getRouter().navTo(sKey);
        },
        onSideNavButtonPress: function () {
            var oToolPage = this.byId("app");
            var bSideExpanded = oToolPage.getSideExpanded();
            this._setToggleButtonTooltip(bSideExpanded);
            oToolPage.setSideExpanded(!oToolPage.getSideExpanded());
        },
        _setToggleButtonTooltip: function (bSideExpanded) {
            var oToggleButton = this.byId('sideNavigationToggleButton');
            if (bSideExpanded) {
                oToggleButton.setTooltip('Large Size Navigation');
            } else {
                oToggleButton.setTooltip('Small Size Navigation');
            }
        }
    });
});