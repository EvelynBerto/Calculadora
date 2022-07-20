package com.example.android.calculadora2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide() //remove a to bar
        //numeros
        zero.setOnClickListener{ AcrescentarUmaExpressao("0", true) }
        um.setOnClickListener{ AcrescentarUmaExpressao("1", true) }
        dois.setOnClickListener{ AcrescentarUmaExpressao("2", true) }
        tres.setOnClickListener{ AcrescentarUmaExpressao("3", true) }
        quatro.setOnClickListener{ AcrescentarUmaExpressao("4", true) }
        cinco.setOnClickListener{ AcrescentarUmaExpressao("5", true) }
        seis.setOnClickListener{ AcrescentarUmaExpressao("6", true) }
        sete.setOnClickListener{ AcrescentarUmaExpressao("7", true) }
        oito.setOnClickListener{ AcrescentarUmaExpressao("8", true) }
        nove.setOnClickListener{ AcrescentarUmaExpressao("9", true) }
        ponto.setOnClickListener{ AcrescentarUmaExpressao(".", true) }

        //operadores
        mais.setOnClickListener{AcrescentarUmaExpressao("+", false)}
        menos.setOnClickListener{AcrescentarUmaExpressao("-", false)}
        multiplicacao.setOnClickListener{AcrescentarUmaExpressao("x", false)}
        divisao.setOnClickListener{AcrescentarUmaExpressao("/", false)}

        limpar.setOnClickListener {
            expressao.text = ""
            tv_resultado.text = ""
        }
        backspace.setOnClickListener {
            var string = expressao.text.toString()
            if(string.isNotBlank()){
                expressao.text = string.substring(0,string.length-1)
            }
            tv_resultado.text = ""
        }
        igual.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(expressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble()){
                    tv_resultado.text = longResult.toString()
                }else{
                    tv_resultado.text = resultado.toString()
                }
            }catch (e: Exception){

            }
        }
    }
    fun AcrescentarUmaExpressao(string:String, limpar_dados:Boolean){
        if(tv_resultado.text.isNotEmpty()){
            expressao.text = ""
        }
        else if (limpar_dados){
            tv_resultado.text = ""
            expressao.append(string)
        } else{
            expressao.append(tv_resultado.text)
            expressao.append(string)
            tv_resultado.text = ""
        }
    }
}