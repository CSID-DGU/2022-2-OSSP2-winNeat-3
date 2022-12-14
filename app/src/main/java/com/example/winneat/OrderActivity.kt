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
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.BootpayAnalytics
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.Payload
import kr.co.bootpay.android.models.statistics.BootStatItem
import kr.co.bootpay.android.models.BootItem
import kr.co.bootpay.android.models.BootExtra
import kr.co.bootpay.android.models.BootUser




class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    val api_1 by lazy { APISSeat.create() }
    val api_2 by lazy { APISOrder.create() }
    val api_3 by lazy { APISOrderDetail.create() }
    lateinit var recyclerView: RecyclerView

    var seatNum=""
    var userId = ""
    var userPassword = ""
    var stadiumName=""
    var storeName=""
    var argList = arrayListOf<PostMenu>()
    var orderData : ArrayList<Order> = arrayListOf<Order>()
    lateinit var orderlist : ArrayList<Order>
    val thisActivity = this

    private val AppId = "638789f2d01c7e001d7bd5b3" //production

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
            Log.d("log", "???????????? ????????? id : $userId, pw : $userPassword, stadium : $stadiumName, storeName= $storeName")
            Log.d("size", orderlist.size.toString())
        }


        binding.btnSeat.setOnClickListener {
            seatNum = binding.seatNum.text.toString()
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
            val orderStatus = "??????"
            val menuQuantity = 1
            val menuName = orderlist.get(0).menuName

            api_2.postOrder(userId,orderNum,orderStatus,stadiumName,seatNum,orderDetailNum,menuName,menuQuantity).enqueue(object : Callback<PostOrder> {
                override fun onResponse(call: Call<PostOrder>, response: Response<PostOrder>) {
                    val body = response.body()
                    val status = body?.orderStatus.toString()

                    Log.d("log1", body.toString())

                    binding.btnOrder.isEnabled = false
                    binding.btnOrder.setBackgroundResource(R.drawable.solid_button_enabled)
                }
                override fun onFailure(call: Call<PostOrder>, t: Throwable) {
                    Log.d("log1","${t.localizedMessage}")
                    ToastObj.createToast(applicationContext, "?????? ??????")?.show() // ????????? ????????? ??????
                }
            })

//            api_3.postOrderDetail(orderNum,menuName,orderDetailNum,menuQuantity).enqueue(object : Callback<PostOrder> {
//                override fun onResponse(call: Call<PostOrder>, response: Response<PostOrder>) {
//                    val body = response.body()
//                    val status = body?.orderStatus.toString()
//
//                    Log.d("log2", body.toString())
//                    binding.btnOrder.isEnabled = false
//                    binding.btnOrder.setBackgroundResource(R.drawable.solid_button_enabled)
//
//                }
//                override fun onFailure(call: Call<PostOrder>, t: Throwable) {
//                    Log.d("log2","${t.localizedMessage}")
//                    ToastObj.createToast(applicationContext, "?????? ??????")?.show() // ????????? ????????? ??????
//
//                }
//            })


            // ?????????
            goRequest()

//            val intent = Intent(thisActivity,MainActivity::class.java)
//            intent.putExtra("userId",userId)
//            intent.putExtra("userPassword",userPassword)
//            intent.putExtra("stadiumName",stadiumName)
//            startActivity(intent) // ?????? ??????????????? ??????

        }

    }

    private fun goRequest() {
        val user = BootUser().setPhone("010-1234-5678") // ????????? ??????
        val extra = BootExtra()
            .setCardQuota("0,2,3") // ?????????, 2??????, 3?????? ?????? ??????, ????????? ?????? 12???????????? ????????? (5?????? ?????? ????????? ???????????? ??????)

        val price = orderlist.get(0).menuPrice.toDouble()

        val pg = "???????????????"
        val method = "??????"

        val items: MutableList<BootItem> = ArrayList()
        val item1 = BootItem().setName("??????'s ???").setId("ITEM_CODE_MOUSE").setQty(1).setPrice(orderlist.get(0).menuPrice.toDouble())
      //  val item2 = BootItem().setName("?????????").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(8000.0)
        items.add(item1)
      //  items.add(item2)

        val payload = Payload()
        payload.setApplicationId(AppId)
            .setOrderName(orderlist.get(0).menuName.toString()+" ??????")
            .setPg(pg)
            .setOrderId("1234")
            .setMethod(method)
            .setPrice(price)
            .setUser(user)
            .setExtra(extra).items = items

        val map: MutableMap<String, Any> = HashMap()
        map["1"] = "abcdef"
        map["2"] = "abcdef55"
        map["3"] = 1234
        payload.metadata = map

        Bootpay.init(supportFragmentManager, applicationContext)
            .setPayload(payload)
            .setEventListener(object : BootpayEventListener {
                override fun onCancel(data: String) {
                    Log.d("bootpay", "cancel: $data")
                }

                override fun onError(data: String) {
                    Log.d("bootpay", "error: $data")
                }

                override fun onClose(data: String) {
                    Log.d("bootpay", "close: $data")
                    Bootpay.removePaymentWindow()
                }

                override fun onIssued(data: String) {
                    Log.d("bootpay", "issued: $data")
                }

                override fun onConfirm(data: String): Boolean {
                    Log.d("bootpay", "confirm: $data")
                    //Bootpay.transactionConfirm(data); //????????? ????????? ????????? ???????????? ?????? true (?????? 1)
                    return true //????????? ????????? ????????? ???????????? ?????? true (?????? 2)
                    //return false; //????????? ???????????? ????????? false
                }

                override fun onDone(data: String) {
                    Log.d("done", data)
                }
            }).requestPayment()
    }


}