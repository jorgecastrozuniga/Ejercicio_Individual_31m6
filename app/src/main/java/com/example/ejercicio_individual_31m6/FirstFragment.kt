package com.example.ejercicio_individual_31m6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.ejercicio_individual_31m6.Model.Player
import com.example.ejercicio_individual_31m6.Model.PlayerDataBase
import com.example.ejercicio_individual_31m6.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!
    private var semuestra: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val database = Room.databaseBuilder(
            requireContext().applicationContext,
            PlayerDataBase::class.java,
            "jugadoresDb"
        )
            .build()



        binding.btagregar.setOnClickListener {

            val datosValidos = ingresarDatos()

            if (datosValidos) {
                val newPlayer = Player(

                    apodo = binding.textInputLayoutApodo.editText?.text.toString(),
                    nombrecompleto = binding.textInputLayoutNombrecompleto.editText?.text.toString(),
                    edad = binding.textInputLayoutEdad.editText?.text.toString().toInt()

                )





                GlobalScope.launch(Dispatchers.IO) {
                    database.getPlayerDao().insertPlayer(newPlayer)
                    Log.d("database", "newPlayer")


                }

            } else {
                Toast.makeText(requireContext(), "Ingrese datos válidos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btmostrar.setOnClickListener {
            semuestra = false
            irasegundofragmento()
            Log.d("semuestra", semuestra.toString())
        }

        binding.btborrar.setOnClickListener {

            semuestra = true
            Log.d("semuestra","********"+semuestra.toString())
            irasegundofragmento()



        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun ingresarDatos(): Boolean {

        var datosValidos = true

        var apodo = binding.textInputLayoutApodo.editText?.text.toString()
        if (apodo.isNullOrBlank()) {
            binding.textInputLayoutApodo.editText?.error = "Ingrese un apodo"
            datosValidos = false
        } else if (apodo.length > 30) {
            binding.textInputLayoutApodo.editText?.error =
                "Apodo no puede llevar mas de 30 caracteres"
            datosValidos = false
        }

        var nombre = binding.textInputLayoutNombrecompleto.editText?.text.toString()
        if (nombre.isNullOrBlank()) {
            binding.textInputLayoutNombrecompleto.editText?.error = "Ingrese nombre completo"
            datosValidos = false
        } else if (!nombre.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"))) {
            binding.textInputLayoutNombrecompleto.editText?.error =
                "El nombre solo puede llevar letras"
            datosValidos = false
        } else if (nombre.length < 5) {
            binding.textInputLayoutNombrecompleto.editText?.error =
                "El nombre no puede tener menos de 5 caracteres"
            datosValidos = false
        }

        var edad = binding.textInputLayoutEdad.editText?.text.toString().toIntOrNull()
        if (edad == null) {
            binding.textInputLayoutEdad.editText?.error = "Ingrese una edad válida"
            datosValidos = false
        } else if (edad < 0 || edad > 150) {
            binding.textInputLayoutEdad.editText?.error =
                "la edad debe ser mayor a 1 y menor a 150"
            datosValidos = false
        }

        return datosValidos
    }

    fun irasegundofragmento() {
        val bundle = Bundle()
        bundle.putBoolean("semuestra", semuestra)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}