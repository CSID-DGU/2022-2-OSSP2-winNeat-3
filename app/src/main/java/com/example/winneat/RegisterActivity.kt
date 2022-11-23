package com.example.winneat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winneat.databinding.ActivityLoginBinding
import com.example.winneat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}