package com.example.winneat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.winneat.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private var rvadapter: Madapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        rvadapter = Madapter(Mdata())
        binding.rvMenu.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvadapter
        }
    }

    private fun Mdata() = arrayListOf(
        Menu(R.drawable.cutlery, "메뉴1", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴2", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴3", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴4", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴5", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴6", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴7", "20,000원"),
        Menu(R.drawable.cutlery, "메뉴8", "20,000원")
    )
}