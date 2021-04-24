package com.neppplus.daily10minutes_apiserverpractice_20210410.datas

import org.json.JSONObject

class Proof {

//    멤버변수들만 추가. 생성자 커스터마이징 X

    var id = 0 // Int가 들어올 예정.
    var content = "" // String이 들어올 예정.

//    이미지 경로( URL) 들을 저장할 ArrayList
    val imageUrls = ArrayList<String>()

//    이 글을 누가 썼는지? 사용자 정보를 통째로 변수로.
    lateinit var writer : User

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

            return proof

        }

    }

}