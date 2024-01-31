package com.salinas.hospedaje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.salinas.hospedaje.model.Hospedaje

class MainActivity : AppCompatActivity() {
    private lateinit var diasTextInputLayout: TextInputLayout
    private lateinit var precioDiarioTextInputLayout: TextInputLayout
    private lateinit var resultTextView: TextView
    private lateinit var calcularButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diasTextInputLayout = findViewById(R.id.diasTextInputLayout)
        precioDiarioTextInputLayout = findViewById(R.id.precioDiarioTextInputLayout)
        resultTextView = findViewById(R.id.resultTextView)
        calcularButton = findViewById(R.id.calcularButton)

        calcularButton.setOnClickListener {
            val diasText = diasTextInputLayout.editText?.text.toString()
            val precioDiarioText = precioDiarioTextInputLayout.editText?.text.toString()

            // Restablecer errores previos
            diasTextInputLayout.error = null
            precioDiarioTextInputLayout.error = null

            if (diasText.isNotEmpty() && precioDiarioText.isNotEmpty()) {
                val dias = diasText.toInt()
                val precioDiario = precioDiarioText.toDouble()

                val hospedaje = Hospedaje(dias, precioDiario)
                val totalPagar = hospedaje.calcularTotalPagar()
                val descuento = hospedaje.calcularDescuento()

                // Mostrar los resultados en el TextView
                resultTextView.text = "Días: $dias" +
                        "\nPrecio: $ $precioDiario " +
                        "\nDescuento: $ $descuento" +
                        "\nTotal a pagar: $ $totalPagar\n"

                // Si deseas mostrar los resultados en un Toast, puedes utilizar el siguiente código:
                // hospedaje.mostrarResultados(this)
            } else {
                if (diasText.isEmpty()) {
                    diasTextInputLayout.error = "Por favor, ingresa los días."
                }
                if (precioDiarioText.isEmpty()) {
                    precioDiarioTextInputLayout.error = "Por favor, ingresa el precio diario."
                }
            }
        }
    }
}
