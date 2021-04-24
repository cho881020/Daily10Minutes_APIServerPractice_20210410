package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()


//    커스텀 액션바를 적용해주는 기능 (함수)

    fun setCustomActionBar() {

//        1. 액션바가 어떤 모양으로 보이게 하고싶은지?
//         => 모양 : layout - xml 작성하자.

    }

}