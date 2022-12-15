package com.example.winneat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winneat.APIS.APISInfo
import com.example.winneat.PostData.PostMenu
import com.example.winneat.PostData.PostStore
import com.example.winneat.PostData.storeResult
import com.example.winneat.databinding.FragmentRInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class R_Info_Fragment : Fragment() {
    val api by lazy { APISInfo.create() }
    private var _binding: FragmentRInfoBinding? = null
    private val binding get() = _binding!!
    var argList = arrayListOf<PostStore>()

    var userId=""
    var userPassword=""
    var stadiumName=""
    var storeName=""
    var storeLoc=""
    var storeCategory=""
    var menulist: ArrayList<PostMenu>? = null

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRInfoBinding.inflate(inflater, container, false)


//        setFragmentResultListener("menu") { key, bundle ->
//            userId = bundle.getString("userId").toString()
//            userPassword = bundle.getString("userPassword").toString()
//            stadiumName = bundle.getString("stadiumName").toString()
//            storeName = bundle.getString("storeName").toString()
//        }

        userId = arguments?.getString("userId").toString()
        userPassword = arguments?.getString("userPassword").toString()
        stadiumName = arguments?.getString("stadiumName").toString()
        storeName =arguments?.getString("storeName").toString()

        Log.d("가게정보","${userId},${userPassword},${stadiumName},${storeName}")


        api.postStore(storeName,stadiumName).enqueue(object : Callback<storeResult> {
            override fun onResponse(call: Call<storeResult>, response: Response<storeResult>) {
                // PostLogin 클래스 형식으로 php에 데이터 Post, php가 응답한 데이터 또한 PostLogin 클레스 형식으로 받아옴

                val body = response.body()
                Log.d("choose_store","${body}")

                storeLoc = response.body()?.storeList?.get(0)?.storeLoc.toString()
                storeCategory =response.body()?.storeList?.get(0)?.storeCategory.toString()

                binding.storeLoc.text = storeLoc
                binding.storeCategory.text = storeCategory
                binding.storeStadium.text = stadiumName
            }
            override fun onFailure(call: Call<storeResult>, t: Throwable) {
                Log.d("log","${t.localizedMessage}")

            }
        })

        return binding.root
    }


}