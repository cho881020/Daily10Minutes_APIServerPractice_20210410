package com.neppplus.daily10minutes_apiserverpractice_20210410.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.neppplus.daily10minutes_apiserverpractice_20210410.R
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project

class ProjectAdapter(
        val mContext : Context,
        resId : Int,
        val mList : List<Project>) : ArrayAdapter<Project>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.project_list_item, null)
        }

        val row = tempRow!!

        return row
    }

}