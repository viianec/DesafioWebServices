package com.example.marvel.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.marvel.R
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register(view)
        backLogin(view)

}

    private fun backLogin(view: View) {
        view.findViewById<ImageView>(R.id.imgBackRegister).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun register(view: View) {
        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.edtEmailRegister)?.text.toString()
            val password = view.findViewById<TextInputEditText>(R.id.edtPasswordRegister)?.text.toString()
            val nome = view.findViewById<TextInputEditText>(R.id.edtNomeRegister)?.text.toString()
            if(nome.isEmpty()) {
                view.findViewById<TextInputEditText>(R.id.edtNomeRegister).error = "Campo vazio!"
            } else if (email.isEmpty()){
                view.findViewById<TextInputEditText>(R.id.edtEmailRegister).error = "Campo vazio!"
            } else if (password.isEmpty()){
                view.findViewById<TextInputEditText>(R.id.edtPasswordRegister).error = "Campo vazio!"
            } else {
                view.findNavController().navigate(R.id.action_registerFragment_to_hq_list_fragment)
                Toast.makeText(context, "Usuário cadastrado", Toast.LENGTH_SHORT).show()
            }

        }
    }
}