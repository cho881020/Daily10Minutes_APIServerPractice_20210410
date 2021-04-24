package com.neppplus.daily10minutes_apiserverpractice_20210410.fcm

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

//        p0 에 우리가 수신한 메세지가 들어있다. => 어떻게 처리할지 코딩.

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.post {

            Toast.makeText(applicationContext, p0.notification!!.title, Toast.LENGTH_SHORT).show()

        }

    }

}