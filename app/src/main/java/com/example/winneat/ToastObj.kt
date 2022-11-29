package com.example.winneat

import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.winneat.databinding.ToastDesignBinding

object ToastObj {
    fun createToast(context: Context, message: String): Toast? {
        val inflater = LayoutInflater.from(context)
        val binding: ToastDesignBinding = DataBindingUtil.inflate(inflater,R.layout.toast_design, null, false)
//        val binding: ToastSampleBinding = DataBindingUtil.inflate(inflater, R.layout.toast_design, null, false)

        binding.tvSample.text = message

        return Toast(context).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 30.toPx())
            duration = Toast.LENGTH_LONG
            view = binding.root
        }
    }

    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}