package com.example.winneat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.winneat.APIS.APISMenu
import com.example.winneat.APIS.APISStore
import com.example.winneat.PostData.PostMenu
import com.example.winneat.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    val api by lazy { APISMenu.create() }

    var userId=""
    var userPassword=""
    var stadiumName=""
    var storeName=""
    var menulist: ArrayList<PostMenu>? = null

    override fun onCreateView( inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)


//        setFragmentResultListener("search"){ _, bundle ->
//            userId = bundle.getString("userId").toString()
//            userPassword = bundle.getString("userPassword").toString()
//            stadiumName = bundle.getString("stadiumName").toString()
//            storeName = bundle.getString("ustoreName").toString()
//        }

        val qlist = arguments?.getSerializable("menuList")
        userId = arguments?.getString("userId").toString()
        userPassword = arguments?.getString("userPassword").toString()
        stadiumName = arguments?.getString("stadiumName").toString()
        storeName =arguments?.getString("storeName").toString()
        menulist = qlist as? ArrayList<PostMenu>

        recyclerView = binding.rvMenu
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
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_menu, parent, false)
            return ResultViewHolder(view)
        }

        override fun getItemCount(): Int {
            return menulist!!.size
        }

        override fun onBindViewHolder(
            holder: ResultRecyclerViewAdpater.ResultViewHolder,
            position: Int
        ) {
            holder.apply {
                nameTextView.text = menulist?.get(position)?.menuName
                costTextView.text = menulist?.get(position)?.menuPrice.toString()


                menuLayout.setOnClickListener {
                    Log.d("뭐 선택했는지",menulist?.get(position)?.menuName.toString())
                }

                val bundle = bundleOf("storeName" to storeName,
                    "userId" to userId,
                    "userPassword" to userPassword,
                    "stadiumName" to stadiumName,
                    "menulist" to menulist
                )
                // 요청키로 수신측의 리스너에 값을 전달
                setFragmentResult("menu", bundle)

            }
        }

        inner class ResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            //위의 onCreateViewHolder에서 생성된 view를 가지고 실행함
            val nameTextView: TextView = view.findViewById(R.id.tv_Mname)
            val costTextView: TextView = view.findViewById(R.id.tv_cost)
            val menuLayout : ConstraintLayout = view.findViewById(R.id.menu_layout)
        }
    }


}