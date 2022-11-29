package com.example.winneat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.winneat.databinding.ActivityLoginBinding
import com.example.winneat.databinding.ActivityMainBinding
import com.example.winneat.databinding.ActivityRegisterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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

    }
}