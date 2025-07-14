package com.example.foodapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.foodapp.databinding.FragmentSignInBinding
import com.example.foodapp.utils.ValidationHelper
import com.example.foodapp.viewmodel.LoginViewModel
import com.example.foodapp.viewmodel.LoginViewModelFactory

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: LoginViewModel by activityViewModels {
        LoginViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.signIn.setOnClickListener {
            val email = binding.edEmailAddress.text.toString()
            val password = binding.edPassword.text.toString()

            val emailError = ValidationHelper.validateEmail(email)
            val passwordError = ValidationHelper.validatePassword(password)

            when {
                emailError != null -> Toast.makeText(requireContext(), emailError, Toast.LENGTH_SHORT).show()
                passwordError != null -> Toast.makeText(requireContext(), passwordError, Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(requireContext(), "Logging in...", Toast.LENGTH_SHORT).show()
                    viewModel.login(email, password)
                }
            }
        }

        return binding.root
    }
}
