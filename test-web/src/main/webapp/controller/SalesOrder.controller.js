sap.ui.define([
    "pe/idc/web/test/springmvc/controller/BaseController",
    "sap/m/MessageBox",
    "pe/idc/web/test/springmvc/model/formatter",
], function (BaseController, MessageBox, formatter) {
    "use strict";
    return BaseController.extend("pe.idc.web.test.springmvc.controller.SalesOrder", {
        formatter: formatter,
        onInit: function () {
            this.model = new sap.ui.model.json.JSONModel();
            this.model.setData({});
            //setModel method was implemented in BaseController
            this.setModel(this.model, "soModel");
            this.fetchData();
        },
        fetchData: function () {
            //perhaps we need to send some parameters
            var oParameters = {
                page: this.currentPage,
                query: this.query,
                view: 'customers'
            };
            var currentModel = this.model;
            $.post(SERVER_URL + "/pages/salesOrder/list", oParameters, function (result) {
                currentModel.getData().rows = result;
                currentModel.updateBindings();
            });
        },
        onSearch: function (oEvent) {
            var query = oEvent.getParameter("query");
            this.query = query;
            this.fetchData();
        },
        showPopup: function (oEvent) {
            var testDialog = this.getOwnerComponent().testDialog;
            testDialog.open(this.getView());
        }
    });
});