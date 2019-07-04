package com.iamwee.android.simpleloadingdialog.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iamwee.android.simpleloadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val delayHandler = Handler(Looper.getMainLooper(), Handler.Callback {
        LoadingDialog.dismiss()
        true
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShowDialog.setOnClickListener {
            LoadingDialog.show(this) {
                delayHandler.removeMessages(12)
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            delayHandler.sendEmptyMessageDelayed(12, 2000)
        }

        buttonShowDialog2.setOnClickListener {
            LoadingDialog.show(this)
        }
    }
}