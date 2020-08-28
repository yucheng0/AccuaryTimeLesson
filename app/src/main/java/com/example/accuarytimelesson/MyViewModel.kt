package com.example.accuarytimelesson

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {
    var diff = MutableLiveData<Long>()
    var now =  MutableLiveData<Long>()
    var next = MutableLiveData<Long>()
    var isStartEnabled = MutableLiveData<Boolean>()

    init {
        diff.value = 0L
        isStartEnabled.value = false
        now.value = SystemClock.uptimeMillis()
    }

    fun initcoroutines(now: LiveData<Long>) {
            viewModelScope.launch(Dispatchers.Main) {
      // 先執行再delay
                next.value = SystemClock.uptimeMillis()  //取系統時間
                var x1:Long = now.value.toString().toLong()
                var x = next.value?.minus(x1)
                var y = x.toString().toLong() /1000
                diff.value = y
                delay500ms()
         }
    }
    suspend fun delay500ms() {          //耗時操作delay
        delay(500)

    }

}











