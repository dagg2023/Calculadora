package com.surveycolombia.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.EditText
import java.lang.StringBuilder
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MainActivity : AppCompatActivity() {

    // Declaración de variables
    lateinit var button0: android.widget.Button
    lateinit var button1: android.widget.Button
    lateinit var button2: android.widget.Button
    lateinit var button3: android.widget.Button
    lateinit var button4: android.widget.Button
    lateinit var button5: android.widget.Button
    lateinit var button6: android.widget.Button
    lateinit var button7: android.widget.Button
    lateinit var button8: android.widget.Button
    lateinit var button9: android.widget.Button
    lateinit var button00: android.widget.Button
    lateinit var buttonPorcen: android.widget.Button
    lateinit var buttonBorrar: android.widget.Button
    lateinit var buttonRetro: android.widget.Button
    lateinit var buttonPunto: android.widget.Button
    lateinit var buttonSum: android.widget.Button
    lateinit var buttonRes: android.widget.Button
    lateinit var buttonDiv: android.widget.Button
    lateinit var buttonMult: android.widget.Button
    lateinit var buttonIgua: android.widget.Button
    lateinit var inputText: EditText
    lateinit var resultText: EditText
    var chk = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Asignar los botones y campos de texto a las variables
        button0 = findViewById(R.id.button0)
        button00 = findViewById(R.id.button00)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        buttonSum = findViewById(R.id.buttonSuma)
        buttonRes = findViewById(R.id.buttonResta)
        buttonDiv = findViewById(R.id.buttonDiv)
        buttonMult = findViewById(R.id.buttonMult)
        buttonIgua = findViewById(R.id.buttonIgual)
        buttonPunto = findViewById(R.id.buttonPunto)
        buttonPorcen = findViewById(R.id.buttonPorcentaje)
        buttonBorrar = findViewById(R.id.buttonBorrar)
        buttonRetro = findViewById(R.id.buttonRetro)
        inputText = findViewById(R.id.edtInputNumber)
        resultText = findViewById(R.id.edtResult)

        // Habilitar el scroll en el campo de texto de entrada
        inputText.movementMethod = ScrollingMovementMethod()
        inputText.isActivated
        inputText.isPressed

        var text: String

        // Configurar los listeners de los botones numéricos
        button1.setOnClickListener {
            text = inputText.text.toString() + "1"
            inputText.setText(text)
            resultado(text)
        }

        button2.setOnClickListener {
            text = inputText.text.toString() + "2"
            inputText.setText(text)
            resultado(text)
        }

        button3.setOnClickListener {
            text = inputText.text.toString() + "3"
            inputText.setText(text)
            resultado(text)
        }

        button4.setOnClickListener {
            text = inputText.text.toString() + "4"
            inputText.setText(text)
            resultado(text)
        }

        button5.setOnClickListener {
            text = inputText.text.toString() + "5"
            inputText.setText(text)
            resultado(text)
        }

        button6.setOnClickListener {
            text = inputText.text.toString() + "6"
            inputText.setText(text)
            resultado(text)
        }

        button7.setOnClickListener {
            text = inputText.text.toString() + "7"
            inputText.setText(text)
            resultado(text)
        }

        button8.setOnClickListener {
            text = inputText.text.toString() + "8"
            inputText.setText(text)
            resultado(text)
        }

        button9.setOnClickListener {
            text = inputText.text.toString() + "9"
            inputText.setText(text)
            resultado(text)
        }

        button0.setOnClickListener {
            text = inputText.text.toString() + "0"
            inputText.setText(text)
            resultado(text)
        }

        button00.setOnClickListener {
            text = inputText.text.toString() + "00"
            inputText.setText(text)
            resultado(text)
        }

        buttonPunto.setOnClickListener {
            text = inputText.text.toString() + "."
            inputText.setText(text)
            resultado(text)
        }

        // Configurar los listeners de los botones de operaciones

        buttonSum.setOnClickListener {
            text = inputText.text.toString() + "+"
            inputText.setText(text)
            chk = chk + 1
        }

        buttonRes.setOnClickListener {
            text = inputText.text.toString() + "-"
            inputText.setText(text)
            chk = chk + 1
        }

        buttonMult.setOnClickListener {
            text = inputText.text.toString() + "*"
            inputText.setText(text)
            chk = chk + 1
        }

        buttonDiv.setOnClickListener {
            text = inputText.text.toString() + "/"
            inputText.setText(text)
            chk = chk + 1
        }

        buttonPorcen.setOnClickListener {
            text = inputText.text.toString() + "%"
            inputText.setText(text)
            chk = chk + 1
        }

        // Configurar el listener del botón de igual
        buttonIgua.setOnClickListener {
            text = resultText.text.toString()
            inputText.setText(text)
            resultText.setText(null)
        }

        // Configurar el listener del botón de borrar
        buttonBorrar.setOnClickListener{
            inputText.setText(null)
            resultText.setText(null)
        }

        // Configurar el listener del botón de retroceso
        buttonRetro.setOnClickListener {
            var retroceso: String? = null
            if (inputText.text.length > 0){
                val stringBuilder: StringBuilder = StringBuilder(inputText.text)
                val buscar = inputText.text.toString()
                val buscar2 = buscar.last()

                if (buscar2.equals("+") || buscar2.equals("-") || buscar2.equals("*") || buscar2.equals("/") || buscar2.equals("%")) {
                    chk = chk - 1
                }

                stringBuilder.deleteCharAt(inputText.text.length-1)
                retroceso = stringBuilder.toString()
                inputText.setText(retroceso)
                resultado(retroceso)
            }
        }
    }

    // Función para mostrar el resultado
    private fun resultado(text: String) {
        try {
            val result: Double = evaluarExpresion(text)
            val mainr = result.toString()
            if (chk == 0) {
                resultText.setText(null)
            } else {
                resultText.setText(mainr)
            }
        } catch (e: Exception) {
            Log.d("TAG", "ERROR")
        }
    }

    // Evaluar una expresión matemática y obtener el resultado
    private fun evaluarExpresion(expresion: String): Double {
        val tokens = expresion.split(Regex("(?<=[-+*/%])|(?=[-+*/%])"))
        var resultado = tokens[0].toDouble()

        for (i in 1 until tokens.size step 2) {
            val operador = tokens[i]
            val numero = tokens[i + 1].toDouble()

            try {
                resultado = when (operador) {
                    "+" -> suma(resultado, numero)
                    "-" -> resta(resultado, numero)
                    "*" -> multiplicacion(resultado, numero)
                    "/" -> {
                        if (numero == 0.0) {
                            throw ArithmeticException("División por cero")
                        }
                        division(resultado, numero)
                    }
                    "%" -> porcentaje(resultado, numero)
                    else -> throw IllegalArgumentException("Operador no válido")
                }
            } catch (e: ArithmeticException) {
                // Manejo de la excepción de división por cero
                resultado = Double.NaN // o algún otro valor que indique un resultado inválido
            } catch (e: Exception) {
                Log.d("TAG", "ERROR")
                resultado = Double.NaN // o algún otro valor que indique un resultado inválido
            }
        }

        return resultado // Devolver el resultado final de la evaluación de la expresión
    }

    // Funciones de operaciones matemáticas

    private fun suma(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    private fun resta(num1: Double, num2: Double): Double {
        return num1 - num2
    }

    private fun multiplicacion(num1: Double, num2: Double): Double {
        return num1 * num2
    }

    private fun division(num1: Double, num2: Double): Double {
        if (num2 == 0.0) {
            throw ArithmeticException("División por cero")
        }
        return num1 / num2
    }

    private fun porcentaje(num1: Double, num2: Double): Double {
        return num1 * (num2 / 100)
    }


}