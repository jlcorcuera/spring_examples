sap.ui.define([
    "sap/ui/base/Object",
], function (Object) {
    "use strict";
    return Object.extend("pe.idc.web.test.springmvc.controller.dialog.TestDialog", {
        currentView: null,
        _getDialog: function () {
            // create dialog lazily
            if (!this._oDialog) {
                // create dialog via fragment factory
                this._oDialog = sap.ui.xmlfragment("testDialog", "pe.idc.web.test.springmvc.view.fragment.Dialog", this); 
            }
            return this._oDialog;
        },
        open: function (oView) {
            var oDialog = this._getDialog();
            this.currentView = oView;
            // connect dialog to view (models, lifecycle)
            oView.addDependent(oDialog);
            // open dialog
            oDialog.open();
        },
        onCloseDialog: function () {
            this._getDialog().close();
        },
        onAcceptDialog: function () {
            this._getDialog().close();
        }
    });
});