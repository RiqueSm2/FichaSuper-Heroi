package com.example.fichasuper_heroi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FichaDoHeroi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ficha_do_heroi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val cnSalvos = getSharedPreferences("CODINAMES", MODE_PRIVATE) //ABRINDO O SHARED PREFERENCES DA TELA 1
        val codinameSalvo = cnSalvos.getString("NmDigitado", "Sem codiname")
        val codiname = findViewById<TextView>(R.id.tvCODINAME)
        codiname.setText("SEU CODINAME: $codinameSalvo" )

        val alinhamentoEXTRA = intent.getStringExtra("ALINHAMENTO")
        val alinhamento = findViewById<TextView>(R.id.tvALINHAMENTO)
        alinhamento.setText("ALINHAMENTO: $alinhamentoEXTRA")

        val poderesEXTRA = intent.getStringArrayListExtra("PODERES")
        val poderes = findViewById<TextView>(R.id.tvSeusPoderes)
        poderes.setText ("PODERES: ${poderesEXTRA?.joinToString(", ")}")//O ${} define o escopo de processamento, o ?. em caso de null nao deixa dar erro e o join to string transforma toda a lista em uma unica string



        val imgEXTRA = intent.getIntExtra("AVATAR", R.drawable.superman )
        val imgFICHA =findViewById<ImageView>(R.id.iconeAvatar)
        imgFICHA.setImageResource(imgEXTRA)



        val avatar = intent.getIntExtra("AVATAR", R.drawable.superman)


        val editar = findViewById<Button>(R.id.btnEDITAR)

        editar.setOnClickListener {view ->


        val tela2 = Intent(this, CriarPerfil::class.java)
        tela2.putExtra("ALINHAMENTOt3", alinhamentoEXTRA)
        tela2.putExtra("PODERESt3", poderesEXTRA)
        tela2.putExtra("AVATARt3", imgEXTRA)
        startActivity(tela2)

        }

    }
}