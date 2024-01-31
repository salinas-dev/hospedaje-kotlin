package com.salinas.hospedaje.model

import android.content.Context
import android.widget.Toast


class Hospedaje(private val dias: Int, private val precioDiario: Double) {
    fun calcularSubtotal(): Double {
        return dias * precioDiario
    }

    fun calcularDescuento(): Double {
        val operacionGeneral = calcularSubtotal()
        var descuento = 0.0
        if (dias > 15) {
            descuento = operacionGeneral * 0.20
        } else if (dias > 10) {
            descuento = operacionGeneral * 0.15
        } else if (dias > 5) {
            descuento = operacionGeneral * 0.10
        }
        return descuento
    }

    fun calcularTotalPagar(): Double {
        val operacionGeneral = calcularSubtotal()
        val descuento = calcularDescuento()
        return operacionGeneral - descuento
    }

    fun mostrarResultados(context: Context?) {
        val operacionGeneral = calcularSubtotal()
        val descuento = calcularDescuento()
        val totalPagar = calcularTotalPagar()
        val mensaje = """
               Subtotal a pagar: $operacionGeneral
               Descuento: $descuento
               Total a pagar: $totalPagar
               """.trimIndent()
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
    }
}
