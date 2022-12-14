package com.example.winneat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.winneat.databinding.ActivityStoreBinding
import com.google.android.material.tabs.TabLayoutMediator

class StoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoreBinding

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

            Log.d("log", "받아온 id : $userId, pw : $userPassword, storeName : $storeName, stadiumName : $stadiumName")


        }
        initViewPager()

    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var viewPager2Adatper = ViewPager2Adapter(this)
        viewPager2Adatper.addFragment(MenuFragment())
        viewPager2Adatper.addFragment(R_Info_Fragment())
        viewPager2Adatper.addFragment(ReviewFragment())

        //Adapter 연결
        binding.vpViewpagerMain.apply {
            adapter = viewPager2Adatper

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tlNavigationView, binding.vpViewpagerMain) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "메뉴"
                1 -> tab.text = "정보"
                2 -> tab.text = "리뷰"
            }
        }.attach()
    }
}