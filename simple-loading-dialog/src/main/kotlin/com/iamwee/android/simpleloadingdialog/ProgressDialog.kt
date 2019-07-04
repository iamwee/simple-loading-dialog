package com.iamwee.android.simpleloadingdialog

import android.app.Dialog
import android.content.Context
import android.graphics.PorterDuff.Mode.*
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

internal class ProgressDialog constructor(
        context: Context,
        private val onCancelledDialogListener: (() -> Unit)? = null
) : Dialog(context, R.style.ProgressDialog) {

    @ColorRes
    var progressColor : Int = R.color.default_progress_bar_color

    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = RelativeLayout(context, null).apply {
            layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            )
        }

        val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyle)
        progressBar.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, progressColor),
            SRC_IN
        )
        progressBar.layoutParams = (container.layoutParams as RelativeLayout.LayoutParams).also {
            it.width = RelativeLayout.LayoutParams.WRAP_CONTENT
            it.height = RelativeLayout.LayoutParams.WRAP_CONTENT
            it.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        }

        container.addView(progressBar)
        setContentView(container)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onCancelledDialogListener?.invoke()
    }

}