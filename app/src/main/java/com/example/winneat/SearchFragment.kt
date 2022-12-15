package com.example.winneat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.winneat.PostData.PostStore
import com.example.winneat.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var recyclerView: RecyclerView
    lateinit var storelist : List<PostStore>
    lateinit var stadiumName:String
    var userId=""
    var userPassword=""
    var storeName=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val qlist = arguments?.getSerializable("storeList")
        userId = arguments?.getString("userId").toString()
        userPassword = arguments?.getString("userPassword").toString()
        stadiumName = arguments?.getString("stadiumName").toString()
        storelist = qlist as List<PostStore>

        Log.d("fragment 들어옴", qlist.toString())
        Log.d("fragment 들어옴", storelist.toString())
        Log.d("userId 들어옴", userId)
        Log.d("userPassword 들어옴", userPassword)
        Log.d("stadiumName 들어옴",stadiumName)

        binding.stadiumName.setText(stadiumName)

        recyclerView = binding.searchRecycle
        recyclerView.adapter = ResultRecyclerViewAdpater()
        recyclerView.layoutManager = LinearLayoutManager(activity?.getApplicationContext())

        return binding.root
    }

    inner class ResultRecyclerViewAdpater() :
        RecyclerView.Adapter<ResultRecyclerViewAdpater.ResultViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ResultRecyclerViewAdpater.ResultViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item, parent, false)
            return ResultViewHolder(view)
        }

        override fun getItemCount(): Int {
            return storelist!!.size
        }

        override fun onBindViewHolder(
            holder: ResultRecyclerViewAdpater.ResultViewHolder,
            position: Int
        ) {
            holder.apply {
                nameTextView.text = storelist?.get(position)?.storeName

                if(storelist?.get(position)?.storeCategory.equals("분식")){
                    Glide.with(itemView).load("https://img.freepik.com/free-photo/cheesy-tokbokki-korean-traditional-food-on-black-board-background-lunch-dish_1150-42995.jpg?w=996&t=st=1671086046~exp=1671086646~hmac=e5010d5ea59272a4e9ea314f833a4493f2295cc997eaf946f8d695d72f5fc89a").override(400,200).centerCrop().into(storePic)
                }else if (storelist?.get(position)?.storeCategory.equals("호프")){
                    Glide.with(itemView).load("https://cdn.pixabay.com/photo/2017/02/25/15/23/barbecue-2098020_960_720.jpg").override(400,200).centerCrop().into(storePic)
                }else if (storelist?.get(position)?.storeCategory.equals("치킨")){
                    Glide.with(itemView).load("http://www.bhc.co.kr/upload/bhc/menu/ckc20150130_470_v.jpg").override(400,200).centerCrop().into(storePic)
                }else if (storelist?.get(position)?.storeCategory.equals("햄버거")){
                    Glide.with(itemView).load("https://cdn.pixabay.com/photo/2014/10/19/20/59/hamburger-494706_960_720.jpg").override(400,200).centerCrop().into(storePic)
                }else if (storelist?.get(position)?.storeCategory.equals("디저트")){
                    Glide.with(itemView).load("https://img.freepik.com/free-photo/homemade-refreshing-summer-lemonade-drink-with-lemon-slices-mint-and-ice-cubes_176532-10726.jpg?w=996&t=st=1671086270~exp=1671086870~hmac=b6db4b28936d728fa5bedebc1dbcdcf12ba759d2ac5fa9b5e00706a105d2b64e").override(400,200).centerCrop().into(storePic)
                }

                storeLayout.setOnClickListener {
                    //Log.d("뭐 선택했는지",storelist?.get(position)?.storeName.toString())
                    storeName=storelist?.get(position)?.storeName.toString()
                    val intent = Intent(activity,StoreActivity::class.java)
                    intent.apply {
                        this.putExtra("userId",userId) // 데이터 넣기
                        this.putExtra("userPassword",userPassword) // 데이터 넣기
                        this.putExtra("storeName",storeName)
                        this.putExtra("stadiumName", stadiumName)
                    }

                    val bundle = bundleOf("userId" to userId, "userPassword" to userPassword, "storeName" to storeName, "stadiumName" to stadiumName)
                    setFragmentResult("search",bundle)
                    Log.d("search bundle", bundle.toString())

                    startActivity(intent)
                }
            }
        }

        inner class ResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            //위의 onCreateViewHolder에서 생성된 view를 가지고 실행함
            val nameTextView: TextView = view.findViewById(R.id.store_name)
            val storePic : ImageView = view.findViewById(R.id.storePic)
            val storeLayout : LinearLayout = view.findViewById(R.id.storeLayout)
        }
    }
}