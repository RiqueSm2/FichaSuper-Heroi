package com.example.fichasuper_heroi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val codiname = findViewById<EditText>(R.id.CODINAME)//Onde o usuario vai colocar o codiname

        val btnCriarPerfil = findViewById<Button>(R.id.btnCriarPerfil)//Botao de criar perfil

        val cnSalvos = getSharedPreferences("CODINAMES", MODE_PRIVATE)//Criando variavel como SharedPrefences, onde posso acessa-la a qualquer momento
        val editor = cnSalvos.edit()

        codiname.setText(cnSalvos.getString("NmDigitado",""))//Setando o ultimo valor salvo no cnSalvos (A key deve ser a mesma do putString)


        btnCriarPerfil.setOnClickListener { view ->
            val nomeDigitado = codiname.text.toString()//O codiname digitado vai para esta variavel

            editor.putString("NmDigitado", nomeDigitado).apply()//Estou jogando para dentro da Shared preferences o nome digitado

            val tela2 = Intent(this, CriarPerfil::class.java)//Aqui estou criando uma Intent para passar para a proxima tela

            tela2.putExtra("EXTRA_CODINAME", nomeDigitado) //"Empacotando" para eu poder reutilizar na outra tela

            startActivity(tela2)//Iniciando a Intent

        }

    }
}