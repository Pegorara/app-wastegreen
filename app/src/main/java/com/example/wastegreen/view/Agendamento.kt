package com.example.wastegreen.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wastegreen.R
import com.example.wastegreen.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)
            }else{
                mes = (monthOfYear +1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if (minute < 10){
                minuto = "0$minute"
            }else{
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {

            val plano1 = binding.plano1
            val plano2 = binding.plano2
            val plano3 = binding.plano3

            when{
                hora.isEmpty() -> {
                    mensagem(it,"Preencha o horário!","#FF0000")
                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it,"Wastegreen esta fechada - horário de atendimento das 08:00 as 19:00!","#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it,"Coloque uma data!","#FF0000")
                }
                plano1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso!","#38BDF8")
                }
                plano2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso!","#38BDF8")
                }
                plano3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso!","#38BDF8")
                }
                else -> {
                    mensagem(it,"Escolha um plano!","#FF0000")
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
//
//    private fun salvarAgendamento(view: View, cliente: String, barbeiro: String, data: String, hora: String){
//
//        val db = FirebaseFirestore.getInstance()
//
//        val dadosUsuario = hashMapOf(
//            "cliente" to cliente,
//            "barbeiro" to barbeiro,
//            "data" to data,
//            "hora" to hora
//        )
//
//        db.collection("agendamento").document(cliente).set(dadosUsuario).addOnCompleteListener {
//            mensagem(view,"Agendamento realizado com sucesso!","#FF03DAC5")
//        }.addOnFailureListener {
//            mensagem(view,"Erro no servidor!","#FF0000")
//        }
//    }
}