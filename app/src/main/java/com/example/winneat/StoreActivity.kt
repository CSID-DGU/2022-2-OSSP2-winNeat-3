package com.example.winneat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.winneat.APIS.APISMenu
import com.example.winneat.PostData.PostMenu
import com.example.winneat.PostData.menuResult
import com.example.winneat.databinding.ActivityStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class StoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoreBinding
    val api by lazy { APISMenu.create() }

    var userId = ""
    var userPassword = ""
    var storeName=""
    var stadiumName=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_store)

        if(intent.hasExtra("userId")&&intent.hasExtra("userPassword")&&intent.hasExtra("storeName")){
            userId = intent.getStringExtra("userId").toString()
            userPassword = intent.getStringExtra("userPassword").toString()
            storeName = intent.getStringExtra("storeName").toString()
            stadiumName = intent.getStringExtra("stadiumName").toString()

          //  Log.d("log", "받아온 id : $userId, pw : $userPassword, storeName : $storeName, stadiumName : $stadiumName")
        }
        binding.storeNameText.text=storeName

        var argList = arrayListOf<PostMenu>()

        api.postMenu(stadiumName, storeName).enqueue(object : Callback<menuResult> {
            override fun onResponse(call: Call<menuResult>, response: Response<menuResult>) {

                val body = response.body()
                Log.d("log","${body}")

                val list : List<PostMenu>? = response.body()?.MenuList
                argList= response.body()?.MenuList as ArrayList<PostMenu>

                val bundle = Bundle()
                bundle.putSerializable("menuList",(list as Serializable))
                bundle.putString("userId",userId)
                bundle.putString("userPassword",userPassword)
                bundle.putString("storeName",storeName)
                bundle.putString("stadiumName",stadiumName)

                val menuFragment = MenuFragment()
                menuFragment.arguments = bundle
                Log.d("log", "$bundle")


                val manager:FragmentManager=supportFragmentManager
                val transaction:FragmentTransaction=manager.beginTransaction()
                transaction.replace(R.id.store_main_frm,menuFragment).commit()

            }
            override fun onFailure(call: Call<menuResult>, t: Throwable) {
                Log.d("log","${t.localizedMessage}")

            }
        })


        binding.storeMainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuFragment -> {
                    val bundle = Bundle()
                    bundle.putString("userId",userId)
                    bundle.putString("userPassword",userPassword)
                    bundle.putString("storeName",storeName)
                    bundle.putString("stadiumName",stadiumName)

                    val menuFragment = MenuFragment()
                    menuFragment.arguments = bundle
                    Log.d("log", "$bundle")

                    val manager:FragmentManager=supportFragmentManager
                    val transaction:FragmentTransaction=manager.beginTransaction()
                    transaction.replace(R.id.store_main_frm,menuFragment).commit()

//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, HomeFragment())
//                        .commitAllowingStateLoss()

                    return@setOnItemSelectedListener true
                }
                R.id.Fragment_R_Info -> {
                    val bundle = Bundle()
                    bundle.putString("userId",userId)
                    bundle.putString("userPassword",userPassword)
                    bundle.putString("storeName",storeName)
                    bundle.putString("stadiumName",stadiumName)

                    val RInfoFragment = R_Info_Fragment()
                    RInfoFragment.arguments = bundle
                    Log.d("STORE TO RINFO", "$bundle")

                    val manager:FragmentManager=supportFragmentManager
                    val transaction:FragmentTransaction=manager.beginTransaction()
                    transaction.replace(R.id.store_main_frm,RInfoFragment).commit()

//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.store_main_frm, R_Info_Fragment())
//                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.ReviewFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.store_main_frm, ReviewFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false

        }

    }

}


