package com.example.winneat

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.winneat.APIS.APISOrder
import com.example.winneat.APIS.APISOrderDetail
import com.example.winneat.PostData.PostMenu
import com.example.winneat.PostData.PostRegister
import com.example.winneat.APIS.APISSeat
import com.example.winneat.PostData.PostOrder
import com.example.winneat.databinding.ActivityOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import java.time.LocalDateTime

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    val api_1 by lazy { APISSeat.create() }
    val api_2 by lazy { APISOrder.create() }
    val api_3 by lazy { APISOrderDetail.create() }
    lateinit var recyclerView: RecyclerView

    var userId = ""
    var userPassword = ""
    var stadiumName=""
    var storeName=""
    var argList = arrayListOf<PostMenu>()
    var orderData : ArrayList<Order> = arrayListOf<Order>()
    lateinit var orderlist : ArrayList<Order>
    val thisActivity = this
    @RequiresApi(Build.VERSION_CODES.O)
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
            api_1.postSeat(userId,seatNum).enqueue(object : Callback<PostRegister> {
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
            val orderNum : String = userId + LocalDateTime.now()
            val orderDetailNum = orderNum + "_1"
            val orderStatus = "접수"
            val menuQuantity = 1
            val menuName = orderlist.get(0).menuName

            api_2.postOrder(userId,orderNum,orderStatus,stadiumName).enqueue(object : Callback<PostOrder> {
                override fun onResponse(call: Call<PostOrder>, response: Response<PostOrder>) {
                    val body = response.body()
                    val status = body?.orderStatus.toString()

                    Log.d("log1", body.toString())

                }
                override fun onFailure(call: Call<PostOrder>, t: Throwable) {
                    Log.d("log1","${t.localizedMessage}")
                    ToastObj.createToast(applicationContext, "주문 오류")?.show() // 토스트 메시지 띄움
                }
            })

            api_3.postOrderDetail(orderNum,menuName,orderDetailNum,menuQuantity).enqueue(object : Callback<PostOrder> {
                override fun onResponse(call: Call<PostOrder>, response: Response<PostOrder>) {
                    val body = response.body()
                    val status = body?.orderStatus.toString()

                    Log.d("log2", body.toString())
                    binding.btnOrder.isEnabled = false
                    binding.btnOrder.setBackgroundResource(R.drawable.solid_button_enabled)

                }
                override fun onFailure(call: Call<PostOrder>, t: Throwable) {
                    Log.d("log2","${t.localizedMessage}")
                    ToastObj.createToast(applicationContext, "주문 오류")?.show() // 토스트 메시지 띄움

                }
            })

            val intent = Intent(thisActivity,MainActivity::class.java)
            intent.putExtra("userId",userId)
            intent.putExtra("userPassword",userPassword)
            intent.putExtra("stadiumName",stadiumName)
            startActivity(intent) // 메인 액티비티로 이동

        }

    }

}