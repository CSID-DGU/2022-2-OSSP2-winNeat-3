package com.example.winneat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.winneat.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val api by lazy { APIS.create() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{ // 로그인 버튼을 눌렀을 때
            var userId= binding.editId.text.toString()
            val userPassword = binding.editPw.text.toString()

            api.post_users(userId,userPassword).enqueue(object : Callback<PostModel>{
                override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())

                    if (!response.body().toString().isEmpty())
                        Log.d("log", response.body().toString())
                }

                override fun onFailure(call: Call<PostModel>, t: Throwable) {
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }
            })

        }
        binding.textRegister.setOnClickListener { // 회원가입 텍스트를 눌렀을 때
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent) // 회원가입 액티비티로 이동
        }


    }
}