package com.example.calculatortask

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var firstNumber: Double = 0.0
    private var lastNumber: Double = 0.0
    private var myFormatter = DecimalFormat("######.######")
    private var operator: Boolean = false
    private var dot = true
    private var btnEquals = false
    private var status: String? = null
    private var history: String? = null
    private var currentResult: String? = null
    private var number: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_0 -> {
                addToInputText("0")
            }
            R.id.button_1 -> {
                addToInputText("1")
            }
            R.id.button_2 -> {
                addToInputText("2")
            }
            R.id.button_3 -> {
                addToInputText("3")
            }
            R.id.button_4 -> {
                addToInputText("4")
            }
            R.id.button_5 -> {
                addToInputText("5")
            }
            R.id.button_6 -> {
                addToInputText("6")
            }
            R.id.button_7 -> {
                addToInputText("7")
            }
            R.id.button_8 -> {
                addToInputText("8")
            }
            R.id.button_9 -> {
                addToInputText("9")
            }
            R.id.button_dot -> {
                addToInputText(".")
            }

            R.id.btn_clear -> {
                number = null
                status = null
                input.text = ""
                output.text = ""
                firstNumber = 0.0
                lastNumber = 0.0
                dot = true
                output.text = ""
            }

            R.id.button_del -> {
                number = number?.substring(0, number!!.length - 1)
                dot = !number?.contains(".")!!
                output.text = number
            }
            R.id.btn_division -> {
                history = input.text.toString()
                currentResult = output.text.toString()
                input.text = "$history$currentResult/"
                Log.e("Spe", "$operator $status")
                if (operator) {
                    when (status) {
                        "multiplication" -> {
                            multiply()
                        }
                        "sum" -> {
                            plus()
                        }
                        "subtraction" -> {
                            minus()
                        }
                        else -> {
                            divide()
                        }
                    }
                }
                status = "division"
                operator = false
                number = null
            }
            R.id.btn_multiply -> {
                Log.e("Spe", "$operator $status")
                history = input.text.toString()
                currentResult = output.text.toString()
                input.text = "$history$currentResult*"
                if (operator) {
                    when (status) {
                        "sum" -> {
                            plus()
                        }
                        "division" -> {
                            divide()
                        }
                        "subtraction" -> {
                            minus()
                        }
                        else -> {
                            multiply()
                        }
                    }
                }
                status = "multiplication"
                operator = false
                number = null
            }
            R.id.btn_subtraction -> {
                Log.e("Spe", "$operator $status")
                history = input.text.toString()
                currentResult = output.text.toString()
                input.text = "$history$currentResult-"
                if (operator) {
                    when (status) {
                        "multiplication" -> {
                            multiply()
                        }
                        "division" -> {
                            divide()
                        }
                        "sum" -> {
                            plus()
                        }
                        else -> {
                            minus()
                        }
                    }
                }
                status = "subtraction"
                operator = false
                number = null
            }
            R.id.btn_addition -> {
                Log.e("Spe", "$operator $status")
                history = input.text.toString()
                currentResult = output.text.toString()
                input.text = "$history$currentResult+"
                if (operator) {
                    when (status) {
                        "multiplication" -> {
                            multiply()
                        }
                        "division" -> {
                            divide()
                        }
                        "subtraction" -> {
                            minus()
                        }
                        else -> {
                            plus()
                        }
                    }

                }
                status = "sum"
                operator = false
                number = null
            }
            R.id.btn_equals -> {

                if (operator) {
                    when (status) {
                        "sum" -> {
                            plus()
                        }
                        "subtraction" -> {
                            minus()
                        }
                        "multiplication" -> {
                            multiply()
                        }
                        "division" -> {
                            divide()
                        }
                        else -> {
                            firstNumber =
                                java.lang.Double.parseDouble(output.text.toString())
                        }
                    }
                }

                operator = false
                btnEquals = true
            }
        }
    }


    private fun addToInputText(view: String) {
        Log.e("Spe", "$view")
        when {
            number == null -> {
                number = view
            }
            btnEquals -> {
                firstNumber = 0.0
                lastNumber = 0.0
                number = view
            }
            else -> {
                number += view
            }
        }
        output.text = number
        operator = true
        button_del.isClickable = true
        btnEquals = false
    }

    private fun plus() {
        lastNumber = java.lang.Double.parseDouble(output.text.toString())
        firstNumber += lastNumber
        output.text = myFormatter.format(firstNumber)
        Log.e("Spe","plus$firstNumber")
        dot = true
    }

    private fun minus() {
        if (firstNumber == 0.0) {
            firstNumber = java.lang.Double.parseDouble(output.text.toString())
        } else {
            lastNumber = java.lang.Double.parseDouble(output.text.toString())
            firstNumber -= lastNumber
        }
        output.text = myFormatter.format(firstNumber)
        Log.e("Spe","minus$firstNumber")
        dot = true
    }

    private fun multiply() {
        if (firstNumber == 0.0) {
            firstNumber = 1.0
            lastNumber = java.lang.Double.parseDouble(output.text.toString())
            firstNumber *= lastNumber
        } else {
            lastNumber = java.lang.Double.parseDouble(output.text.toString())
            firstNumber *= lastNumber
        }
        output.text = myFormatter.format(firstNumber)
        dot = true
    }

    private fun divide() {
        if (firstNumber == 0.0) {

            lastNumber = java.lang.Double.parseDouble(output.text.toString())
            firstNumber = lastNumber / 1
        } else {
            lastNumber = java.lang.Double.parseDouble(output.text.toString())
            firstNumber /= lastNumber
        }
        output.text = myFormatter.format(firstNumber)
        Log.e("Spe","div$firstNumber")
        dot = true
    }

}