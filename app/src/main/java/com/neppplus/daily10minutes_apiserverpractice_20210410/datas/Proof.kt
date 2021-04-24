package com.neppplus.daily10minutes_apiserverpractice_20210410.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Proof {

//    멤버변수들만 추가. 생성자 커스터마이징 X

    var id = 0 // Int가 들어올 예정.
    var content = "" // String이 들어올 예정.

//    이미지 경로( URL) 들을 저장할 ArrayList
    val imageUrls = ArrayList<String>()

//    이 글을 누가 썼는지? 사용자 정보를 통째로 변수로.
    lateinit var writer : User

//    인증 글 작성 일시 저장 변수.
    val proofDateTime = Calendar.getInstance()

    companion object {

//        JSON 한덩어리 -> Proof로 변환 기능.

        fun getProofFromJson(jsonObj : JSONObject) : Proof {

            val proof = Proof()

//            JSON 항목들 -> proof 변수들에 대입.
            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

//            imageUrls에 사진 경로들을 추가.

            val imagesArr = jsonObj.getJSONArray("images")

            for (i   in 0 until imagesArr.length()) {

//                {  } -> "img_url" String추출 -> proof의 imageUrls에 추가.

                proof.imageUrls.add(  imagesArr.getJSONObject(i).getString("img_url")  )

            }

//            이 글의 작성자 정보도 파싱.
            proof.writer = User.getUserFromJson( jsonObj.getJSONObject("user") )

//            이 글이 작성된 일시도 파싱. (서버가 주는 데이터 : String => Calendar 저장.)

//            String  파싱 => Calendar 로 변환
            val proofTimeStr = jsonObj.getString("proof_time")

//            서버가 내려주는 문장을 분석하기 위한 양식
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

//            만들어진 일/시 (Date) 를 => Calendar의 일/시로 반영.

            proof.proofDateTime.time = sdf.parse(proofTimeStr)

            return proof

        }

    }

}