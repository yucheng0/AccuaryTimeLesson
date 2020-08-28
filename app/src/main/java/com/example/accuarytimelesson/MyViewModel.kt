package com.example.accuarytimelesson

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {
  var diff = MutableLiveData<Long>()   //數據監聽是要的
  //  var now =  MutableLiveData<Long>()   //這個很重要一開始的值不能變
  //  var next = MutableLiveData<Long>()
    var isStartEnabled = false
    var now:Long = SystemClock.uptimeMillis()

    init {
      diff.value = 0L
  //      isStartEnabled.value = false
        //now.value = SystemClock.uptimeMillis()
  //      var now = SystemClock.uptimeMillis()
    }

    fun initcoroutines(now:Long) {
            viewModelScope.launch(Dispatchers.Main) {
      // 先執行再delay
                val next= SystemClock.uptimeMillis()  //取系統時間
                var x1:Long = now.toString().toLong()
                var x = next.minus(x1)
                var y = x.toString().toLong() /1000
                diff.value = y
                delay500ms()
         }
    }
    suspend fun delay500ms() {          //耗時操作delay
        delay(500)

    }

}











