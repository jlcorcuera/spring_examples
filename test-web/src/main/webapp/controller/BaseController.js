sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent",
     "sap/ui/model/resource/ResourceModel"
], function (Controller, UIComponent, ResourceModel) {
    "use strict";

    return Controller.extend("pe.idc.web.test.springmvc.controller.BaseController", {

        customInit: function() {
            var i18nModel = new sap.ui.model.resource.ResourceModel({
                bundleName: "pe.idc.web.test.springmvc.i18n.i18n"
            });
            this.setModel(i18nModel, "i18n");
        },
        /**
         * Convenience method for accessing the router.
         * @public
         * @returns {sap.ui.core.routing.Router} the router for this component
         */
        getRouter: function () {
            return UIComponent.getRouterFor(this);
        },

        /**
         * Convenience method for getting the view model by name.
         * @public
         * @param {string} [sName] the model name
         * @returns {sap.ui.model.Model} the model instance
         */
        getModel: function (sName) {
            return this.getView().getModel(sName);
        },

        /**
         * Convenience method for setting the view model.
         * @public
         * @param {sap.ui.model.Model} oModel the model instance
         * @param {string} sName the model name
         * @returns {sap.ui.mvc.View} the view instance
         */
        setModel: function (oModel, sName) {
            return this.getView().setModel(oModel, sName);
        }
    });

});