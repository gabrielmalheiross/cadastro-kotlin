package com.example.cadastrokotlin.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastrokotlin.R
import com.example.cadastrokotlin.data.db.DatabaseHelper
import com.example.cadastrokotlin.data.model.Cliente
import com.example.cadastrokotlin.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        binding.saveButton.setOnClickListener{
            val nome = binding.nomeEditText.text.toString()
            val email = binding.emailEditText.text.toString()
//            if (TextUtils.isEmpty(nome.getText())) {
//                nome.setError("Nome é obrigatório")
//            }
//            if (TextUtils.isEmpty(email.getText())) {
//                email.setError("Email é obrigatório")
//            }


            val cliente = Cliente(0, nome, email)
            db.insertCliente(cliente)
            finish()
            Toast.makeText(this, "Cliente Cadastrado", Toast.LENGTH_SHORT).show()
        }
    }
}