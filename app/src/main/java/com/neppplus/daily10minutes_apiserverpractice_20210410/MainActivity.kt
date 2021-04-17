package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.daily10minutes_apiserverpractice_20210410.adapters.ProjectAdapter
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjects = ArrayList<Project>()
    lateinit var mProjectAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        서버에서 => 보여줄 프로젝트 목록이 어떤것들이 있는지 받아서 => Project() 형태로 변환해서 => mProjects 에 하나하나 추가.
        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjects)
        projectListView.adapter = mProjectAdapter

    }

    fun getProjectListFromServer() {
//        실제로 서버에서 받아오는 기능 수행.

        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")

                val projectsArr = dataObj.getJSONArray("projects")

//                반복문 for문으로 => projectsArr 내부를 하나씩 꺼내서 파싱.

                for ( i  in 0 until projectsArr.length() ) {

//                    [  ] 안에 있는 => {  }들을, 하나 꺼내서 => 파싱 : Project형태로 변환. => mProjects에 추가.

                    val projectObj = projectsArr.getJSONObject(i)

//                    Project 클래스에 보조 생성자 추가. => 재료 없이도 만들 수 있게.
//                    Project 클래스에 함수(기능) 추가. => JSONObject를 넣으면 => Project 형태로 변환해주는 기능.
                    val project = Project.getProjectFromJson(projectObj)

                    mProjects.add( project )

                }

//                서버 통신이 어댑터 연결보다 먼저 실행되지만 => 실제로는 더 늦게 끝날 수도 있다. (비동기 방식)
//                데이터 추가가 => 리스트뷰의 내용 변경일 수도 있다. => notifyDataSetChanged 실행해주자.

                runOnUiThread {
                    mProjectAdapter.notifyDataSetChanged()
                }




            }

        })

    }

}