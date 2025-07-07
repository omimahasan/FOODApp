package com.example.foodapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.databinding.FragmentSignUpBinding
import com.example.foodapp.viewmodel.LoginViewModel
import com.example.foodapp.viewmodel.LoginViewModelFactory

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val factory = LoginViewModelFactory(requireContext())
        viewModel = ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]

        binding.signUp.setOnClickListener {
            val name = binding.edName.text.toString().trim()
            val email = binding.edEmailAddress.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()

            if (validateInputs(name, email, password)) {
                viewModel.signUp(email, password)
            }
        }

        observeViewModel()

        return binding.root
    }

    private fun validateInputs(name: String, email: String, password: String): Boolean {
        return when {
            name.isEmpty() -> {
                binding.edName.error = "Name required"
                false
            }
            email.isEmpty() -> {
                binding.edEmailAddress.error = "Email required"
                false
            }
            password.length < 6 -> {
                binding.edPassword.error = "Password must be at least 6 characters"
                false
            }
            else -> true
        }
    }

    private fun observeViewModel() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Sign up successful", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
