package com.example.marvel.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.marvel.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    lateinit var _view: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      _view = inflater.inflate(R.layout.fragment_login, container, false)

        return _view
    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            login(_view)
            createAccount(_view)
        }

    private fun login(view: View) {
        view.findViewById<Button>(R.id.btnLogIn).setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.txtEmailLogin)?.text.toString()
            val password = view.findViewById<TextInputEditText>(R.id.edtPasswordLogin)?.text.toString()
            if(email.isEmpty()) {
                view.findViewById<TextInputEditText>(R.id.txtEmailLogin).error = "Campo vazio!"
            } else if (password.isEmpty()){
                view.findViewById<TextInputEditText>(R.id.edtPasswordLogin).error = "Campo vazio!"
            } else
            view.findNavController().navigate(R.id.action_loginFragment_to_hq_list_fragment)
        }
    }

    private fun createAccount(view: View) {
        view.findViewById<Button>(R.id.btnCreatAccount).setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}
