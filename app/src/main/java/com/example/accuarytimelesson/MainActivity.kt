package com.example.accuarytimelesson

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    //    if (myViewModel.isStartEnabled.value ==false ){
       //     myViewModel.now.value = SystemClock.uptimeMillis()}

        //監聽LiveData
        myViewModel.diff.observe(this, {
            println("myViewModel.isStartEnabled= ${myViewModel.isStartEnabled.value}")
            if (myViewModel.isStartEnabled.value == true) {
                myViewModel.initcoroutines(myViewModel.now)
                var minutes = (myViewModel.diff.value)?.div(60).toString()
                var second = (myViewModel.diff.value)?.rem(60).toString()
                if (minutes.toInt()<= 9) {
                    minutes = "0"+minutes
                }
                if (second.toInt()<= 9) {
                    second = "0"+second
                }
                var time1 = "${minutes}:${second}"
                timeView.text = time1
            }

            butStart.setOnClickListener {
                if (myViewModel.isStartEnabled.value == false) {
                    myViewModel.isStartEnabled.value = true
                    myViewModel.now.value = SystemClock.uptimeMillis()
                    myViewModel.diff.value = myViewModel.diff.value
                }
            }
        })   //  監聽 end

    }
}
