package com.example.winneat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.winneat.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private var rvadapter: Rv_adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        rvadapter = Rv_adapter(Rvdata())
        binding.rvReview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvadapter
        }
    }

    private fun Rvdata() = arrayListOf(
        Review ("User1", 5.0F, "맛있어요."),
        Review ("User2", 4.5F, "맛있어요."),
        Review ("User3", 5.0F, "맛있어요."),
        Review ("User4", 5.0F, "맛있어요."),
        Review ("User5", 5.0F, "맛있어요."),
    )
}