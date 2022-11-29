package com.example.winneat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.winneat.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api_1 by lazy { APISRegister.create() }
        val api_2 by lazy { APISValidate.create() }
        // setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnValidate.setOnClickListener{
            var userId = binding.enterId.text.toString()
            api_2.postValidateInfo(userId).enqueue(object : Callback<PostRegister> {
                override fun onResponse(call: Call<PostRegister>, response: Response<PostRegister>) {
                    // PostLogin 클래스 형식으로 php에 데이터 Post, php가 응답한 데이터 또한 PostLogin 클래스 형식으로 받아옴
//                    Log.d("log",response.toString())
//                    Log.d("log", response.body().toString())
                    //Log.d("log", "성공 : ${response.body()}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐
                    val body = response.body()
                    val status = response.body()?.status
                    Log.d("log", "$userId 아이디 생성 가능 여부 : $status")
                    if(status.equals("true")){
                        ToastObj.createToast(applicationContext, "아이디 생성 가능")?.show() // 토스트 메시지 띄움
                    }else{
                        ToastObj.createToast(applicationContext, "아이디가 이미 존재합니다.")?.show() // 토스트 메시지 띄움
                    }
                }
                override fun onFailure(call: Call<PostRegister>, t: Throwable) {
                    Log.d("log","${t.localizedMessage}")
                    ToastObj.createToast(applicationContext, "회원가입 오류")?.show() // 토스트 메시지 띄움
                }
            })
        }

        binding.btnRegister.setOnClickListener {
            //var userId:String? = binding.enterId.text.toString() // null 허용
            var userId = binding.enterId.text.toString()
            var userPassword = binding.enterPw.text.toString()
            var re_userPassword = binding.reenterPw.text.toString()
            var userName = binding.enterName.text.toString()

            if (userId.equals("") || userPassword.equals("") || re_userPassword.equals("") || userName.equals("")){
                // 하나라도 적지 않은 정보가 있다면
                val context = this
                ToastObj.createToast(context, "정보를 모두 입력하세요.")?.show() // 토스트 메시지 띄움
            }
            else if(userPassword != re_userPassword){
                // 비밀번호과 비밀번호 확인이 일치하지 않는다면
                val context = this
                ToastObj.createToast(context, "비밀번호가 일치하지 않습니다.")?.show() // 토스트 메시지 띄움
                binding.enterPw.setText(null)
                binding.reenterPw.setText(null)

            }else{ // 정보 모두 입력하고, 비밀번호가 일치 했을 때
                api_1.postUsersInfo(userId,userPassword,userName).enqueue(object : Callback<PostRegister> {
                    override fun onResponse(call: Call<PostRegister>, response: Response<PostRegister>) {
                        // PostLogin 클래스 형식으로 php에 데이터 Post, php가 응답한 데이터 또한 PostLogin 클래스 형식으로 받아옴
//                    Log.d("log",response.toString())
//                    Log.d("log", response.body().toString())
                        Log.d("log", "성공 : ${response.body()}") // php가 보내온 아이디, 비밀번호가 로그캣에 띄워짐
                        val body = response.body()
                        val status = response.body()?.status
                        Log.d("log", "성공 : $status")
                    }

                    override fun onFailure(call: Call<PostRegister>, t: Throwable) {
                        Log.d("log","${t.localizedMessage}")
                        ToastObj.createToast(applicationContext, "회원가입 오류")?.show() // 토스트 메시지 띄움
                    }
                })
            }
        }

    }
}