package com.iamwee.android.simpleloadingdialog

import android.content.Context

object LoadingDialog {

    private var progressDialog: ProgressDialog? = null

    @JvmStatic
    fun show(context: Context) {
        progressDialog = progressDialog ?: ProgressDialog(context)
        progressDialog?.show()
    }

    @JvmStatic
    fun show(context: Context, onCancelledDialogListener: () -> Unit) {
        progressDialog = progressDialog ?: ProgressDialog(context, onCancelledDialogListener)
        progressDialog?.show()
    }

    @JvmStatic
    fun show(context: Context, onCancelledDialogListener: OnCancelledDialogListener) {
        progressDialog = progressDialog ?: ProgressDialog(context) {
            onCancelledDialogListener.onCancelled()
        }
        progressDialog?.show()
    }

    @JvmStatic
    fun dismiss() {
        progressDialog?.dismiss()
        progressDialog = null
    }


    interface OnCancelledDialogListener {
        fun onCancelled()
    }

}