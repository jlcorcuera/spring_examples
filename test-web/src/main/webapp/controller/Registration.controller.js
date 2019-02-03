sap.ui.define([
    'sap/m/MessageBox',
    "pe/idc/web/test/springmvc/controller/BaseController",
    'sap/m/MessageToast',
    'sap/ui/Device'
], function (MessageBox, BaseController, MessageToast, Device) {
    "use strict";
    return BaseController.extend("pe.idc.web.test.springmvc.controller.Registration", {

        onInit: function () {
            this.customInit();
            this.model = new sap.ui.model.json.JSONModel();
            //initializaing the model with an empty json object
            var data = {};
            data.userType = 1;
            //setting the data to the model
            this.model.setData(data);

            //setting the model to the window
            this.setModel(this.model);
        },
        updateWindow: function (evt) {
            var data = this.model.getData();
            var userType = data.userType;
            var externalInfoHBox = this.byId("externalInfoHBox");
            if (userType == 2) {
                externalInfoHBox.setVisible(true);
            } else {
                externalInfoHBox.setVisible(false);
            }
        },
        businessSearch: function (evt){
            var data = this.model.getData();
            data.businessName = "ID Consulting S.A.C.";
            //to refresh the changes in the UI
            this.model.updateBindings();
        },
        register: function (evt) {
            //this variable have all the attributes
            var data = this.model.getData();
            MessageBox.information(JSON.stringify(data));
        },
        cancel: function (evt) {
            var loginPage = SERVER_URL;
            window.location.replace(loginPage);
        }
    });
});