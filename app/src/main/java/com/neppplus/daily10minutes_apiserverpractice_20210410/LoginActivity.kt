package com.neppplus.daily10minutes_apiserverpractice_20210410

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }

        loginBtn.setOnClickListener {

//            입력한 이메일/비번 추출

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()

//            서버에 로그인 요청 (배울 내용)

            ServerUtil.postRequestLogin(inputEmail, inputPassword, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val codeNum = jsonObj.getInt("code")

                    if (codeNum == 200) {
//                        로그인 성공한 경우
//                        로그인 한 사람의 닉네임 + 님 환영합니다. 토스트
//                        메인화면으로 이동

                        val dataObj = jsonObj.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")

                        val nickname = userObj.getString("nick_name")

                        runOnUiThread {

                            Toast.makeText(mContext, "${nickname}님 환영합니다.", Toast.LENGTH_SHORT).show()

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)

                            finish()

                        }

                    }
                    else {
//                        로그인 실패. => 토스트 띄워보자.
//                        서버가 알려주는 실패 사유를 받아서 => 그 내용을 토스트로.

                        val message = jsonObj.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }


                    }

                }

            })

        }

    }

    override fun setValues() {

    }


}