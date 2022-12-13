package com.example.winneat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.winneat.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val api by lazy { APISStore.create() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("userId")&&intent.hasExtra("userPassword")){
            val userId = intent.getStringExtra("userId")
            val userPassword = intent.getStringExtra("userPassword")
            Log.d("log", "메인에서 받아온 id : $userId, pw : $userPassword")
        }

        val postStadium="광주기아챔피언스필드"

        api.postStadium(postStadium).enqueue(object : Callback<storeResult> {
            override fun onResponse(call: Call<storeResult>, response: Response<storeResult>) {
                // PostLogin 클래스 형식으로 php에 데이터 Post, php가 응답한 데이터 또한 PostLogin 클레스 형식으로 받아옴
//                    Log.d("log",response.toString())
//                    Log.d("log", response.body().toString())
                Log.d("log", "성공 : ${response.body()}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐
                val body = response.body()
                val len = response.body()?.storeList?.size
                Log.d("log", "len : ${len}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐

                //val storeName= response.body()?.storeList?.get(1)?.storeName
                for(i in 0 until len!!){
                    Log.d("log", "${i}번째 stadiumName : ${response.body()!!.storeList[i].stadiumName}")
                    Log.d("log", "${i}번째 storeLoc : ${response.body()!!.storeList[i].storeLoc}")
                    Log.d("log", "${i}번째 storeName : ${response.body()!!.storeList[i].storeName}")
                    Log.d("log", "${i}번째 phoneNum : ${response.body()!!.storeList[i].phoneNum}")
                }

            }
            override fun onFailure(call: Call<storeResult>, t: Throwable) {
                Log.d("log","${t.localizedMessage}")

            }
        })

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}