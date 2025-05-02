package com.example.cadastrokotlin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastrokotlin.R
import com.example.cadastrokotlin.data.db.DatabaseHelper
import com.example.cadastrokotlin.data.model.Cliente
import com.example.cadastrokotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoVoltar.setOnClickListener{
            finish()
        }

        val nome = findViewById<EditText>(R.id.nomeEditText)
        val email = findViewById<EditText>(R.id.emailEditText)
        val sexo = findViewById<RadioGroup>(R.id.radioSexo)
        val spinnerEscolaridade = findViewById<Spinner>(R.id.spinnerEscolaridade)
        val btnCadastrar = findViewById<Button>(R.id.saveButton)

        val escolaridades = resources.getStringArray(R.array.Escolaridades)

        if (spinnerEscolaridade != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, escolaridades)
            spinnerEscolaridade.adapter = adapter
        }

        db = DatabaseHelper(this)
        btnCadastrar.setOnClickListener{
            val postNome = nome.text.toString()
            val postEmail = email.text.toString()


            if (postNome.isBlank() || postEmail.isBlank() || !isRadioGroupFilled(sexo)) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val sexoIdSelecionado = sexo.checkedRadioButtonId
                val postSexo = findViewById<RadioButton>(sexoIdSelecionado).id

                val postEscolaridade = spinnerEscolaridade.selectedItem.toString()

                val cliente = Cliente(0, postNome, postEmail, postSexo, postEscolaridade)
                db.insertCliente(cliente)
                finish()
                Toast.makeText(this, "Cliente Cadastrado", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun isRadioGroupFilled(radioGroup: RadioGroup): Boolean {
        return radioGroup.checkedRadioButtonId != -1
    }
}