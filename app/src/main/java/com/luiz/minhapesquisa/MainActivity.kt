package com.luiz.minhapesquisa

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luiz.minhapesquisa.databinding.ActivityMainBinding
import com.luiz.minhapesquisa.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate com ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Definir texto do TextView com seu nome
        binding.tvUserName.text= "Seu Nome Aqui"

        // Configurar Spinner de gênero
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerGender.adapter = adapter
        }

        // Ação do botão
        binding.btnCalculate.setOnClickListener {
            val ageStr = binding.etAge.text.toString()
            if (ageStr.isEmpty()) {
                Toast.makeText(this, "Informe sua idade!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageStr.toInt()
            val gender = binding.spinnerGender.selectedItem.toString()

            // Cálculo conforme regras: 65 para homens, 62 para mulheres
            val remaining = when (gender) {
                "Masculino" -> 65 - age
                else        -> 62 - age
            }

            // Exibir resultado
            binding.tvResult.text = if (remaining > 0) {
                "Faltam $remaining anos para sua aposentadoria."
            } else {
                "Você já pode se aposentar!"
            }
        }
    }
}
