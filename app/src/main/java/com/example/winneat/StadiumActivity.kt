package com.example.winneat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.winneat.databinding.ActivityMainBinding
import com.example.winneat.databinding.ActivityStadiumBinding

class StadiumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStadiumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_stadium)
        binding = ActivityStadiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userId = ""
        var userPassword = ""

        if(intent.hasExtra("userId")&&intent.hasExtra("userPassword")){
            userId = intent.getStringExtra("userId").toString()
            userPassword = intent.getStringExtra("userPassword").toString()
            Log.d("log", "메인에서 받아온 id : $userId, pw : $userPassword")
        }

        binding.stadiumBtn.setOnClickListener{ //스타디움 텍스트 눌렀을 때
            val intent = Intent(this, StadiumSelectActivity::class.java)
            startActivity(intent) // 경기장 선택 화면 이동
        }

    }
}