package com.neppplus.daily10minutes_apiserverpractice_20210410

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ContextUtil
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

//        체크박스의 "체크여부가 변화되면"
//        SharedPreferences에 어떻게 변화되었는지 (체크 / 해제) 저장 예제.

        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->

//            ContextUtil을 이용해서, 변경된 체크값 저장.

            ContextUtil.setAutoLogin(mContext, isChecked)


        }

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

//                        서버가 내려주는 토큰값 추출
                        val token = dataObj.getString("token")

//                        SharedPreferences에 저장 : 기기에 보관. (전원이 나가도 유지)
                        ContextUtil.setLoginToken(mContext, token)

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

//        ContextUtil에 저장해둔 자동로그인 여부를 꺼내서 => 체크박스에 반영.
        autoLoginCheckBox.isChecked = ContextUtil.getAutoLogin(mContext)

    }


}