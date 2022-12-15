package com.example.winneat

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.winneat.PostData.PostMenu
import com.example.winneat.PostData.PostRegister
import com.example.winneat.APIS.APISSeat
import com.example.winneat.databinding.ActivityOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    val api by lazy { APISSeat.create() }
    lateinit var recyclerView: RecyclerView

    var userId = ""
    var userPassword = ""
    var stadiumName=""
    var storeName=""
    var argList = arrayListOf<PostMenu>()
    var orderData : ArrayList<Order> = arrayListOf<Order>()
    lateinit var orderlist : ArrayList<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_order)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(intent.hasExtra("userId")&&intent.hasExtra("userPassword")&&intent.hasExtra("stadiumName")&&intent.hasExtra("storeName")&&intent.hasExtra("orderList")){
            userId = intent.getStringExtra("userId").toString()
            userPassword = intent.getStringExtra("userPassword").toString()
            stadiumName = intent.getStringExtra("stadiumName").toString()
            storeName = intent.getStringExtra("storeName").toString()
            orderlist = intent.getSerializableExtra("orderList") as ArrayList<Order>
            Log.d("log", "메인에서 받아온 id : $userId, pw : $userPassword, stadium : $stadiumName, storeName= $storeName")
            Log.d("size", orderlist.size.toString())
        }


        binding.btnSeat.setOnClickListener {
            val seatNum = binding.seatNum.text.toString()
            api.postSeat(userId,seatNum).enqueue(object : Callback<PostRegister> {
                override fun onResponse(call: Call<PostRegister>, response: Response<PostRegister>) {
                    binding.seatNum.setText("")
                    val body = response.body()
                    val status = response.body()?.status
                    Log.d("log", "$body")
                    binding.btnSeat.isEnabled = false
                    binding.btnSeat.setBackgroundResource(R.drawable.solid_button_enabled)

                }
                override fun onFailure(call: Call<PostRegister>, t: Throwable) {
                    Log.d("log","${t.localizedMessage}")

                }
            })

        }

        binding.btnOrder.setOnClickListener{



        }

    }

}