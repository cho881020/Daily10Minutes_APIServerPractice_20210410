package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        emailCheckBtn.setOnClickListener {

//            입력한 이메일을 받아서
            val inputEmail = emailEdt.text.toString()

//            서버에 중복인지 아닌지 물어보는 기능 API 활용. => 그 결과를 텍스트뷰에 반영 (UI 반영)

            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {

                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    runOnUiThread {
                        if (code == 200) {
                            emailCheckResultTxt.text = "사용해도 좋은 이메일입니다."
                        }
                        else {
                            emailCheckResultTxt.text = "중복된 이메일이라 사용할 수 없습니다."
                        }
                    }



                }

            })

        }

        signUpBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

            ServerUtil.putRequestSignUp(inputEmail, inputPw, inputNickname, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    if (code == 200) {

                        runOnUiThread {

                            Toast.makeText(mContext, "환영합니다.", Toast.LENGTH_SHORT).show()
                            finish()

                        }

                    }
                    else {
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