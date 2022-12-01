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
    val api by lazy { APISLogin.create() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_login)

        val thisActivity = this
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{ // 로그인 버튼을 눌렀을 때
            var userId= binding.editId.text.toString() // editId에 있는 문자열 저장
            val userPassword = binding.editPw.text.toString() // editPw에 있는 문자열 저장

            api.postUsers(userId,userPassword).enqueue(object : Callback<PostLogin>{
                override fun onResponse(call: Call<PostLogin>, response: Response<PostLogin>) {
                    // PostLogin 클래스 형식으로 php에 데이터 Post, php가 응답한 데이터 또한 PostLogin 클레스 형식으로 받아옴
//                    Log.d("log",response.toString())
//                    Log.d("log", response.body().toString())
                      Log.d("log", "성공 : ${response.body()}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐
                    val body = response.body()

                    val userId = response.body()?.userId
                    val userPassword = response.body()?.userPassword

                    binding.editId.setText(null)
                    binding.editPw.setText(null)

                    val intent = Intent(thisActivity,MainActivity::class.java)
                    intent.putExtra("userId",userId)
                    intent.putExtra("userPassword",userPassword)

                    startActivity(intent) // 메인 액티비티로 이동
                }
                override fun onFailure(call: Call<PostLogin>, t: Throwable) {
                    Log.d("log","${t.localizedMessage}")
                    ToastObj.createToast(applicationContext, "아이디와 비밀번호를 다시 입력해주세요.")?.show() // 토스트 메시지 띄움
                    binding.editId.setText(null)
                    binding.editPw.setText(null)
                }
            })
        }
        binding.textRegister.setOnClickListener { // 회원가입 텍스트를 눌렀을 때
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent) // 회원가입 액티비티로 이동
        }


    }
}