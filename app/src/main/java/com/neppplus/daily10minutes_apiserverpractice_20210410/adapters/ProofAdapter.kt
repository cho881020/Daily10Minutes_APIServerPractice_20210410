package com.neppplus.daily10minutes_apiserverpractice_20210410.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.daily10minutes_apiserverpractice_20210410.R
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Proof

class ProofAdapter(
        val mContext : Context,
        resId : Int,
        val mList : List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val proofData = mList[position]

        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val proofTimeTxt = row.findViewById<TextView>(R.id.proofTimeTxt)
        val proofContentTxt = row.findViewById<TextView>(R.id.proofContentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)


        proofContentTxt.text = proofData.content


//        인증글의 사진이 한개도 없다면 => 이미지뷰 숨김.
//        하나라도 있다면 => 맨 앞 (0번칸) 의 이미지를 반영.

        if (proofData.imageUrls.size == 0) {
            proofImg.visibility = View.GONE
        }
        else {
            proofImg.visibility = View.VISIBLE

//            0번칸 이미지 반영.
            Glide.with(mContext).load(proofData.imageUrls[0]).into(proofImg)

        }

//        인증글에 달린 -> 작성자 정보를 받아서 -> UI 에 반영.
        Glide.with(mContext).load(proofData.writer.profileImgUrls[0]).into(writerProfileImg)
        writerNicknameTxt.text = proofData.writer.nickName

        return row
    }

}