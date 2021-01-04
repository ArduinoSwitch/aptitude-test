package com.example.pruebaandroid.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.pruebaandroid.R

class MainActivity : AppCompatActivity() {

    /**
     * Tengo hecho la mitad +-, me falta toda la parte de UI ( hacer recyclerView de todos los
     * items diferentes que hay en las transacciones y luego hacer un on click pasando los datos
     * al detalle y haciendo los calculos necesarios.
     *
     * No he tenido tiempo de terminar la prueba y envio esto en prueba de que estoy
     * trabajando en ello, si con lo que he enviado no basta pues lo siento, estoy a tope este mes y
     * no tengo tiempo para casi nada (fin de semana incluido) si quereis la prueba acabada
     * comentarse-lo a Ines (tardare 1 semana mas min porque de verdad no tengo tiempo).
     * Ines esta avisada de esto tmb :P espero que tengais buena semana
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}