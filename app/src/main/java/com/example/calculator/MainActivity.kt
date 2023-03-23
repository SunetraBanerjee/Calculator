package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvclk: TextView?=null

    var lastnumeric:Boolean=false
    var lastdot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvclk=findViewById(R.id.tvclk)
    }


    fun onDigit(view: View){
        tvclk?.append((view as Button).text)
        lastnumeric=true
        lastdot=false

    }
    fun onClear(view: View)
    {
        tvclk?.text=""
    }

    fun onDot(view: View)
    {
        if(lastnumeric && !lastdot)
        {
            tvclk?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }

    fun onOperator(view: View)
    {
        tvclk?.text?.let{
            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvclk?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }

    fun onEqual(view: View)
    {
        if(lastnumeric)
        {
            var tvValue=tvclk?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue=tvValue.substring(1)

                }
                if(tvValue.contains("-"))
                {
                    val splitVal=tvValue.split("-")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvclk?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }
                else if(tvValue.contains("+"))
                {
                    val splitVal=tvValue.split("+")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvclk?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }
                else if(tvValue.contains("*"))
                {
                    val splitVal=tvValue.split("*")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvclk?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }
                else if(tvValue.contains("/"))
                {
                    val splitVal=tvValue.split("/")
                    var one=splitVal[0]
                    var two=splitVal[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tvclk?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }

            }catch (e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result:String):String
    {
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)

        return value
    }
}
