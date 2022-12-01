package com.example.winneat

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface APISRegister{
    //post -> php 파일 주소
    @FormUrlEncoded
    @POST(MyApp.Register_url)
    @Headers(
        "accept: application/json",
        "content-type: application/x-www-form-urlencoded; charset=utf-8"
    )

    // Post로 서버에 데이터를 보내는 메서드 - 회원가입
    fun postUsersInfo(
        @Field("userId") userId: String,
        @Field("userPassword") userPassword: String,
        @Field("userName") userName: String,
        @Field("phoneNum") phoneNum: String
    ): Call<PostRegister>

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        //서버 IP만 입력해주세요~
        private const val BASE_URL = "http://winandeat.dothome.co.kr/"
        fun create(): APISRegister {
            val gson: Gson = GsonBuilder().setLenient().create();
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APISRegister::class.java)
        }
    }
}