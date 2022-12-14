package com.example.winneat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.winneat.APIS.APISLogin
import com.example.winneat.APIS.APISStadium
import com.example.winneat.PostData.PostLogin
import com.example.winneat.PostData.PostStadium
import com.example.winneat.PostData.PostStore
import com.example.winneat.PostData.stadiumResult
import com.example.winneat.databinding.ActivityStadiumBinding
import com.example.winneat.databinding.ActivityStadiumSelectBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StadiumSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStadiumSelectBinding
    private val api by lazy { APISStadium.create() }
    lateinit var recyclerView: RecyclerView
    var list: ArrayList<PostStadium>? = null
    val thisActivity = this
    var userId = ""
    var userPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //setContentView(R.layout.activity_stadium_select)

        binding = ActivityStadiumSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val stadiumexam = "광주기아챔피언스필드"

        if(intent.hasExtra("userId")&&intent.hasExtra("userPassword")){
            userId = intent.getStringExtra("userId").toString()
            userPassword = intent.getStringExtra("userPassword").toString()
            Log.d("log", "메인에서 받아온 id : $userId, pw : $userPassword")
        }

        api.postStadiumInfo(stadiumexam).enqueue(object :Callback<stadiumResult>{
            override fun onResponse(call: Call<stadiumResult>, response: Response<stadiumResult>) {
                // PostLogin 클래스 형식으로 php에 데이터 Post, php가 응답한 데이터 또한 PostLogin 클레스 형식으로 받아옴
//                    Log.d("log",response.toString())
//                    Log.d("log", response.body().toString())
                Log.d("log", "성공 : ${response.body()}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐
                val body = response.body()

                list= response.body()?.stadiumList as ArrayList<PostStadium>
                Log.d("list : ", "성공 : ${list}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐

                recyclerView = binding.stadiumRecycle
                recyclerView.adapter = ResultRecyclerViewAdpater()
                recyclerView.layoutManager = LinearLayoutManager(thisActivity.applicationContext)


            }
            override fun onFailure(call: Call<stadiumResult>, t: Throwable) {
                Log.d("log","${t.localizedMessage}")

            }
        })

        binding.arrowBack.setOnClickListener {
            val intent = Intent(thisActivity,StadiumActivity::class.java)
            intent.putExtra("userId",userId)
            intent.putExtra("userPassword",userPassword)

            startActivity(intent) // 경기장 액티비티로 이동
        }

    }

    inner class ResultRecyclerViewAdpater() :
        RecyclerView.Adapter<ResultRecyclerViewAdpater.ResultViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ResultRecyclerViewAdpater.ResultViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.stadium_info, parent, false)
            return ResultViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list!!.size
        }

        override fun onBindViewHolder(
            holder: ResultRecyclerViewAdpater.ResultViewHolder,
            position: Int
        ) {
            holder.apply {
                //stadiumTextView.text = list?.get(position)?.stadiumName
                //Log.d("list : ", "성공 : ${list}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐
                stadiumTextView.text = list?.get(position)?.stadiumName
                stadiumLayout.setOnClickListener {
                    Log.d("뭐 선택했는지",list?.get(position)?.stadiumName.toString())
                    val intent = Intent(thisActivity,MainActivity::class.java)
                    intent.putExtra("userId",userId)
                    intent.putExtra("userPassword",userPassword)
                    intent.putExtra("stadiumName",list?.get(position)?.stadiumName.toString())

                    startActivity(intent) // 경기장 액티비티로 이동
                }

            }
        }

        inner class ResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            //위의 onCreateViewHolder에서 생성된 view를 가지고 실행함
            val stadiumTextView : TextView = view.findViewById(R.id.stadium_text)
            val stadiumLayout : LinearLayout = view.findViewById(R.id.stadiumLayout)
        }
    }
}