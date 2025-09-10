package com.example.fichasuper_heroi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CriarPerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_criar_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

      val boas_vindas = findViewById<TextView>(R.id.personalize)
      val codiname = intent.getStringExtra("EXTRA_CODINAME") //criando uma variavel para jogar o codiname "empacotado" da tela 1

      boas_vindas.text = "Personalize o perfil de: $codiname"

      val rgOpcoes = findViewById<RadioGroup>(R.id.rgOpcoes)//Jogando a opção selencionada em Herois para esta variavel

      rgOpcoes.setOnCheckedChangeListener{group, checkedId ->

          val mensagem = when (checkedId) { //ao clicar em uma das 3 opcoes ira aparecer esta mensagem
              R.id.rbtnHeroi -> "Bem vindo a Liga da Justiça!"
              R.id.rbtnVilao -> "Bem vindo ao esquadrao suic..Ops Liga da Justiça!"
              R.id.rbtnAntiHeroi ->"A Amanda Waller tem um trabalho perfeito para você!"
              else -> ""
          }

          if (mensagem.isNotEmpty()) {
              Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show() //Se for clicada, aparecera a mensagem rapidamente
          }

      }

      val avatar = listOf(
          R.drawable.coringa,
          R.drawable.superman,
          R.drawable.pacificador,
     )
        var avatarIndex = 0 //"var" é uma variavel mutavel

        val imgAvatar = findViewById<ImageView>(R.id.imgAvatar)


        imgAvatar.setOnClickListener {

          val posicao = (avatarIndex + 1) % avatar.size  // usa o resto da divisao para atualizar a posição, quando chega no 3 (n. de imgs),
                                                         // divide por 3  e o resto é 0, voltando na primeira imagem
          imgAvatar.setImageResource(avatar[posicao])   //Alterando a imagem de acordo com o indice criado no ListOf

          avatarIndex = posicao // atualizando o index

        }



        val gerarFicha = findViewById<Button>(R.id.btnFicha)


        gerarFicha.setOnClickListener{view ->

            val checkAlinhamento = rgOpcoes.checkedRadioButtonId //Como não esta no parametro, eu devo "buscar" o radio button selecionado
                val alinhamento = when (checkAlinhamento){
                R.id.rbtnHeroi ->"Heroi"
                R.id.rbtnVilao ->"Vilão"
                R.id.rbtnAntiHeroi->"Anti-Heroi"
                else -> {""}
             }

            val poderes = arrayListOf<String>() //criando uma array, e ao verificar se o CHECKBOX esta marcado, adiciona o poder correspondente na array

            if (findViewById<CheckBox>(R.id.chSuperForca).isChecked) {
                poderes.add("Super-Força")
            }
            if (findViewById<CheckBox>(R.id.chSuperVelocidade).isChecked) {
                poderes.add("Super Velocidade")
            }
            if (findViewById<CheckBox>(R.id.chVoo).isChecked) {
                poderes.add("Voo")
            }
            if (findViewById<CheckBox>(R.id.chTelepatia).isChecked) {
                poderes.add("Telepatia")
            }
            if (findViewById<CheckBox>(R.id.chRajadasEnergia).isChecked) {
                poderes.add("Rajadas de Energia")
            }
        val tela3 = Intent(this, FichaDoHeroi::class.java)
        tela3.putExtra("ALINHAMENTO", alinhamento)
        tela3.putExtra("PODERES", poderes)
        tela3.putExtra("AVATAR", avatar[avatarIndex])
        startActivity(tela3)

        }//fim button gerar ficha

        //recuperando dados tela 3
        val alinhamentoExtra = intent.getStringExtra("ALINHAMENTOt3")
        val poderesExtra = intent.getStringArrayListExtra("PODERESt3")
        val avatarExtra = intent.getIntExtra("AVATARt3", R.drawable.superman)

        when (alinhamentoExtra) {
         "Heroi" -> rgOpcoes.check(R.id.rbtnHeroi)
         "Vilão" -> rgOpcoes.check(R.id.rbtnVilao)
         "Anti-Heroi" -> rgOpcoes.check(R.id.rbtnAntiHeroi)
        }

        poderesExtra?.forEach { poder -> // o forEach pecorre cada item da lista, se o nome bater, ele marca o check box referente como true
            when (poder) {
                "Super-Força" -> findViewById<CheckBox>(R.id.chSuperForca).isChecked = true
                "Super Velocidade" -> findViewById<CheckBox>(R.id.chSuperVelocidade).isChecked = true
                "Voo" -> findViewById<CheckBox>(R.id.chVoo).isChecked = true
                "Telepatia" -> findViewById<CheckBox>(R.id.chTelepatia).isChecked = true
                "Rajadas de Energia" -> findViewById<CheckBox>(R.id.chRajadasEnergia).isChecked = true
            }
        }
          imgAvatar.setImageResource(avatarExtra)

    }
}