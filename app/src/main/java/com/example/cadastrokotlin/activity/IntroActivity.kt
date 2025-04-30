package com.example.cadastrokotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastrokotlin.MainActivity
import com.example.cadastrokotlin.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding:ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnCadastre.setOnClickListener{
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }
}