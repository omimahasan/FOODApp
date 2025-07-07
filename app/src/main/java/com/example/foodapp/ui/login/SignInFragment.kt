package com.example.foodapp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodapp.data.Repository.AuthRepository
import com.example.foodapp.databinding.FragmentSignInBinding
import com.example.foodapp.viewmodel.LoginViewModel
import com.example.foodapp.viewmodel.LoginViewModelFactory

import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var factory: LoginViewModelFactory
    private val viewModel: LoginViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        factory = LoginViewModelFactory(AuthRepository(FirebaseAuth.getInstance()))

        binding.signIn.setOnClickListener {
            val email = binding.edEmailAddress.text.toString()
            val password = binding.edPassword.text.toString()

            if (email.isNotEmpty() && password.length >= 6) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter valid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) {
            if (it) {
                saveLoginState()
                findNavController().navigate(com.example.foodapp.R.id.action_loginFragment_to_homeFragment)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun saveLoginState() {
        val prefs = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("isLoggedIn", true).apply()
    }
}
