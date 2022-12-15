package com.example.winneat.APIS

import com.example.winneat.MyApp
import com.example.winneat.PostData.PostLogin
import com.example.winneat.PostData.PostRegister
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface APISSeat {
    //post -> php 파일 주소
    @FormUrlEncoded
    @POST(MyApp.SetSeat_url)
    @Headers(
        "accept: application/json",
        "content-type: application/x-www-form-urlencoded; charset=utf-8"
    )

    // Post로 서버에 데이터를 보내는 메서드 - 로그인
    fun postSeat(
        // 서버에 Post 방식으로 보낼 떄 사용하는  파라미터의 키 값
        //ex)@Field('키') =>  $_POST['키']
        @Field("userId") userId: String,
        @Field("seatNum") seatNum: String
    ): Call<PostRegister>

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        //서버 IP만 입력해주세요~
        private const val BASE_URL = "http://winandeat.dothome.co.kr/"
        fun create(): APISSeat {
            val gson: Gson = GsonBuilder().setLenient().create();
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APISSeat::class.java)
        }
    }
}